<%-- 
    Document   : ChangePassword
    Created on : May 16, 2023, 8:54:13 PM
    Author     : khanhnhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="css/styleLogin.css" rel="stylesheet" type="text/css"/>
        <title>electriq - change password</title>
    </head>
    <body>
        <!-- navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0" style="background-color: #5f5f5f">
            <a class="navbar-brand bg-primary px-4 py-3" href="home">
                electriq
            </a>
        </nav>

        <header class="header">
            <nav class="navbar navbar-expand-lg navbar-light py-3">
                <div class="container">
                    <!-- Navbar Brand -->
                    <a href="#" class="navbar-brand">
                        <img src="https://bootstrapious.com/i/snippets/sn-registeration/logo.svg" alt="logo" width="150">
                    </a>
                </div>
            </nav>
        </header>

        <!-- container -->
        <div class="card text-center" style="width: 500px; margin: auto">
            <div class="card-header h5  align-middle">Set new Password</div>

            <div class="card-body px-5">
                <form id="form" action="change_password" method="post" onsubmit=" return window.confirm('Are you sure?');">
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingInput" name="curpass" placeholder="password" >
                        <label for="floatingInput">Current Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingInput" name="newpass" placeholder="password" >
                        <label for="floatingInput">New Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingInput" name="renewpass" placeholder="confirm password" >
                        <label for="floatingInput">Confirm New Password</label>
                    </div>
                    <!-- submit button -->
                    <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                        <input type="submit" value="Save Change" class="font-weight-bold btn btn-primary btn-block py-2"  style="width: 75%;  font-weight: bold">
                    </div>
                </form>
                ${msg}
            </div>
        </div>

    </body>
</html>
