<%-- 
    Document   : EditThread
    Created on : Jun 25, 2023, 2:15:43 AM
    Author     : ducan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Thread" %>
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

    <body class="pb-10">
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
        <section style="background-color: #eee;" >
            <div class="container py-5">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item"><a href="userhome" style="text-decoration: none;">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">View Campaign Details</li>
                            </ol>
                        </nav>
                    </div>
                </div>
                <form action="EditThreadController" method="post">
                    
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <!-- content -->
                                    <div class="row">
                                        <div class="input-group mb-4 col">
                                            <label for="content" class="input-group-text bg-white px-4 border-md border-right-0">Content</label> <br>
                                            <input class="form-control bg-white border-left-0 border-md" type="text" name="content" placeholder="Content" " value="${content}">
                                            <input type="hidden" name="tId" value="${tId}">
                                            <input type="hidden" name="cId" value="${cId}">
                                        </div>
                                    </div>
                                        <button class="btn btn-primary">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
        </section>
        <!-- end of features -->
    </body>
</html>