<%-- 
    Document   : Review
    Created on : Jun 20, 2023, 2:21:00 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.VotingObject" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="override.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <style>
            .flex-container {
                display: flex;
                align-items: stretch;
                justify-content: center;
            }

            .content{
                display: column;
                flex-direction: column;
                margin-bottom: 10px; /* Khoảng cách giữa các thẻ div */
                display: flex;
                justify-content: center;
                margin: 250px;
                margin-top: 100px;
            }

            .breadcrumb1 {
                display: flex;
                flex-direction: column;
                border: 2px solid #cccccc;
                padding: 10px;
            }

            .breadcrumb {
                display: flex;
                align-items: center;
                justify-content: center;
                text-align: center;
                margin-bottom: 10px;
            }

            .breadcrumb ol {
                display: flex;
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .breadcrumb li {
                flex: 1;
                text-align: center;
            }

            .breadcrumb li:not(:last-child)::after {
                content: "";
                margin: 0 5px;
                color: #6c757d;
            }

            .headbread {
                margin-bottom: 10px;
                background-color: #6c757d;
                padding: 5px;
            }

            .headbread h6 {
                margin: 0;
                padding-left: 10px;
                font-size: 14px;
                color: white;
            }

            .contentbread {
                display: inline;
            }

            .breadcrumb a {
                text-decoration: none;
                color: #212529;
                transition: color 0.3s ease;
            }

            .breadcrumb a:hover {
                color: #6c757d;
            }

            .breadcrumb li:first-child a {
                color: #198754;
                font-weight: bold;
            }
            
            h2 {
                text-align: center;
                margin: 50px 0;
            }

            .grid-container {
                display: flex;
                justify-content: center;
            }

            .col-md-4.card.py-2 {
                width: 18rem;
                margin-top: 10px;
            }

            img {
                width: 200px;
                height: 200px;
                border-radius: 50%;
            }

            div.col-md-4.card.py-2 {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                margin: 20px;
            }

            .choice {
                display: flex;
                flex-direction: column;
            }

            .form-check {
                margin-bottom: 10px;
            }

            .form-check-input {
                margin-right: 5px;
            }

            .form-check-label {
                font-weight: bold;
            }





        </style>
    </head>
    <body>
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
                <a class="navbar-brand bg-primary px-4 py-3" href="home">
                    electriq
                </a>

                <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    +
                </button>
            </nav>
        </div>



        <div class="content">
            <div class="breadcrumb1">
                <div class="contentbread">
                    <!--<nav style="--bs-breadcrumb-divider: url(&#34;data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='%236c757d'/%3E%3C/svg%3E&#34;);" aria-label="breadcrumb">-->
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active" aria-current="page">Set up</li>
                            <li class="breadcrumb-item active" aria-current="page">Details</li>
                            <li class="breadcrumb-item"><a href="#" style="color: #3333ff;font-weight: bold">Review</a></li>                                               
                        </ol>
                    </nav>
                </div>

            </div>

            <h2>${questionforcam}</h2>
            <div class="grid-container">
                <c:forEach items="${votingobject}" var="v" >
                    <div class="col-md-4 card py-2" style="width: 18rem;margin-top: 10px;">
                        <img src="${v.getImgPath()}" alt="..." >
                        <div class="card-body">
                            <h5 class="card-title">${v.getVotingObjectName()}</h5>
                            <p>${v.getVotingObjectDescription()}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="text-center mt-4">
                <a href="javascript:void(0)" onclick="createCampaign()" class="btn btn-primary">Create Campaign</a>
            </div>
            <script>
                function createCampaign() {
                    var currentDate = new Date();
                    var year = currentDate.getFullYear();
                    var month = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1
                    var day = currentDate.getDate();
                    var hours = currentDate.getHours();
                    var minutes = currentDate.getMinutes();
                    var seconds = currentDate.getSeconds();

                    var url = "timeupdate?year=" + year + "&month=" + month + "&day=" + day + "&hours=" + hours + "&minutes=" + minutes + "&seconds=" + seconds;
                    window.location.href = url;
                }
            </script>
    </body>
</html>
