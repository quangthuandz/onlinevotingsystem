<%-- 
    Document   : CampaignStatisticsUser
    Created on : Jul 13, 2023, 3:11:52 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">-->
        <!--<link href="../css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="../bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>-->
        <style>
            .nav-item:hover {
                border-top: 1px solid white;
            }
            body{
                margin-top:20px;
                background:#FAFAFA;
            }
            .order-card {
                color: #fff;
            }

            .bg-c-blue {
                background: linear-gradient(45deg,#4099ff,#73b4ff);
            }

            .bg-c-green {
                background: linear-gradient(45deg,#2ed8b6,#59e0c5);
            }

            .bg-c-yellow {
                background: linear-gradient(45deg,#FFB64D,#ffcb80);
            }

            .bg-c-pink {
                background: linear-gradient(45deg,#FF5370,#ff869a);
            }


            .card {
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2.94px 0.06px rgba(4,26,55,0.16);
                box-shadow: 0 1px 2.94px 0.06px rgba(4,26,55,0.16);
                border: none;
                margin-bottom: 30px;
                -webkit-transition: all 0.3s ease-in-out;
                transition: all 0.3s ease-in-out;
            }

            .card .card-block {
                padding: 5px 25px;
            }

            .order-card i {
                font-size: 26px;
            }

            .f-left {
                float: left;
            }

            .f-right {
                float: right;
            }

            #countdown {
                display: flex;
                justify-content: center;
                align-items: center;
                font-family: Arial, sans-serif;
                font-size: 24px;
                color: #333;
            }

            .countdown-item {
                text-align: center;
                margin: 0 10px;
            }

            .countdown-item span {
                display: block;
                font-size: 48px;
                font-weight: bold;
            }

            .countdown-item span:last-child {
                font-size: 18px;
                font-weight: normal;
                margin-top: 5px;
            }
        </style>

    </head>
    <body>
        <!-- Navigation Bar -->
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

        <div class="container rounded mt-4 my-5">
            <!-- Featured Section -->
            <section id="featured-section" class="section rounded-3" style="margin-top: 80px">

                <!-- Campaign Details Card -->
                <div class="container py-5" style="display:flex;flex-direction: column;">
                    <div class="row">
                        <div class="col">
                            <nav aria-label="breadcrumb" class=" rounded-3 p-3 mb-4">
                                <ol class="breadcrumb mb-0">
                                    <li class="breadcrumb-item"><a href="userhome" style="text-decoration: none;">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Campaign Statistics</li>
                                </ol>
                            </nav>
                        </div>
                    </div><!--  -->
            </section>


            <!-- body -->
            <section class="body justify-content-between">
                <h1 class="text-center">Campaign Dashboard</h1>

                <!-- Countdown -->
                <div id="countdown">
                    <div class="countdown-item">
                        <span id="days">00</span>
                        <span>Days</span>
                    </div>
                    <div class="countdown-item">
                        <span id="hours">00</span>
                        <span>Hours</span>
                    </div>
                    <div class="countdown-item">
                        <span id="minutes">00</span>
                        <span>Minutes</span>
                    </div>
                    <div class="countdown-item">
                        <span id="seconds">00</span>
                        <span>Seconds</span>
                    </div>
                </div>

                <div class=" container d-flex justify-content-center">
                    <div class="p-2  col-md-3" style="margin-top: 45px">
                        <div class="card bg-c-green order-card p-2" style="margin-right: 10px">
                            <h5>My Vote</h5>
                            <c:forEach var="entry" items="${myCurrentVote}">
                                <div class="d-flex justify-content-between">
                                    <p>${entry.key}</p>
                                    <p>${entry.value}</p>
                                </div>
                            </c:forEach>
                            <c:if test="${!HasEnd}">
                                <a href="vote?id=${campaignId}" class="btn btn-facebook text-white"><b>Edit Vote</b></a>
                            </c:if>
                            <c:if test="${HasEnd}">
                                <a role="button" href="vote?id=${c.getId()}" class="btn btn-danger disabled position-absolute bottom-0 end-0 m-4" aria-disabled="true">
                                    Campaign had Ended</a>
                                </c:if>
                        </div>   
                    </div>

                    <div class="p-2" style="margin-top: 45px">
                        <!-- Card -->
                        <div class="card bg-c-blue order-card" >
                            <div class="card-block">
                                <h6 class="">Total Participants</h6>
                                <h2 class="text-right"><i class="fa fa-cart-plus f-left"></i><span>${noOfVotes.get(0).getValue()}</span></h2>
                                <p class="mb-0">Casted Votes<span class="f-right">${noOfVotes.get(0).getKey()}</span></p>
                            </div>
                        </div>
                        <!-- Card -->
                        <div class="card bg-c-pink order-card">
                            <div class="card-block">
                                <h6 class="m-b-20">Leading Option</h6>
                                <h2 class="text-right"><i class="fa fa-cart-plus f-left"></i><span>${leadingOption.get(0).getKey()}</span></h2>
                                <p class="mb-0">Points<span class="f-right">${leadingOption.get(0).getValue()}</span></p>
                            </div>
                        </div>
                    </div>

                    <div class="p-2">
                        <div class="card-body" style="max-width: 600px;">
                            <!-- Current Result Pie chart -->
                            <div class="chart-pie pt-4 pb-2" style="flex: 1;">
                                <canvas id="currentResultChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </section>



    </body>

    <script>
        var currentResult = JSON.parse('${currentResultJson}');
        var lablesSet = [];
        var dataSet = [];
        var backgroundColors = [];
        var hoverBackgroundColors = [];
        for (var item in currentResult)
        {
            if (currentResult.hasOwnProperty(item))
            {
                lablesSet.push(item);
                dataSet.push(currentResult[item]);
                var randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
                backgroundColors.push(randomColor);
                hoverBackgroundColors.push(randomColor);
            }
        }

        var ctx = document.getElementById("currentResultChart");
        var currentResultChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: lablesSet,
                datasets: [{
                        data: dataSet,
                        backgroundColor: backgroundColors,
                        hoverBackgroundColor: hoverBackgroundColors,
                        hoverBorderColor: "rgba(234, 236, 244, 1)"
                    }]
            }
        });


        //time count down
        function countdown() {
            const endDate = new Date("${endTime}"); // Đặt ngày kết thúc đếm ngược
            const now = new Date();
            const timeDiff = endDate - now;
            if (timeDiff <= 0) {
                // Cuộc đếm ngược đã kết thúc
                document.getElementById("countdown").innerHTML = "Campaign has ended!";
                return;
            }

            const days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
            const hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);
            document.getElementById("days").textContent = padNumber(days);
            document.getElementById("hours").textContent = padNumber(hours);
            document.getElementById("minutes").textContent = padNumber(minutes);
            document.getElementById("seconds").textContent = padNumber(seconds);
            setTimeout(countdown, 1000);
        }

        function padNumber(number) {
            return number.toString().padStart(2, "0");
        }

        countdown();
    </script>
</html>
