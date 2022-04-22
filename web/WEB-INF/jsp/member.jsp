<%-- 
    Document   : member
    Created on : Dec 5, 2021, 8:08:14 AM
    Author     : piryandyp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        welcome ${sessionScope.user.username} di halaman member
        <a href="mahasiswa-form.html?action=logout">Logout</a>
    </body>
</html>
