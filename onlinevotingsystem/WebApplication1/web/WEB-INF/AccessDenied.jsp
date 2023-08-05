<%-- 
    Document   : AccessDenied
    Created on : May 29, 2023, 11:02:14 AM
    Author     : ducan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <title>Access Denied</title>
    </head>
    <body class="bg-gradient">
        <div style="display: flex;">
            <img class="py-5" src="images/stop.png" alt="access denied" style="margin-right: 150px;" />
            <div style="margin-top: 150px;">
                <h1 style="margin-left: 120px;">FORBIDDEN</h1>
                <h1 style="margin-left: 190px;color: red">403</h1>
                <h3>To go to inside, you have to login first</h3>
                <div class="py-3" style="margin-left: 110px;">
                    <a class="btn btn-primary" href="index">Back to HomePage</a> or
                    <a class="btn btn-primary" href="login">Login</a> 
                </div>

            </div>
        </div>
    </body>
</html>
