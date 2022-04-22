/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kampusapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kampusapp.model.DatabaseConfig;
import kampusapp.model.Mahasiswa;
import kampusapp.model.UserLogin;

/**
 *
 * @author piryandyp
 */
public class KampusDAOProses implements KampusDAO{
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    
    @Override
    public List<Mahasiswa> get() {
        List<Mahasiswa>list=new ArrayList<Mahasiswa>();
        try{
            String sql="SELECT * FROM tbl_mhs";
            connection=DatabaseConfig.openConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Mahasiswa mhs=new Mahasiswa();
                mhs=new Mahasiswa();
                mhs.setId(resultSet.getInt("id"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setNim(resultSet.getString("nim"));
                mhs.setKelas(resultSet.getString("kelas"));
                mhs.setJurusan(resultSet.getString("jurusan"));
                list.add(mhs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Mahasiswa mahasiswa) {
        boolean flag = false;
        try{
            String sql = "Insert Into tbl_mhs(nama,nim,kelas,jurusan) values(?,?,?,?)";
            connection=DatabaseConfig.openConnection();
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, mahasiswa.getNama());
            preparedStatement.setString(2, mahasiswa.getNim());
            preparedStatement.setString(3, mahasiswa.getKelas());
            preparedStatement.setString(4, mahasiswa.getJurusan());
            preparedStatement.executeUpdate();
            flag=true;    
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return flag;
    }  

    @Override
    public Mahasiswa getSinggle(int id) {
        Mahasiswa mhs=null;
        try{
            String sql="select * from tbl_mhs where id=?";
            connection=DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                mhs=new Mahasiswa();
                mhs.setId(resultSet.getInt("id"));
                mhs.setNim(resultSet.getString("nim"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setJurusan(resultSet.getString("jurusan"));
                mhs.setKelas(resultSet.getString("kelas"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return mhs;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        boolean flag=false;
        try{
            String sql="update tbl_mhs set nama=?,nim=?,kelas=?,jurusan=? where id=?";
            connection=DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,mahasiswa.getNama());
            preparedStatement.setString(2,mahasiswa.getNim());
            preparedStatement.setString(3,mahasiswa.getKelas());
            preparedStatement.setString(4,mahasiswa.getJurusan());
            preparedStatement.setInt(5,mahasiswa.getId());
            preparedStatement.executeUpdate();
            flag=true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag=false;
        try{
            String sql="delete from tbl_mhs where id=?";
            connection=DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag=true;
        }catch(SQLException ex){
           ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public UserLogin getLogin(String username, String password) {
        UserLogin login=null;
        try{
            String sql ="select * from tbl_login where username=? and password=?";
            connection = DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                login = new UserLogin(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
         return login;
    }
}
