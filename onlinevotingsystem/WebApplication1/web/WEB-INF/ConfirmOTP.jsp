<%-- 
    Document   : ConfirmOTP
    Created on : May 25, 2023, 9:08:13 PM
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
        <title>electriq - otp confirm</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0" style="background-color: #5f5f5f">
            <a class="navbar-brand bg-primary px-4 py-3" href="index">
                electriq
            </a>
        </nav>
        <!-- Navbar-->
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
            <div class="card-header h5  align-middle">OTP Verification</div>

            <div class="card-body px-5">

                <p class="card-text py-2">Enter your OTP</p>
                <form action="confirmemail" method="post">
                    <input type="hidden" name="email" value="${email}">
                    <input type="hidden" name="otpran" value="${otpran}">
                    <input type="hidden" name="op" value="0">       <!-- for diff op in controller -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" name="otp" placeholder="OTP">
                        <label for="floatingInput">OTP</label>
                    </div>

                    <!-- divide text -->
                    <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                        <div class="border-bottom w-100 mr-5"></div>
                    </div>

                    <!-- submit button -->
                    <div>
                        <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                            <input type="submit" value="Search" class="font-weight-bold btn btn-primary btn-block py-2"  style="width: 75%;  font-weight: bold">
                        </div>
                        <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                            <a type="button" onclick="window.location.reload()" class="font-weight-bold btn btn-block"  style="width: 50%">Resend OTP</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        ${msg}
    </body>
</html>