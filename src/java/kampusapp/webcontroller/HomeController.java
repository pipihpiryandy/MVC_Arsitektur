/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kampusapp.webcontroller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kampusapp.dao.KampusDAO;
import kampusapp.dao.KampusDAOProses;
import kampusapp.model.DatabaseConfig;
import kampusapp.model.Mahasiswa;
import kampusapp.model.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author piryandyp
 */
@Controller
public class HomeController {
    KampusDAO mhsDao=null;
    public HomeController(){
        mhsDao=new KampusDAOProses();
    }
    
    @RequestMapping(value="/home")
    public ModelAndView home(HttpServletRequest req,HttpServletResponse res)throws SQLException{
        HttpSession session=req.getSession(false);
        if(session==null){
            return new ModelAndView("redirect:/login.html");
        }else{
            UserLogin user=(UserLogin) session.getAttribute("user");
            if(user.getRole().equalsIgnoreCase("admin")){
                return listMahasiswa(req,res);   
            }else{
                return new ModelAndView("redirect:/member.html");  
            }
        }
    }

    private ModelAndView listMahasiswa(HttpServletRequest req, HttpServletResponse res) {
       List<Mahasiswa> list=mhsDao.get();
       ModelAndView modelandview=new ModelAndView();
       modelandview.addObject("listMahasiswa", list);
       return modelandview;
    }
    
    
    @RequestMapping(value="/member")
    public ModelAndView member(HttpServletRequest req,HttpServletResponse res)throws SQLException{
        HttpSession session=req.getSession(false);
        if(session==null){
            return new ModelAndView("redirect:/login.html");
        }else{
            return new ModelAndView();
        }
    }
}
