<%-- 
    Document   : HomePage
    Created on : May 11, 2023, 10:26:01 AM
    Author     : ducan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Campaign" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!doctype html>
<html lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Homepage - Voting online</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="override.css">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="fontawesome-free-6.3.0-web/css/all.min.css" type="text/css" />
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <style>
            .nav-item:hover {
                border-top: 1px solid white;
            }
            .button-pin {
                border: none;
                background: transparent;
                position: absolute;
                top: 0;
                right: 5px;
                font-size: 20px;
            }

            .pinned {
                color: blue;
            }

            .unpinned {
                color: gray;
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

        <script>
            $(document).ready(function () {
                // Updating the view with notifications using AJAX
                function loadUnseenNotification(view) {
                    $.ajax({
                        url: "/OnlineVotingSystem/notification",
                        method: "POST",
                        data: {view: view},
                        dataType: "json",
                        success: function (data) {

                            var notificationMenu = document.getElementById("notificationMenu");
                            notificationMenu.innerHTML = data.notifications;

                            if (data.unseen_notification > 0) {
                                $('.count').html(data.unseen_notification);
                            }
                            var notificationList = document.getElementById("notificationList");
                            notificationList.innerHTML = data.all;
                        }
                    });
                }

                loadUnseenNotification();

                setInterval(function () {
                    loadUnseenNotification();
                }, 5000);


                // Load new notifications
                $(document).on('click', '#notification_button', function () {
                    $('.count').html('');
                    loadUnseenNotification("yes");
                });
                // Get the modal element and the close button
                var modal = document.getElementById("notificationModal");
                var closeButton = document.getElementsByClassName("btn-close")[0];

                // Open the modal when the notification button is clicked
                $(document).on('click', '#notification_button', function () {
                    modal.style.display = "block";
                });

                // Close the modal when the close button is clicked
                closeButton.onclick = function () {
                    modal.style.display = "none";
                };

                // Close the modal when the user clicks anywhere outside of it
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                };

            });
        </script>
    </head>

    <body>
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
            <a class="navbar-brand bg-primary px-4 py-3" href="#">
                electriq
            </a>

            <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                +
            </button>

            <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ">
                </ul>
                <div class="mx-5">
                    <button type="button" class="btn btn-dark" id="notification_button" data-bs-toggle="modal">
                        <span class="position-relative">
                            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger count" style="border-radius:10px;"></span>
                            <span class="bi bi-bell" style="font-size:18px;"></span>
                        </span>
                    </button>
                </div>

                <div id="notificationModal" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" ></button>
                            </div>
                            <div class="modal-body">

                                <ul class="nav nav-tabs" id="myTab" role="tablist">
                                    <li class="nav-item" role="presentation">
                                        <button class="nav-link active" id="tab1-tab" data-bs-toggle="tab" data-bs-target="#tab1" type="button" role="tab" aria-selected="true">
                                            <span style="color:black">New Notifications</span>
                                        </button>
                                    </li>
                                    <li class="nav-item" role="presentation">
                                        <button class="nav-link" id="tab2-tab" data-bs-toggle="tab" data-bs-target="#tab2" type="button" role="tab" aria-selected="false">
                                            <span style="color:black">All Notification</span>
                                        </button>
                                    </li>
                                </ul>

                                <div class="tab-content" id="myTabContent">
                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
                                        <h3>New Notifications</h3>
                                        <ul id="notificationMenu"></ul>
                                    </div>
                                    <div class="tab-pane fade" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
                                        <h3>All Notifications</h3>
                                        <ul id="notificationList"></ul>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <ul class="navbar-nav ms-auto me-0 me-lg-3 ">
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3 " href="AdminDashboard" >Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3 " href="search_user" >User Management</a>
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
                <p class="lead fw-normal">"Learn to code with the wisest master!"</p>
            </div> .container 
            <p class="lead fw-normal"></p>
        </div><!-- .container -->
    </header>

    <!-- Featured - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <section class="py-5">
        <div class="row col container-fluid px-4">
            <h2 class="display-4 text-center">Your Campaign </h2>
            <form action="admin" method="post">
                <input type="text" name="campaign_search" style="border-radius: 20px; border: none" placeholder="Search campaign here...">

                <label for="sort_By">Sort By:</label>
                <select name="sort_By" id="sortBy" class="btn btn-outline-secondary dropdown-toggle col">
                    <option value="CampaignName">Name</option>
                    <option value="StartTime">Start Time</option>
                    <option value="EndTime">End Time</option>
                </select>

                <button class="campaign search oke btn btn-primary" style="border-radius: 20px">Search</button>
            </form>
            <form action="admin" method="post" class="col-md-12 mb-4">
                <div class="btn-group" role="group" aria-label="Filter campaigns">
                    <button type="submit" class="btn btn-primary filter-btn active" name="filter" value="all">All</button>
                    <button type="submit" class="btn btn-primary filter-btn" name="filter" value="available">Available</button>
                    <button type="submit" class="btn btn-primary filter-btn" name="filter" value="locked">Locked</button>
                </div>
            </form>
            <div class=" row my-4">
                <div class="col py-2 " >
                    <section class=" row py-2 text-center bg-light " style="display: flex;justify-content: space-around;">
                        <c:set var="currentTime" value="<%= new java.sql.Timestamp(System.currentTimeMillis()) %>"/>
                        <c:forEach items="${data}" var="item" >
                            <div class="col-md-4 card py-2" style="width: 18rem;margin-top: 10px;">
                                <img src="${item.getImg()}" class="card-img-top img-thumbnail" alt="..." >
                                <div class="card-body">
                                    <h5 class="card-title">${item.getName()}</h5>
                                    <c:if test="${currentTime.before(item.getEndTimeByid(item.getId()))}">
                                        <p>${item.getStartTime()}</p>To<br><br>
                                        <p>${item.getEndTimeByid(item.getId())}</p>
                                    </c:if>
                                    <c:if test="${currentTime.after(item.getEndTimeByid(item.getId()))}">
                                        <h2 style="color: white;background-color: red">
                                            Campaign Ended!
                                        </h2><br><br>
                                    </c:if>
                                    <a href="ViewCampaignDetails?id=${item.getId()}" class="btn btn-primary">View</a>
                                    <a href="admin?mod=remove&campaignId=${item.getId()}" class="btn btn-primary">Delete</a>
                                </div>
                            </div>
                        </c:forEach>
                    </section>
                </div><!-- .col -->
            </div><!-- .row -->
        </div><!-- .container -->
    </section> 

    <!-- Footer - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
    crossorigin="anonymous"></script>

    <!--Scipt for pin-->
    <script>
            function pin(userId, campaignId) {
                var pinBtn = document.getElementById("pinBtn_" + campaignId);
                if (pinBtn.classList.contains("pinned")) {
                    // User wants to unlike the post
                    unpin(userId, campaignId);
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "pin?action=pin&tuserId=" + userId + "&campaignId=" + campaignId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            pinBtn.classList.remove("unpinned");
                            pinBtn.classList.add("pinned");
                        }
                    };
                    xhr.send();
                }
            }
            function unpin(userId, campaignId) {
                var pinBtn = document.getElementById("pinBtn_" + campaignId);
                // User wants to unlike the post
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "pin?action=unpin&campaignId=" + campaignId + "&userId" + userId, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        pinBtn.classList.remove("pinned");
                        pinBtn.classList.add("unpinned");
                    }
                };
                xhr.send();
            }
    </script>
</body>

</html>
