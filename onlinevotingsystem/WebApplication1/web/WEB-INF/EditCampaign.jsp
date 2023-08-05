<%-- 
    Document   : Profile
    Created on : May 22, 2023, 8:19:47 AM
    Author     : khanhnhatt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Campaign" %>
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

                <ul class="navbar-nav ms-auto px-5">
                    <div class="dropdown">
                        <button type="button" class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown">
                            Menu
                        </button>
                        <ul class="dropdown-menu">
                            <li >
                                <a class="dropdown-item " href="#" >
                                    Create new vote
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="profile">
                                    User profile
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="logout">
                                    Log out
                                </a>
                            </li>
                        </ul>
                    </div>
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
                                <li class="breadcrumb-item active" aria-current="page">Campaign Setting</li>
                            </ol>
                        </nav>
                    </div>
                </div>
                <form action="edit_campaign" method="post">
                    <input type="hidden" id="id" name="id" value="${c.getId()}">
                    <!-- name -->
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="name" class="input-group-text bg-white px-4 border-md border-right-0">Campaign Name</label>
                            <input id="name" type="text" name="name" placeholder="Campaign Name" class="form-control bg-white border-left-0 border-md" value="${c.getName()}">
                        </div>
                    </div>
                    <!-- desc -->
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="desc" class="input-group-text bg-white px-4 border-md border-right-0">Campaign Description</label>
                            <input id="desc" type="text" name="desc" placeholder="Campaign Description" class="form-control bg-white border-left-0 border-md" value="${c.getDes()}">
                        </div>
                    </div>
                    <!-- start time -->
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="startTime" class="input-group-text bg-white px-4 border-md border-right-0">Start Time</label>
                            <input id="startTime" type="date" name="startTime" placeholder="Start Time" class="form-control bg-white border-left-0 border-md" value="${c.getStartTime()}">
                        </div>
                    </div>
                    <!-- end time -->
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="endTime" class="input-group-text bg-white px-4 border-md border-right-0">End Time</label>
                            <input id="endTime" type="date" name="endTime" placeholder="End Time" class="form-control bg-white border-left-0 border-md" value="${c.getEndTime()}">
                        </div>
                    </div>
                    <!-- ruleid -->
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="ruleId" class="input-group-text bg-white px-4 border-md border-right-0">Rule ID</label>
                            <input id="ruleId" type="text" name="ruleId" placeholder="Rule ID" class="form-control bg-white border-left-0 border-md" value="${c.getRuleId()}">
                        </div>
                    </div>
                    <!-- isPublic -->    
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="isPublic" class="input-group-text bg-white px-4 border-md border-right-0">Visibility</label>
                            <div class="align-items-center" style="margin-left: 15px; margin-top: 6px">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="isPublic" name="isPublic" value="1" ${c.isIsPublic() ? 'checked' : ''}>
                                    <label class="form-check-label" for="gender">Public</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="isPublic" name="isPublic" value="0" ${c.isIsPublic() ? 'checked' : ''}>
                                    <label class="form-check-label" for="gender">Private</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- password -->
                    <div class="row">
                        <div class="input-group mb-4 col">
                            <label for="password" class="input-group-text bg-white px-4 border-md border-right-0">Password</label>
                            <input id="password" type="password" name="password" placeholder="Password" class="form-control bg-white border-left-0 border-md" value="${c.getPassword()}">
                        </div>
                    </div>
                        
                        <input type="submit" class="btn btn-primary" value="Save Changes">
                </form>
            </div>
        </section>
        <!-- end of features -->
    </body>
</html>