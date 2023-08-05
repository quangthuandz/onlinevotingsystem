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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="override.css">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
            <a class="navbar-brand bg-primary px-4 py-3" href="admin">electriq</a>
            <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                +
            </button>
            <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ">
                </ul>
                <ul class="navbar-nav ms-auto me-0 me-lg-3 ">
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="admin_dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="search_user">User Management</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="logout">Log out</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Header -->
        <header class="py-7 text-center text-white bg-trees">
            <div class="container-fluid">
                <p class="lead fw-normal">"Learn to code with the wisest master!"</p>
            </div>
        </header>

        <!-- Dashboard -->
        <section class="py-5">
            <div class="container-fluid px-4">
                <h2 class="display-4 text-center">Dashboard</h2>

                <!-- Count User and Campaign -->
                <div class="row justify-content-center py-5">
                    <div class="col-sm-6 col-xl-3">
                        <div class="card mb-4">
                            <div class="card-body bg-success">
                                <div class="d-flex justify-content-center">
                                    <div>
                                        <h3 style="color: white">Users</h3>
                                        <div style="color: white" class="text-large text-center">${countUser}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3">
                        <div class="card mb-4">
                            <div class="card-body bg-warning">
                                <div class="d-flex justify-content-center">
                                    <div>
                                        <h3 style="color: white">Campaigns</h3>
                                        <div style="color: white" class="text-large text-center">${countCampaign}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3">
                        <div class="card mb-4">
                            <div class="card-body bg-info">
                                <div class="d-flex justify-content-center">
                                    <div>
                                        <h3 style="color: white">Threads</h3>
                                        <div style="color: white" class="text-large text-center">${countThread}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3">
                        <div class="card mb-4">
                            <div class="card-body bg-primary">
                                <div class="d-flex justify-content-center">
                                    <div>
                                        <h3 style="color: white">Comments</h3>
                                        <div style="color: white" class="text-large text-center">${countComment}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Display All Campaigns -->
                <div class="card">
                    <div class="card-body p-0">
                        <div class="d-flex justify-content-between m-4">
                            <h4 class="heading mb-0">All Campaigns</h4>
                            <button class="btn btn-primary" onclick="exportCampaignReport()">Export Report</button>
                        </div>
                        <div>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Campaign Name</th>
                                        <th>Host</th>
                                        <th>Start Date</th>
                                        <th>End Date</th>
                                        <th>Public</th>
                                        <th>Status</th>
                                        <th>Participants</th>
                                        <th>Threads</th>
                                        <th>Comments</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${paginatedList}" var="dc" varStatus="loop">
                                        <tr>
                                    <input type="hidden" name="cid" value="${c.getId()}">
                                            <td>${loop.index + 1}</td>
                                            <td>${dc.getName()}</td>
                                            <td>${a.getNameById(dc.getCreatedBy())}</td>
                                            <td>${dc.getStartTime()}</td>
                                            <td>${dc.getEndTime()}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${dc.isIsPublic()}">
                                                        <input type="checkbox" checked disabled>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" disabled>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${dc.getStatus()}</td>
                                            <td>${ca.countVoterinCampaign(dc.getId())}</td>
                                            <td>${t.countThreadInCampaign(dc.getId())}</td>
                                            <td>${cp.countCommentInThread(dc.getId())}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- Paging Navigation -->
                            <section class="section rounded-3 text-black text-center">
                                <c:if test="${pageNumber > 1}">
                                    <a href="AdminDashboard?id=${c.getId()}&pageNumber=${pageNumber - 1}&pageSize=${pageSize}">Previous</a>
                                </c:if>
                                <c:forEach begin="1" end="${totalPages}" step="1" varStatus="i">
                                    <c:choose>
                                        <c:when test="${i.index == pageNumber}">
                                            <strong>${i.index}</strong>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="AdminDashboard?id=${c.getId()}&pageNumber=${i.index}&pageSize=${pageSize}">${i.index}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${pageNumber < totalPages}">
                                    <a href="AdminDashboard?id=${c.getId()}&pageNumber=${pageNumber + 1}&pageSize=${pageSize}">Next</a>
                                </c:if>
                            </section>
                        </div>
                    </div>
                </div>

                <!-- display top user have most interact -->
                <div class="card mt-4">
                    <div class="card-body">
                        <div class="d-flex justify-content-between m-4">
                            <h4 class="heading mb-0">Top 10 Users with Interactions</h4>
                            <button class="btn btn-primary" onclick="exportUserReport()">Export Report</button>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>User</th>
                                    <th>Thread</th>
                                    <th>Comment</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dataTopUser}" var="user" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${user.getUsername()}</td>
                                        <td>${user.getThreadCount()}</td>
                                        <td>${user.getCommentCount()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <script>
                                function exportCampaignReport() {
                                    window.location.href = "ExportCampaignReport";
                                }
                                function exportUserReport() {
                                    window.location.href = "ExportUserReport";
                                }
        </script>
    </body>
</html>
