<%-- 
    Document   : ViewCampaignDetails1
    Created on : Jul 2, 2023, 7:11:17 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Campaign" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>electriq - campaign home</title>
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

            .section {
                padding: 20px;
                margin-bottom: 20px;
                background-color: rgba(68, 68, 68, 0.18);
                color: #fff;
            }/*
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800&display=swap");
            */
            body{
                background-color: #eee;
                font-family: "Poppins", sans-serif;
                font-weight: 300
            }
            .card{
                border:none
            }
            .ellipsis{
                color: #a09c9c
            }
            hr{
                color: #a09c9c;
                margin-top: 4px;
                margin-bottom: 8px
            }
            .muted-color{
                color: #a09c9c;
                font-size: 13px
            }
            .ellipsis i{
                margin-top: 3px;
                cursor: pointer
            }
            .icons i{
                font-size: 25px
            }
            .icons .fa-heart{
                color: red
            }
            .icons .fa-smile-o{
                color: yellow;
                font-size: 29px
            }
            .rounded-image{
                border-radius: 50%!important;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 50px;
                width: 50px
            }
            .name{
                font-weight: 600
            }
            .status small{
                margin-right: 10px;
            }
            .form-control{
                border-radius: 26px
            }
            .comment-input{
                position: relative
            }
            .fonts{
                position: absolute;
                right: 13px;
                top:8px;
                color: #a09c9c
            }
            .form-control:focus{
                color: #495057;
                background-color: #fff;
                border-color: #8bbafe;
                outline: 0;
                box-shadow: none
            }
            .likeBtn.liked {
                color: blue;
            }

            .dislikeBtn.disliked {
                color: red;
            }
        </style>
        <!--Script for Like and Dislike-->
        <script>
            function like(threadId) {
                var likeBtn = document.getElementById("likeBtn_" + threadId);
                var dislikeBtn = document.getElementById("dislikeBtn_" + threadId);
                var likeCountElement = document.getElementById("likeCountElement_" + threadId);
                var dislikeCountElement = document.getElementById("dislikeCountElement_" + threadId);


                if (likeBtn.classList.contains("liked")) {
                    // User wants to unlike the post
                    unlike(threadId);
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "like?action=like&threadId=" + threadId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            likeCountElement.textContent = response.likes;
                            dislikeCountElement.textContent = response.dislikes;
                            likeBtn.classList.add("liked");
                            dislikeBtn.classList.remove("disliked");
                        }
                    };
                    xhr.send();
                }
            }
            function dislike(threadId) {
                var likeBtn = document.getElementById("likeBtn_" + threadId);
                var dislikeBtn = document.getElementById("dislikeBtn_" + threadId);
                var likeCountElement = document.getElementById("likeCountElement_" + threadId);
                var dislikeCountElement = document.getElementById("dislikeCountElement_" + threadId);

                if (dislikeBtn.classList.contains("disliked")) {
                    // User wants to unlike the post
                    undislike(threadId);
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "like?action=dislike&threadId=" + threadId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            likeCountElement.textContent = response.likes;
                            dislikeCountElement.textContent = response.dislikes;
                            dislikeBtn.classList.add("disliked");
                            likeBtn.classList.remove("liked");
                        }
                    };
                    xhr.send();
                }
            }

            function unlike(threadId) {
                var likeBtn = document.getElementById("likeBtn_" + threadId);
                var likeCountElement = document.getElementById("likeCountElement_" + threadId);

                // User wants to unlike the post
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "like?action=unlike&threadId=" + threadId, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        likeCountElement.textContent = response.likes;
                        likeBtn.classList.remove("liked");
                    }
                };
                xhr.send();
            }

            function undislike(threadId) {
                var dislikeBtn = document.getElementById("dislikeBtn_" + threadId);
                var dislikeCountElement = document.getElementById("dislikeCountElement_" + threadId);

                // User wants to undislike the post
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "like?action=undislike&threadId=" + threadId, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        dislikeCountElement.textContent = response.dislikes;
                        dislikeBtn.classList.remove("disliked");
                    }
                };
                xhr.send();
            }
            function likeCmt(commentId, threadId) {
                var likeBtn = document.getElementById("likeCmtBtn_" + commentId);
                var dislikeBtn = document.getElementById("dislikeCmtBtn_" + commentId);
                var likeCountElement = document.getElementById("likeCountCmtElement_" + commentId);
                var dislikeCountElement = document.getElementById("dislikeCountCmtElement_" + commentId);

                if (likeBtn.classList.contains("liked")) {
                    // User wants to unlike the post
                    unlikeCmt(commentId);
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "like?action=likeCmt&commentId=" + commentId + "&threadId=" + threadId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            likeCountElement.textContent = response.likes;
                            dislikeCountElement.textContent = response.dislikes;
                            likeBtn.classList.add("liked");
                            dislikeBtn.classList.remove("disliked");
                        }
                    };
                    xhr.send();
                }
            }
            function dislikeCmt(commentId, threadId) {
                var likeBtn = document.getElementById("likeCmtBtn_" + commentId);
                var dislikeBtn = document.getElementById("dislikeCmtBtn_" + commentId);
                var likeCountElement = document.getElementById("likeCountCmtElement_" + commentId);
                var dislikeCountElement = document.getElementById("dislikeCountCmtElement_" + commentId);

                if (dislikeBtn.classList.contains("disliked")) {
                    // User wants to unlike the post
                    undislikeCmt(commentId);
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "like?action=dislikeCmt&commentId=" + commentId + "&threadId=" + threadId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            likeCountElement.textContent = response.likes;
                            dislikeCountElement.textContent = response.dislikes;
                            dislikeBtn.classList.add("disliked");
                            likeBtn.classList.remove("liked");
                        }
                    };
                    xhr.send();
                }
            }

            function unlikeCmt(commentId) {
                var likeBtn = document.getElementById("likeCmtBtn_" + commentId);
                var likeCountElement = document.getElementById("likeCountCmtElement_" + commentId);

                // User wants to unlike the post
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "like?action=unlikeCmt&commentId=" + commentId, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        likeCountElement.textContent = response.likes;
                        likeBtn.classList.remove("liked");
                    }
                };
                xhr.send();
            }

            function undislikeCmt(commentId) {
                var dislikeBtn = document.getElementById("dislikeCmtBtn_" + commentId);
                var dislikeCountElement = document.getElementById("dislikeCountCmtElement_" + commentId);

                // User wants to undislike the post
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "like?action=undislikeCmt&commentId=" + commentId, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        dislikeCountElement.textContent = response.dislikes;
                        dislikeBtn.classList.remove("disliked");
                    }
                };
                xhr.send();
            }
            // Xoa phan tu hien len thong bao va khong phai tai lai trang su dung Ajax request
            function deleteThread(event, threadId) {
                event.preventDefault();
                var threadSection = document.getElementById("thread_" + threadId);
                if (confirm("Bạn có chắc chắn muốn xóa bài viết này ?")) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "removeThread?action=delete&threadId=" + threadId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            threadSection.remove();
                        }
                    };
                    xhr.send();
                }
            }

        </script>
        <script>
            function show(threadId) {
                var divElement = document.getElementById('reply-input-' + threadId);

                if (divElement) {
                    divElement.style.display = "block";
                }
            }

            function threadOp(threadId) {
                var divElement = document.getElementById('thread_' + threadId);

                if (divElement) {
                    divElement.style.display = "block";
                }
            }

            function commentOp(commentId) {
                var divElement = document.getElementById('comment_' + commentId);

                if (divElement) {
                    divElement.style.display = "block";
                }
            }

        </script>
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
                                    <li class="breadcrumb-item active" aria-current="page">View Campaign Details</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div style="display:flex;justify-content: space-evenly;">
                        <img src="${c.getImg()}" alt="alt" class="img-fluid" style="height: 262.73px;max-width: 293px;"/> 
                        <div class="col-lg-8 text-black rounded-3">
                            <div class="card mb-4">
                                <div class="card-body position-relative py-3 px-4">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h3 class="mb-0">Name</h3>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${c.getName()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Start Time</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${c.getStartTime()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">End Time</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${c.getEndTime()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row mb-4">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Description</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${c.getDes()}</p>
                                        </div>
                                    </div>
                                    <c:if test="${!HasEnd}">
                                        <a href="vote?id=${c.getId()}" class="btn btn-primary position-absolute bottom-0 end-0 m-4">Vote</a>
                                    </c:if>
                                    <c:if test="${HasEnd}">
                                        <a role="button" href="vote?id=${c.getId()}" class="btn btn-danger disabled position-absolute bottom-0 end-0 m-4" aria-disabled="true">
                                            Campaign had Ended</a>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- End of Featured -->

            <!-- Candidates Section -->
            <section id="candidate-section row py-2 text-center bg-light" class="section rounded-3">
                <h1 class="text-center text-black">Candidates</h1>

                <!-- List of Candidates -->
                <section id="candidates-list" class="d-flex flex-row flex-wrap h-75 justify-content-around text-black">
                    <c:forEach items="${data}" var="v" >
                        <div class="card col-md-3 py-2 p-2 mx-1 my-3">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img class="img-fluid" src="${v.getImgPath()}" alt="${v.getVotingObjectName()} avatar"/>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">${v.getVotingObjectName()}</h5>
                                        <p class="card-text">${v.getVotingObjectDescription()}</p>
                                        <p class="card-text"><span class="badge bg-primary">${vote.getPointByCandidateId(v.getVotingObjectId())}</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <div class="text-center">
                    <a href="campaign_statistics?campaignId=${c.getId()}" class="btn btn-primary w-25 text-center">Campaign Statistics</a>
                </div>
            </section>  
            <!-- End of Candidates -->

            <!-- Posting Thread Section -->
            <section id="post-section" class="section rounded-3 text-black">
                <!-- Posting new thread -->
                <!--                <form action="ViewCampaignDetails" method="post" >
                                    <div class="form-floating">
                                        <textarea class="form-control" name="content" placeholder="What do you think?" style="height: 100px; resize: none;"></textarea>
                                        <label for="floatingTextarea2">What do you think?</label>
                                        <input type="hidden" name="Cid" value="${Cid}">
                                        <button class="btn-primary btn my-2">Post</button>
                                    </div>
                                </form>  -->
            </section>
            <!-- End of Thread -->

            <!-- Paging Navigation -->
            <section class="section rounded-3 text-black text-center">
                <c:if test="${pageNumber > 1}">
                    <a href="ViewCampaignDetails?id=${c.getId()}&pageNumber=${pageNumber - 1}&pageSize=${pageSize}">Previous</a>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" varStatus="i">
                    <c:choose>
                        <c:when test="${i.index == pageNumber}">
                            <strong>${i.index}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="ViewCampaignDetails?id=${c.getId()}&pageNumber=${i.index}&pageSize=${pageSize}">${i.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageNumber < totalPages}">
                    <a href="ViewCampaignDetails?id=${c.getId()}&pageNumber=${pageNumber + 1}&pageSize=${pageSize}">Next</a>
                </c:if>
            </section>

            <!-- Threads Section -->
            <c:forEach items="${dataThread}" var="Tdata">
                <c:if test="${Tdata.isIsDeleted() == false}">
                    <section id=thread_${Tdata.getThreadId()}" class="section rounded-3 text-black">
                        <div class="row d-flex align-items-center justify-content-center ">
                            <div class="">
                                <div class="card">
                                    <div class="d-flex justify-content-between p-2 px-3">
                                        <div class="d-flex flex-row align-items-center"> <img src="images\ava.png" width="50" class="rounded-circle">
                                            <div class="d-flex flex-column ml-2"> <span class="name font-weight-bold mx-2">${a.getNameById(Tdata.getAccountId())}</span>
                                                <small class="mr-2 mx-2 ellipsis">${Tdata.getTimeCreate()}</small> </div>
                                        </div>
                                        <div class="d-flex flex-row mt-1 ellipsis"> 
                                            <c:if test="${Tdata.getAccountId() == a.getId() || a.isIsAdmin()}"> 
                                                <i class="fa fa-ellipsis-h" onclick="threadOp(${Tdata.getThreadId()})">...</i> 
                                            </c:if>
                                        </div>
<!--                                        <form id="thread_${Tdata.getThreadId()}" action="EditThreadController" method="get" style="display: none">
                                            <input type="hidden" name="threadId" value="${Tdata.getThreadId()}">
                                            <input type="hidden" name="Cid" value="${Cid}">
                                            <input type="hidden" name="content" value="${Tdata.getContent()}">
                                            <div>
                                                <button class="btn btn-primary btn-edit" style="margin-left: 180px">Edit</button>
                                                <button class="btn btn-primary delete-btn" onclick="deleteThread(event, ${Tdata.getThreadId()})">Delete</button>
                                            </div>
                                        </form>-->
                                    </div>

                                    <div class="p-2">
                                        <p class="text-justify">${Tdata.getContent()}</p>
                                    </div>

                                    <div>
                                        <c:set var="likeCount" value="${Tdata.getLikeByThreadId(Tdata.getThreadId())}" />
                                        <c:set var="dislikeCount" value="${Tdata.getDislikeByThreadId(Tdata.getThreadId())}" />
                                        <c:set var="threadId" value="${Tdata.getThreadId()}" />
                                        <a href="#!" style="text-decoration: none">
                                            <button id="likeBtn_${threadId}" onclick="like(${threadId})" class="likeBtn <c:if test="${Tdata.isLiked(accountId, Tdata.getThreadId())}">liked</c:if>">
                                                Like <span id="likeCountElement_${threadId}" >${likeCount}</span>
                                            </button>
                                        </a>
                                        <a href="#!" style="text-decoration: none">
                                            <button id="dislikeBtn_${threadId}" onclick="dislike(${threadId})" class="dislikeBtn <c:if test="${Tdata.isDisliked(accountId, Tdata.getThreadId())}">disliked</c:if>">
                                                Dislike <span id="dislikeCountElement_${threadId}" >${dislikeCount}</span>
                                            </button>
                                        </a>   
                                    </div>


                                    <hr>

                                    <!-- Comment section -->
                                    <div class="comments">
                                        <c:forEach items="${commentList}" var="comment">
                                            <c:if test ="${comment.getThreadId() == Tdata.getThreadId() && comment.getParentCommentId() == 0}">
                                                <div class="d-flex flex-row mb-2"> 
                                                    <img src="images\ava.png" alt="avatar" width="40" height="40" class="rounded-image">
                                                    <div class="d-flex flex-column ml-2 mx-2"> 
                                                        <span class="name">${a.getNameById(comment.getAccountId())}</span> 
                                                        <small class="comment-text">
                                                            ${comment.getContent()}
                                                            <c:set var="commentId" value="${comment.getCommentId()}" />
                                                            <c:set var="likeCountCmt" value="${comment.getLikeByCommentId(commentId)}" />
                                                            <c:set var="dislikeCountCmt" value="${comment.getDislikeByCommentId(commentId)}" />
                                                            <button id="likeCmtBtn_${commentId}" onclick="likeCmt(${commentId}, ${threadId})" style="border: none; background-color: transparent" class="likeBtn <c:if test="${comment.isLiked(accountId, commentId)}">liked</c:if>">Like <span id="likeCountCmtElement_${commentId}" >${likeCountCmt}</span></button>
                                                            <button id="dislikeCmtBtn_${commentId}" onclick="dislikeCmt(${commentId}, ${threadId})" style="border: none; background-color: transparent" class="dislikeBtn <c:if test="${comment.isDisliked(accountId, commentId)}">disliked</c:if>">Dislike <span id="dislikeCountCmtElement_${commentId}" >${dislikeCountCmt}</span></button>

                                                                <div class="col-md-3 ellipsis end-0"> 
                                                                <c:if test ="${a.getId() == comment.getAccountId()}">
                                                                    <i class="fa fa-ellipsis-h" onclick="commentOp(${comment.getCommentId()})">...</i> 
                                                                </c:if>
                                                            </div>

<!--                                                            <form id="comment_${comment.getCommentId()}" action="CommentController" method="post" style="display: none">
                                                                <input type ="hidden" name ="commentID" value = "${comment.getCommentId()}">
                                                                <input type="hidden" name="accountID" value="${a.getId()}">
                                                                <input type="hidden" name="threadID" value="${Tdata.getThreadId()}">
                                                                <input type="hidden" name="Cid" value="${Cid}">
                                                                <input type="text" class="form-control" name="EditContent">
                                                                <button type ="submit">Edit</button>
                                                            </form>-->


                                                        </small>
                                                        <small>${comment.getCreatedAt()}</small> 

                                                        <!-- Intereaction Comment -->
                                                        <div class="d-flex flex-row align-items-center status"> 

                                                            <!-- Reply button -->
                                                            <c:if test="${(a.getId() == comment.getAccountId()) || isHost}">        
<!--                                                                <form id="comment_${comment.getCommentId()}" action="deleteComment" method="post" >
                                                                    <input type ="hidden" name ="commentID" value = "${comment.getCommentId()}">
                                                                    <input type="hidden" name="accountID" value="${a.getId()}">
                                                                    <input type="hidden" name="Cid" value="${Cid}">
                                                                    <input type="hidden" name="CmtType" value="comment">
                                                                    <input type="hidden" name="threadID" value="${Tdata.getThreadId()}">
                                                                    <button type ="submit">Delete</button>
                                                                </form>-->
                                                            </c:if>
                                                            <small class="btn btn-link reply-btn" onclick="show(${comment.getCommentId()})">Reply</small>
                                                            <!--                                                            <form action="ReplyCommentController" method="post">
                                                                                                                            <input type="hidden" name="Cid" value="${Cid}">
                                                                                                                            <input type="hidden" name="threadID" value="${Tdata.getThreadId()}">
                                                                                                                            <input type="hidden" name="parentId" value=" ${comment.getCommentId()} ">
                                                                                                                            <div id="reply-input-${comment.getCommentId()}" style="display: none;">
                                                                                                                                <input type="text" name="replyContent" class="form-control" placeholder="Write a reply">
                                                                                                                                <button class="btn btn-primary submit-reply-btn">Reply</button>
                                                                                                                            </div>
                                                                                                                        </form>-->

                                                        </div>
                                                        <!-- End of Intereaction Comment -->

                                                        <!-- All comment replies -->
                                                        <c:forEach items="${commentList}" var="reply">
                                                            <c:if test="${reply.getParentCommentId() == comment.getCommentId()}">
                                                                <div class="d-flex flex-row mb-2"> 
                                                                    <img src="images\ava.png" alt="avatar" width="40" height="40" class="rounded-image">
                                                                    <div class="d-flex flex-column ml-2 mx-2"> 
                                                                        <span class="name">${a.getNameById(reply.getAccountId())}</span> 
                                                                        <small class="comment-text">${reply.getContent()}
                                                                            <c:set var="commentId" value="${reply.getCommentId()}" />
                                                                            <c:set var="likeCountCmt" value="${reply.getLikeByCommentId(commentId)}" />
                                                                            <c:set var="dislikeCountCmt" value="${reply.getDislikeByCommentId(commentId)}" />
                                                                            <button id="likeCmtBtn_${commentId}" onclick="likeCmt(${commentId}, ${threadId})" style="border: none; background-color: transparent" class="likeBtn <c:if test="${comment.isLiked(accountId, commentId)}">liked</c:if>">Like <span id="likeCountCmtElement_${commentId}" >${likeCountCmt}</span></button>
                                                                            <button id="dislikeCmtBtn_${commentId}" onclick="dislikeCmt(${commentId}, ${threadId})" style="border: none; background-color: transparent" class="dislikeBtn <c:if test="${comment.isDisliked(accountId, commentId)}">disliked</c:if>">Dislike <span id="dislikeCountCmtElement_${commentId}" >${dislikeCountCmt}</span></button>
                                                                            </small>
                                                                            <small>${reply.getCreatedAt()}</small> 
                                                                    </div>
                                                                </div>

                                                                <div class="d-flex flex-row align-items-center status"> 
                                                                    <c:if test="${(a.getId() == reply.getAccountId()) || isHost}">        
<!--                                                                        <form id="comment_${reply.getCommentId()}" action="deleteComment" method="post" >
                                                                            <input type ="hidden" name ="commentID" value = "${reply.getCommentId()}">
                                                                            <input type="hidden" name="accountID" value="${a.getId()}">
                                                                            <input type="hidden" name="Cid" value="${Cid}">
                                                                            <input type="hidden" name="CmtType" value="reply">
                                                                            <input type="hidden" name="threadID" value="${Tdata.getThreadId()}">
                                                                            <button type ="submit">Delete</button>
                                                                        </form>-->

                                                                    </c:if>

                                                                    <small class="btn btn-link reply-btn" onclick="show(${reply.getCommentId()})">Reply</small>
                                                                    <!--                                                                    <form action="ReplyCommentController" method="post">
                                                                                                                                            <input type="hidden" name="Cid" value="${Cid}">
                                                                                                                                            <input type="hidden" name="threadID" value="${Tdata.getThreadId()}">
                                                                                                                                            <input type="hidden" name="parentId" value=" ${reply.getCommentId()} ">
                                                                                                                                            <div id="reply-input-${reply.getCommentId()}" style="display: none;">
                                                                                                                                                <input type="text" name="replyContent" class="form-control" placeholder="Write a reply">
                                                                                                                                                <button class="btn btn-primary submit-reply-btn">Reply</button>
                                                                                                                                            </div>
                                                                                                                                        </form>-->
                                                                </div>

                                                            </c:if>
                                                        </c:forEach>

                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                        <!--                                         Place to comment 
                                                                                <form action="CommentController" method="post">
                                                                                    <div class="comment-input"> 
                                                                                        <input type="hidden" name="Cid" value="${Cid}">
                                                                                        <input type="hidden" name="accountID" value="${a.getId()}">
                                                                                        <input type="hidden" name="threadID" value="${Tdata.getThreadId()}">
                                                                                        <textarea class="form-control" name="Content" id="textAreaExample" rows="1" placeholder="Write a comment..." style="resize: none;"></textarea>
                                                                                        <div class="fonts"> <i class="fa fa-camera"></i> </div>
                                                                                        <button type="submit" class="btn btn-primary float-end my-1 mx-1">Comment</button>
                                                                                    </div>
                                                                                </form>-->

                                    </div>

                                </div>
                            </div>
                        </div>
                    </section>
                </c:if>
            </c:forEach>

            <!-- Paging Navigation -->
            <section class="section rounded-3 text-black text-center">
                <c:if test="${pageNumber > 1}"><a href="ViewCampaignDetails?id=${c.getId()}&pageNumber=${pageNumber - 1}&pageSize=${pageSize}">Previous</a></c:if>
                <c:forEach begin="1" end="${totalPages}" step="1" varStatus="i">
                    <c:choose>
                        <c:when test="${i.index == pageNumber}">
                            <strong>${i.index}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="ViewCampaignDetails?id=${c.getId()}&pageNumber=${i.index}&pageSize=${pageSize}">${i.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageNumber < totalPages}"><a href="ViewCampaignDetails?id=${c.getId()}&pageNumber=${pageNumber + 1}&pageSize=${pageSize}">Next</a></c:if>
            </section>
        </div>
    </body>
</html>
