<%-- 
    Document   : home
    Created on : Nov 16, 2021, 6:50:03 AM
    Author     : piryandyp
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@include file="include.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    </head>
    <body>
        <h1 align="center">Selamat Datang Admin Di Halaman List Mahasiswa</h1>
        <a href="mahasiswa-form.html?action=logout">Logout</a>
         cek DB: ${msg}
         <p>
         <table class="table table-striped table-bordered">
             <tr class="thead-dark">
                <th>No</th>
                <th>Nim</th>
                <th>Nama</th>
                <th>Kelas</th>
                <th>Jurusan</th>
                <th>Action</th>
             </tr>
             <c:forEach items="${listMahasiswa}" var="mhs">
                 <tr>
                    <td>${mhs.id}</td>
                    <td>${mhs.nim}</td>
                    <td>${mhs.nama}</td>
                    <td>${mhs.kelas}</td>
                    <td>${mhs.jurusan}</td>
                    <td>
                        <button class="btn-primary" onclick="window.location.href='mahasiswa-formedit.html?action=edit&id=${mhs.id}'">Edit</button>
                        <button class="btn-danger" onclick="window.location.href='mahasiswa-form.html?action=delete&id=${mhs.id}'">Delete</button>
                    </td>
                 </tr>
             </c:forEach>
         </table>
         </p>
         <p>
            <button class="btn btn-primary" onclick="window.location.href='mahasiswa-form.html'" >
                Add Mahasiswa
            </button>
        </p>
    </body>
</html>
