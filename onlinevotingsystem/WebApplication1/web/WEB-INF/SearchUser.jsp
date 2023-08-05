<%-- 
Document   : SearchUser
Created on : 26/05/2023
Author     : khanhnhatt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Campaign" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <title>User Management</title>
        <link rel="stylesheet" type="text/css" href="usermanage.css">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>User management</title>
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

    <style>
        /* ... (previous CSS styles) ... */

        .table thead th,
        .table tbody td {
            border-right: 2px solid #dee2e6;
        }
    </style>
    <body>
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
            <a class="navbar-brand bg-primary px-4 py-3" href="admin">
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
                        <a class="nav-link px-4 px-lg-2 py-3" href="admin">Campaign Management</a>
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
        <section class="py-5">
            <div class="row col container-fluid px-4">
                <h2 class="display-4 text-center" style="margin-top: 20px; color: #000">User Management</h2>
                <!-- search user -->
                <div class="text-end" style="width: 30%; margin-bottom: -20px">
                    <form action="search_user" method="post" class="row">
                        <input type="text" id="id" name="searchQuery" class="form-control col" style="width: 20%">
                        <select class="btn btn-outline-secondary dropdown-toggle col" type="button" name="searchType" data-bs-toggle="dropdown" aria-expanded="false">
                            <option class="dropdown-item" value="Username">Username</option>
                            <option class="dropdown-item" value="Name">Name</option>
                            <option class="dropdown-item" value="Male">Male</option>
                            <option class="dropdown-item" value="Female">Female</option>
                        </select>
                        <button type="submit" class="btn btn-outline-secondary col">Search</button>
                    </form>
                </div>
                <div class="row my-4">
                    <div class="col py-2">
                        <!-- user table -->
                        <section class="row py-2 text-center bg-light" style="display: flex;justify-content: space-around;">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Username</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Phone</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">DOB</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Job</th>
                                        <th scope="col">Select</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <form action="delete" method="post">
                                    <c:forEach var="user" items="${userList}">
                                        <tr>
                                            <th scope="row">${user.getId()}</th>
                                            <td>${user.getUsername()}</td>
                                            <td>${user.getName()}</td>
                                            <td>${user.getEmail()}</td>
                                            <td>${user.getPhone()}</td>
                                            <td>${user.getAddress()}</td>
                                            <td>${user.getDob()}</td>
                                            <td>${user.getGender()}</td>
                                            <td>${user.getJob()}</td>
                                            <td><input type="checkbox" id="id" name="selectedUsers" value="${user.getId()}"></td>
                                        </tr>
                                    </c:forEach>

                                    <tr>
                                        <td colspan="10">
                                            <button type="submit" style ="color: #007bff">DELETE</button>
                                        </td>
                                    </tr>
                                </form>
                                </tbody>
                            </table>
                        </section>
                    </div><!-- .col -->
                </div><!-- .row -->
            </div><!-- .container -->
        </section>

        <style>
            /* CSS Styling for User Management Section */
            .py-5 {
                padding-top: 3rem;
                padding-bottom: 3rem;
            }

            .text-end {
                display: flex;
                justify-content: flex-end;
                align-items: center;
                width: 30%;
                margin-bottom: -20px;
            }

            .form-control.col {
                width: 20%;
            }

            .btn-outline-secondary.col {
                margin-left: 10px;
            }

            .row.my-4 {
                margin-bottom: 2rem;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
            }

            .table thead th {
                background-color: #f8f9fa;
                border-bottom: 2px solid #dee2e6;
                padding: 8px;
                text-align: center;
            }

            .table tbody td {
                border-bottom: 1px solid #dee2e6;
                padding: 8px;
                text-align: center;
            }

            .table tbody tr:last-child td {
                border-bottom: none;
            }

            .table tbody tr:hover {
                background-color: #f8f9fa;
            }

            .table tbody tr td input[type="checkbox"] {
                margin-right: 2px;
            }

            .table tbody tr td:last-child {
                text-align: center;
            }

            .button-container {
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 20px 0;
            }

            .button-container button {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .button-container button:hover {
                background-color: #0056b3;
            }
        </style>

    </body>
