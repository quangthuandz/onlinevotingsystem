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
        <link rel="stylesheet" href="fontawesome-free-6.3.0-web/css/all.min.css" type="text/css" />
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <style>
            .nav-item:hover {
                border-top: 1px solid white;
            }
            .popup-container {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                width: 350px;
                padding: 20px;
                text-align: center;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
            }

            .popup-container img {
                display: block;
                margin: 10px auto;
                max-width: 100%;
                height: auto;
                position: relative;
            }

            .image-container {
                position: relative;
            }

            .content {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                background-color: rgba(0, 0, 0, 0.5);
                color: white;
                text-align: center;
            }

            .content h2, .content h3 {
                margin: 0;
                padding: 10px;
                background-color: rgba(0, 0, 0, 0);
            }

            .content a {
                color: white;
                text-decoration: none;
            }

            .content a:before {
                content: "------";
            }

            .content h5 {
                color: white;
                opacity: 1;
            }

            .popup-container .close-button {
                position: absolute;
                top: 10px;
                right: 10px;
                cursor: pointer;
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

            /* Notification button styles */
            .notification-button {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }

            .notification-badge {
                position: absolute;
                top: -5px;
                right: -5px;
                width: 18px;
                height: 18px;
                background-color: red;
                border-radius: 50%;
                color: #fff;
                font-size: 12px;
                font-weight: bold;
                text-align: center;
                line-height: 18px;
            }

            /* Modal styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 500px;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
            #nav{
                list-style:none;
                margin: 0px;
                padding: 0px;
            }
            #nav li {
                float: left;
                margin-right: 20px;
                font-size: 14px;
                font-weight:bold;
            }
            #nav li a{
                color:#333333;
                text-decoration:none
            }
            #nav li a:hover{
                color:#006699;
                text-decoration:none
            }



        </style>
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
                <ul class="navbar-nav border-top border-lg-0">
                </ul>

                <ul class="navbar-nav ms-auto px-5">

                    <!----Notification---->
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

                    <div class="dropdown mx-5">
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
                        <!--                        <button type="button" class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown">
                                                    Menu
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li >
                                                        <a id="createButton" class="dropdown-item " href="setup" >
                                                            Create new Campaign
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
                                                </ul>-->
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

        <!-- Featured - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <section class="py-5">
            <div class="container-fluid px-4">
                <div class="row my-4">
                    <div class="col py-2">
                        <section class="row py-2 text-center bg-light" style="display: flex;justify-content: space-around;">
                            <c:set var="currentTime" value="<%= new java.sql.Timestamp(System.currentTimeMillis()) %>"/>
                            <div class = "row"">
                                <h2 class="display-4 text-center">Host</h2>
                                <c:forEach items="${host}" var="item">
                                    <div class="card py-4" style="width: 18rem;margin-top: 10px; margin-left: 50px; ">
                                        <img src="${item.getImg()}" class="card-img-top img-thumbnail" alt="...">
                                        <button id="pinBtn_${item.getId()}" class="button-pin <c:if test="${item.isPinned(account.getId(), item.getId())}">pinned</c:if><c:if test="${item.isPinned(account.getId(), item.getId()) == false}">unpinned</c:if>" onclick="pin(${account.getId()}, ${item.getId()})">
                                                <i class="fa-sharp fa-solid fa-thumbtack"></i>
                                            </button>
                                            <div class="card-body">
                                                    <h5 class="card-title">${item.getName()}</h5>
                                            <c:if test="${currentTime.before(item.getEndTimeByid(item.getId()))}">
                                                <p>${item.getStartTime()}</p>To<br><br>
                                                <p>${item.getEndTimeByid(item.getId())}</p>
                                            </c:if>
                                            <c:if test="${currentTime.after(item.getEndTimeByid(item.getId()))}">
                                                <h2 style="color: white;background-color: red">
                                                    Campaign Ended!
                                                </h2>
                                            </c:if>
                                            <a href="ViewCampaignDetails?id=${item.getId()}" class="btn btn-primary">View</a>
                                            <a href="hostdashboard?id=${item.getId()}" class="btn btn-primary">Dashboard</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class = "row">
                                <h2 class="display-4 text-center">Join</h2>
                                <c:forEach items="${join}" var="item">
                                    <div class="card py-4" style="width: 18rem;margin-top: 10px; margin-left: 50px; ">
                                        <img src="${item.getImg()}" class="card-img-top img-thumbnail" alt="...">
                                        <button id="pinBtn_${item.getId()}" class="button-pin <c:if test="${item.isPinned(account.getId(), item.getId())}">pinned</c:if><c:if test="${item.isPinned(account.getId(), item.getId()) == false}">unpinned</c:if>" onclick="pin(${account.getId()}, ${item.getId()})">
                                                <i class="fa-sharp fa-solid fa-thumbtack"></i>
                                            </button>
                                            <div class="card-body">
                                                    <h5 class="card-title">${item.getName()}</h5>
                                            <c:if test="${currentTime.before(item.getEndTimeByid(item.getId()))}">
                                                <p>${item.getStartTime()}</p>To<br><br>
                                                <p>${item.getEndTimeByid(item.getId())}</p>
                                            </c:if>
                                            <c:if test="${currentTime.after(item.getEndTimeByid(item.getId()))}">
                                                <h2 style="color: white;background-color: red">
                                                    Campaign Ended!
                                                </h2>
                                            </c:if>
                                            <a href="ViewCampaignDetails?id=${item.getId()}" class="btn btn-primary">View</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class = "row">
                                <h2 class="display-4 text-center">No join</h2>
                                <c:forEach items="${unjoin}" var="item">
                                    <div class="card py-4" style="width: 18rem;margin-top: 10px; margin-left: 50px; ">
                                        <img src="${item.getImg()}" class="card-img-top img-thumbnail" alt="...">
                                        <button id="pinBtn_${item.getId()}" class="button-pin <c:if test="${item.isPinned(account.getId(), item.getId())}">pinned</c:if><c:if test="${item.isPinned(account.getId(), item.getId()) == false}">unpinned</c:if>" onclick="pin(${account.getId()}, ${item.getId()})">
                                                <i class="fa-sharp fa-solid fa-thumbtack"></i>
                                            </button>
                                            <div class="card-body">
                                                    <h5 class="card-title">${item.getName()}</h5>
                                            <c:if test="${currentTime.before(item.getEndTimeByid(item.getId()))}">
                                                <p>${item.getStartTime()}</p>To<br><br>
                                                <p>${item.getEndTimeByid(item.getId())}</p>
                                            </c:if>
                                            <c:if test="${currentTime.after(item.getEndTimeByid(item.getId()))}">
                                                <h2 style="color: white;background-color: red">
                                                    Campaign Ended!
                                                </h2>
                                            </c:if>
                                            <a href="ViewCampaignDetails?id=${item.getId()}" class="btn btn-primary">View</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </section>

        <!-- Footer - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->



        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
        <script>
                                            // Lấy đối tượng nút "create" và màn hình pop-up
                                            const createButton = document.getElementById("createButton");
                                            const popupContainer = document.getElementById("popupContainer");

                                            // Xử lý sự kiện khi click vào nút "create"
                                            createButton.addEventListener("click", function () {
                                                // Hiển thị màn hình pop-up
                                                popupContainer.style.display = "block";
                                            });

                                            // Xử lý sự kiện khi click vào dấu "X"
                                            const closeButton = document.querySelector(".close-button");
                                            closeButton.addEventListener("click", function () {
                                                // Ẩn màn hình pop-up khi click vào dấu "X"
                                                popupContainer.style.display = "none";
                                            });

                                            // For pin
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