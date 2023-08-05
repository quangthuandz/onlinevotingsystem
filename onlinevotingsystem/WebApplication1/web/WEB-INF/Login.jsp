<%-- 
    Document   : Login
    Created on : May 17, 2023, 7:30:37 AM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="css/styleLogin.css" rel="stylesheet" type="text/css"/>
        <title>electriq - log in</title>
        <script src="link">
            $(function () {
                $('input, select').on('focus', function () {
                    $(this).parent().find('.input-group-text').css('border-color', '#80bdff');
                });
                $('input, select').on('blur', function () {
                    $(this).parent().find('.input-group-text').css('border-color', '#ced4da');
                });
            });

        </script>
    </head>
    <body class="bg-light">
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
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


        <div class="container">
            <div class="row py-5 mt-4 align-items-center align-middle" style="display: flex; justify-content: space-around">
                <!-- For Demo Purpose -->
                <div class="col-md-5 pr-lg-5 mb-5 mb-md-0">
                    <img src="https://bootstrapious.com/i/snippets/sn-registeration/illustration.svg" alt="" class="img-fluid mb-3 d-none d-md-block">
                    <h1>Log in</h1>
                </div>

                <!-- Login Form -->
                <div class="col-md-7 col-lg-6 ml-auto" style="width: 400px; height: 350px">
                    <div class="row card py-3">
                        <form action="login" method="post">
                            <c:choose>
                                <c:when test="${empty error1}">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="floatingInput" name="username" placeholder="Username" value="${user}" required>
                                        <label for="floatingInput"> Username</label>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control is-invalid" id="floatingInput" name="username" placeholder="Username" value="${user}" required>
                                        <label for="floatingInput"> Username</label>
                                        <div class="invalid-feedback">
                                            Incorrect username.
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${empty error}">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" value="${pass}" required>
                                        <label for="floatingPassword"> Password</label>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control is-invalid" id="floatingPassword" name="password" placeholder="Password"value="${pass}" required>
                                        <label for="floatingPassword"> Password</label>
                                        <div class="invalid-feedback">
                                            Incorrect password.
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                            <!-- Submit Button -->
                            <div style="width: 100%">
                                <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                                    <input type="submit" value="Log in" class="font-weight-bold btn btn-primary btn-block py-2"  style="width: 100%;  font-weight: bold">
                                </div>
                            </div>
                        </form>

                        <!-- Forgotten Password -->
                        <a class="text-center" style="text-decoration: none" href="forgot_password">Forgotten Password?</a>

                        <!-- Divider Text -->
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5"></div>
                            <div class="border-bottom w-100 mr-5"></div>
                        </div>

                        <!-- Registration -->
                        <div style="width: 100%">
                            <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                                <a href="register" class="btn btn-block py-2" style="width: 50%; background-color: #06d84a; color: white; font-weight: bold">
                                    <span class="font-weight-bold">Create new account</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
