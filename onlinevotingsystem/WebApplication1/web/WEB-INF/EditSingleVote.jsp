<%-- 
    Document   : Vote
    Created on : Jun 15, 2023, 6:25:26 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Voting Form</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="fontawesome-free-6.3.0-web/css/all.min.css" type="text/css" />
    </head>
    <style>
        *{
            mardin:0;
            padding:0;
            box-sizing: border-box;
        }
        body{
            margin: 0;
            padding:0;
            font-family: "Outfit",sans-serif;
        }
        .wrapper{
            height: 100%;
            background-color: #1b1d28;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }
        .wrapper > h1{
            margin-bottom: 80px;
            color: #fff;
            font-size: 40px;
            font-weight: 400;
            text-transform: capitalize;
        }
        .radio-buttons {
            /* Updated styling */
            border-radius: 10px;
            max-width: 1200px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
        }

        .custom-radio {
            /* Updated styling */
            flex: 1;
            flex-basis: 150px; /* Adjust the initial width of each button as needed */
            margin: 0 auto;
            max-width: 234px; /* Adjust the maximum width of each button as needed */
        }

        .custom-radio input{
            display: none;
        }
        .radio-btn{
            position:relative;
            width: 234px;
            height: 260px;
            margin: 10px;
            background-color: #222533;
            border: 4px solid transparent;
            border-radius: 10px;
            cursor: pointer;
        }
        .radio-btn .content{
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .radio-btn .candidate-img{
            width: 80px;
            height: 80px;
            margin: 20px 0;
            border-radius: 50%;
            overflow: hidden;
        }
        .radio-btn .candidate-img img{
            width:100%;
            height:100%;
            user-select: none;
        }
        .radio-btn h2{
            color: #fff;
            margin-bottom: 5px;
            font-size: 20px;
            font-weight: 700;
        }
        .radio-btn p{
            color: #fff;
            margin-bottom: 5px;
            font-size: 14px;
            font-weight: 500;
        }
        .radio-btn .check-icon{
            width:30px;
            height: 30px;
            background-color: #1b1d28;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }
        .radio-btn .check-icon .icon{
            color: #fff;
            display: inline-block;
            position: relative;
            width: 18px;
            height: 8px;
            margin-top: -2px;
            transform: rotate(-40deg);
        }
        .radio-btn .check-icon .icon::before{
            content: "";
            width: 3px;
            height: 100%;
            background-color: #fff;
            position: absolute;
            left: 0;
            bottom: 0;
            border-radius: 10px;
            box-shadow: -2px 0 5px rgba(0,0,0,0.231);
            transform: scaleY(0);
            transform-origin: top;
            transition: all 0.3s ease-in-out;
        }
        .radio-btn .check-icon .icon::after{
            content: "";
            width: 100%;
            height: 3px;
            background-color: #fff;
            position: absolute;
            left: 0;
            bottom: 0;
            border-radius: 10px;
            box-shadow: 0 3px 5px rgba(0,0,0,0.231);
            transform: scaleX(0);
            transform-origin: left;
            transition: all 0.3s ease-in-out;
        }
        .custom-radio input:checked + .radio-btn{
            border: 6px solid #7434c3;
        }
        .custom-radio input:checked + .radio-btn .check-icon{
            background-color: #7434c3;
        }
        .custom-radio input:checked + .radio-btn .check-icon .icon::before,
        .custom-radio input:checked + .radio-btn .check-icon .icon::after{
            transform: scale(1);
        }

    </style>
    <body>
        <div class="wrapper">
            <h1 class="text-center mt-4">Single Voting Form</h1>
            <div class="radio-buttons flex-row flex-wrap justify-content-around">
                <form action="editVote" method="post">
                    <input type="text" name="Cid" value="${c.getId()}" hidden>
                    <input type="text" name="Rule" value="${c.getRuleId()}" hidden>
                    <h5 class="card-title mb-4" style='color:#fff'>Choose your preferences</h5>
                    <div class="row justify-content-center">
                        <c:forEach var="candidate" items="${candidates}">
                            <div class="col-md-3 col-sm-6 mb-4">
                                <label class="custom-radio d-block text-center">
                                    <input type="radio" name="choice" value="${candidate.getVotingObjectId()}" ${candidate.getVotingObjectId() == v.getVoId() ?'checked':''}>
                                    <div class="radio-btn">
                                        <div class="content">
                                                <div class="candidate-img">
                                                    <img src="${candidate.getImgPath()}" alt="Candidate Image">
                                                </div>
                                                <div>
                                                    <h2>${candidate.getVotingObjectName()}</h2>
                                                    <p>${candidate.getVotingObjectDescription()}</p>
                                                </div>
                                            <span class="check-icon">
                                                <span class="icon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-sm-6">
                            <button onclick="history.back()" class="btn btn-primary btn-block mt-4">Back To Campaign</button>
                        </div>
                        <div class="col-sm-6">
                            <button type="submit" class="btn btn-primary btn-block mt-4">Submit Vote</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>






