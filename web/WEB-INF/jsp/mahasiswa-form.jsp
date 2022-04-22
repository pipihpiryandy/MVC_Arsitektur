<%-- 
    Document   : mahasiswa-form
    Created on : Nov 16, 2021, 7:48:27 AM
    Author     : piryandyp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" >
            <h2>Add Mahasiswa ${message} </h2>
            <div class="row">
                <div class="col-md-6" >
                    <form action="mahasiswa-form.html" modelAttribute="mhs" method="POST" >                    
                        <div class="form-group">
                            <input type="text" class="form-container" name="nama" placeholder="Nama" value="${mhs.nama}"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-container" name="nim" placeholder="Nim" value="${mhs.nim}"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-container" name="kelas" placeholder="Kelas" value="${mhs.kelas}"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-container" name="jurusan" placeholder="Jurusan" value="${mhs.jurusan}"/>
                        </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/home.html">Back To List</a>
        </div>
    </body>
</html>
