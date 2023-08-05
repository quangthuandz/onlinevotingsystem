<%-- 
    Document   : Profile
    Created on : May 22, 2023, 8:19:47 AM
    Author     : khanhnhatt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Account" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Homepage - Voting online</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="override.css">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <style>
            .nav-item:hover {
                border-top: 1px solid white;
            }
        </style>
    </head>

    <body>
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
            <a class="navbar-brand bg-primary px-4 py-3" href="home">
                electriq
            </a>

            <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                +
            </button>

            <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <ul class="navbar-nav border-top border-lg-0">
                </ul>

                <ul class="navbar-nav ms-auto me-0 me-lg-3 ">
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="setup">Create new Campaign</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="logout">Log out</a>
                    </li>
                </ul>
            </div><!-- .navbar-collapse -->

        </nav>

        <!-- Header - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <header class="py-7 text-center text-white bg-trees">
            <div class="container-fluid">
                <p class="lead fw-normal"></p>
            </div><!-- .container -->
        </header>
        <!-- Features ------------------------------------------------------------------- -->
        <section style="background-color: #eee;">
            <div class="container py-5">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item"><a href="userhome" style="text-decoration: none;">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                            </ol>
                        </nav>
                    </div>
                </div>
                <form action="edit" method="post">
                    <input type="hidden" id="id" name="id" value="${data.getId()}">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="card mb-4">
                                <div class="card-body text-center">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                         class="rounded-circle img-fluid" style="width: 150px;">
                                    <h5 class="my-3">${data.getUsername()}</h5>
                                    <p class="text-muted mb-1">${data.getJob()}</p>
                                    <p class="text-muted mb-4">${data.getFormattedDob()}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <!-- First Name -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="firstname" class="input-group-text bg-white px-4 border-md border-right-0">First Name</label> <br>
                                            <input class="form-control bg-white border-left-0 border-md" type="text" name="firstname" placeholder="First Name" value="${data.getFirstName()}">
                                        </div>
                                        <div class="input-group mb-4 col">
                                            <label for="lastname" class="input-group-text bg-white px-4 border-md border-right-0">Last Name</label>
                                            <input id="lastName" type="text" name="lastname" placeholder="Last Name" class="form-control bg-white border-left-0 border-md" value="${data.getLastName()}">
                                        </div>
                                    </div>
                                    <!-- Dob -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="dob" class="input-group-text bg-white px-4 border-md border-right-0">Date of Birth</label>
                                            <input id="lastName" type="date" name="dob" placeholder="Date of Birth" class="form-control bg-white border-left-0 border-md" value="${data.getDob()}">
                                        </div>
                                    </div>
                                    <!-- Job -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="job" class="input-group-text bg-white px-4 border-md border-right-0">Job</label>
                                            <select id="job" name="job" class="form-control custom-select bg-white border-left-0 border-md">
                                                <option value="">Choose your job</option>
                                                <option id="Designer" value="Designer" ${data.getJob() == 'Designer'?'selected':''}>Designer</option>
                                                <option id="Developer" value="Developer" ${data.getJob() == 'Developer'?'selected':''}>Developer</option>
                                                <option id="Manager" value="Manager" ${data.getJob() == 'Manager'?'selected':''}>Manager</option>
                                                <option id="Accountant" value="Accountant" ${data.getJob() == 'Accountant'?'selected':''}>Accountant</option>
                                                <option id="Teacher" value="Teacher" ${data.getJob() == 'Teacher'?'selected':''}>Teacher</option>
                                                <option id="Student" value="Student" ${data.getJob() == 'Student'?'selected':''}>Student</option>
                                                <option id="Freelance" value="Freelancer" ${data.getJob() == 'Freelancer'?'selected':''}>Freelancer</option>
                                                <option id="Other" value="Other" ${data.getJob() == 'Other'?'selected':''}>Other</option>
                                            </select>
                                            <!-- Get Job from DB -->
                                            <script>
                                                var job = ${data.getJob()};
                                                document.getElementById(job).checked = true;
                                            </script>
                                        </div>
                                    </div>
                                    <!-- Gender -->
                                    <div class="row">
                                        <div class="input-group mb-4 col-lg-12">
                                            <label for="gender" class="input-group-text bg-white px-4 border-md border-right-0">Gender</label> <br>
                                            <div class="align-items-center" style="margin-left: 15px; margin-top: 6px">
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="gender" name="gender" value="male" ${data.getGender() == 'male'?'checked':''}>
                                                    <label class="form-check-label" for="gender">Male</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="gender" name="gender" value="female" ${data.getGender() == 'female'?'checked':''}>
                                                    <label class="form-check-label" for="gender">Female</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="gender" name="gender" value="other" ${data.getGender() == 'other'?'checked':''}>
                                                    <label class="form-check-label" for="gender">Other</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Email -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="email" class="input-group-text bg-white px-4 border-md border-right-0">Email</label>
                                            <input id="email" type="email" name="email" placeholder="Email" class="form-control bg-white border-left-0 border-md" value="${data.getEmail()}">
                                        </div>
                                    </div>
                                    <!-- Phone -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="phone" class="input-group-text bg-white px-4 border-md border-right-0">Phone</label>
                                            <input id="phone" type="text" name="phone" placeholder="Phone" class="form-control bg-white border-left-0 border-md" value="${data.getPhone()}">
                                        </div>
                                    </div>
                                    <!-- Address -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="address" class="input-group-text bg-white px-4 border-md border-right-0">Address</label>
                                            <input id="address" type="text" name="address" placeholder="Address" class="form-control bg-white border-left-0 border-md" value="${data.getAddress()}">
                                        </div>
                                    </div>
                                    ${error}
                                    <br><input class="btn btn-primary" type="submit" value="Edit" name="Edit">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- end of features -->
    </body>
</html>