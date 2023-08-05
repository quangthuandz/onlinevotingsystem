<%-- 
    Document   : Register
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
        <link href="css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <title>Register</title>
        <script src="link">
            // For Demo Purpose [Changing input group text on focus]
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
    <body>
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
            <a class="navbar-brand bg-primary px-4 py-3" href="index">
                electriq
            </a>

            <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                +
            </button>

            <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <!--                <ul class="navbar-nav border-top border-lg-0">
                                    <li class="nav-item">
                                        <a class="nav-link px-4 px-lg-2 py-3" href="login">About us</a>
                                    </li>
                                </ul>-->
                </li>
                </ul>
            </div><!-- .navbar-collapse -->
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
            <div class="row py-5 mt-4 align-items-center align-middle">
                <!-- For Demo Purpose -->
                <div class="col-md-5 pr-lg-5 mb-5 mb-md-0">
                    <img src="https://bootstrapious.com/i/snippets/sn-registeration/illustration.svg" alt="" class="img-fluid mb-3 d-none d-md-block">
                    <h1>Create an Account</h1>
                    <!--                    <p class="font-italic text-muted mb-0">Create a minimal registeration page using Bootstrap 4 HTML form elements.</p>
                                        <p class="font-italic text-muted">Snippet By <a href="https://bootstrapious.com" class="text-muted">
                                                <u>Bootstrapious</u></a>
                                        </p>-->
                </div>

                <!-- Registeration Form -->
                <div class="col-md-7 col-lg-6 ml-auto">
                    <form action="register" method="post">
                        <div class="row">

                            <!-- First Name -->
                            <div class="input-group mb-4 col">
                                <!--<label for="firstname" class="input-group-text bg-white px-4 border-md border-right-0">First Name</label>-->
                                <input id="firstName" type="text" name="firstname" placeholder="First Name" class="form-control bg-white border-left-0 border-md" value="${a.getFirstName()}" required >
                            </div>

                            <!-- Last Name -->
                            <div class="input-group mb-4 col">
                                <!--<label for="lastname" class="input-group-text bg-white px-4 border-md border-right-0">Last Name</label>-->
                                <input id="lastName" type="text" name="lastname" placeholder="Last Name" class="form-control bg-white border-left-0 border-md" value="${a.getLastName()}" required>
                            </div>

                            <!-- Gender -->
                            <div class="input-group col-lg-12 mb-4">
                                <label for="gender" class="input-group-text bg-white px-4 border-md border-right-0">Gender</label> <br>
                                <div class="align-items-center" style="margin-left: 15px; margin-top: 6px">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="gender" name="gender" value="Male" required>
                                        <label class="form-check-label" for="gender">Male</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="gender" name="gender" value="Female" required>
                                        <label class="form-check-label" for="gender">Female</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="gender" name="gender" value="Other" required>
                                        <label class="form-check-label" for="gender">Other</label>
                                    </div>
                                </div>
                            </div>

                            <!-- DOB -->

                            <c:choose>
                                <c:when test="${empty error2}">
                                    <div class="input-group col-lg-12 mb-4">
                                        <label for="dob" class="input-group-text bg-white px-4 border-md border-right-0">Date of Birth</label> <br>
                                        <input type="date" name="dob" placeholder="Date of Birth" class="form-control bg-white border-left-0 border-md" value="${a.getDob()}" required>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-lg-12 mb-4">
                                        <div class="input-group col-lg-12 mb-4">
                                            <label for="dob" class="input-group-text bg-white px-4 border-md border-right-0">Date of Birth</label> <br>
                                            <input type="date" name="dob" placeholder="Date of Birth" class="form-control bg-white border-left-0 border-md is-invalid" value="${a.getDob()}" required>
                                            <div class="invalid-feedback">
                                                Invalid Date of Birth.
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <!-- Job -->
                            <div class="input-group col-lg-12 mb-4">
                                <!--<label for="lastname" class="input-group-text bg-white px-4 border-md border-right-0">Job</label>-->
                                <select id="job" name="job" class="form-control custom-select bg-white border-left-0 border-md"  value="${a.getJob()}" required>
                                    <option value="">Choose your job</option>
                                    <option value="Designer">Designer</option>
                                    <option value="Developer">Developer</option>
                                    <option value="Manager">Manager</option>
                                    <option value="Accountant">Accountant</option>
                                    <option value="Teacher">Teacher</option>
                                    <option value="Student">Student</option>
                                    <option value="Freelancer">Freelancer</option>
                                    <option value="Other">Other</option>
                                </select>
                            </div>

                            <!-- Home Address -->
                            <div class="input-group col-lg-12 mb-4">
                                <input id="address" type="text" name="address" placeholder="Address" class="form-control bg-white border-md border-left-0 pl-3" value="${a.getAddress()}" required>
                            </div>

                            <!-- Phone -->
                            <c:choose>
                                <c:when test="${empty error5}">
                                    <div class="input-group col-lg-12 mb-4">
                                        <input id="phone" type="text" name="phone" placeholder="Phone" class="form-control bg-white border-md border-left-0 pl-3" value="${a.getPhone()}" required>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-lg-12 mb-4">
                                        <input id="phone" type="text" name="phone" placeholder="Phone" class="form-control bg-white border-md border-left-0 pl-3 is-invalid" value="${a.getPhone()}" required>
                                        <div class="invalid-feedback">
                                            Invalid phone number.
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <!-- Email Address -->

                            <c:choose>
                                <c:when test="${empty error4}">
                                    <div class="input-group col-lg-12 mb-4">
                                        <!--<label for="email" class="input-group-text bg-white px-4 border-md border-right-0">Email Address</label>-->
                                        <input id="email" type="email" name="email" placeholder="Email Address" class="form-control bg-white border-left-0 border-md" value="${a.getEmail()}" required>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-lg-12 mb-4">
                                        <input id="email" type="email" name="email" placeholder="Email Address" class="form-control bg-white border-md border-left-0 pl-3 is-invalid" value="${a.getEmail()}" required>
                                        <div class="invalid-feedback">
                                            Email existed.
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <!-- Username -->

                            <c:choose>
                                <c:when test="${empty error1}">
                                    <div class="input-group col-lg-6 mb-4">
                                        <!--<label for="user" class="input-group-text bg-white px-4 border-md border-right-0">Username</label>-->
                                        <input id="password" type="text" name="user" placeholder="Username" class="form-control bg-white border-left-0 border-md" value="${a.getUsername()}" required>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-lg-12 mb-4">
                                        <input id="password" type="text" name="user" placeholder="Username" class="form-control bg-white border-md border-left-0 pl-3 is-invalid" value="${a.getUsername()}" required>
                                        <div class="invalid-feedback">
                                            Username existed.
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <!-- Password -->
                            <div class="input-group col-lg-6 mb-4">
                                <!--<label for="password" class="input-group-text bg-white px-4 border-md border-right-0">Password</label>-->
                                <input id="password" type="password" name="password" placeholder="Password" class="form-control bg-white border-left-0 border-md" required>
                            </div>

                            <!-- Password Confirmation -->
                            <c:choose>
                                <c:when test="${empty error3}">
                                    <div class="input-group col-lg-6 mb-4">
                                        <!--<label for="repassword" class="input-group-text bg-white px-4 border-md border-right-0">Confirm Password</label>-->
                                        <input id="passwordConfirmation" type="password" name="repasss" placeholder="Confirm Password" class="form-control bg-white border-left-0 border-md" required>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-lg-12 mb-4">
                                        <input id="passwordConfirmation" type="password" name="repasss" placeholder="Confirm Password" class="form-control bg-white border-md border-left-0 pl-3 is-invalid" required>
                                        <div class="invalid-feedback">
                                            Password does not match
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                            <!-- Submit Button -->
                            <div class="form-group col-lg-12 mx-auto mb-0 text-center">
                                <input type="submit" value="Create your account" class="font-weight-bold btn btn-primary btn-block py-2"  style="width: 40%;  font-weight: bold">
                            </div>

                            <!-- Divider Text -->
                            <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                <div class="border-bottom w-100 ml-5"></div>
                                <span class="px-2 small text-muted font-weight-bold text-muted">OR</span>
                                <div class="border-bottom w-100 mr-5"></div>
                            </div>

                            <!-- Already Registered -->
                            <div class="text-center w-100">
                                <p class="text-muted font-weight-bold">Already Registered? <a href="login" class="text-primary ml-2">Login</a></p>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
