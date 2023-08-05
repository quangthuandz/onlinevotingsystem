<%-- 
    Document   : SetupCampaign
    Created on : Jun 14, 2023, 10:24:33 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                color: #3366ff;
                font-weight: bold;
            }

            .guide{
                margin-top: 20px;
                background-color: #99ccff ;
                padding: 10px;
                border-left: 2px solid #007bff;
            }

            .form{
                margin-top: 20px;
            }



            .mb-3{
                width: 500px;
            }



            img {
                width: 100%;
                max-height: 200px;
                object-fit: cover;
                margin-bottom: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .voteimage {
                font-weight: 600;
                cursor: pointer;
                color: #fff;
                border-radius: 8px;
                padding: 10px 20px;
                background-color: #007bff;
                transition: background-color 0.3s ease;
            }

            .voteimage:hover {
                background-color: #0056b3;
            }

            input[type="file"] {
                display: none;
            }

            .time{
                width:45px;
            }

            .counttime {
                display: flex;
                align-items: center;
            }

            .time-inputs {
                display: flex;
                align-items: center;
                margin-left: 10px; /* Điều chỉnh khoảng cách giữa các phần tử */
            }

            .time-inputs input,
            .time-inputs span {
                margin-right: 5px; /* Điều chỉnh khoảng cách giữa các input và span */
            }

            .content .preview {
                margin-bottom: 5px;
            }

            .content .voteimage {
                font-size: 12px;
                padding: 5px 10px;
            }

            .field{
                font-weight: bold;
                color: #333333;
            }

            .password-container {
                margin: 10px;
            }

            .password-container label {
                display: block;
            }

            .password-container input {
                width: 200px;
                margin-bot: 5px;
            }

            .password-container label {
                font-size: 16px;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .password-container input[type="password"] {
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            .popup-container {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                z-index: 9999;
                visibility: hidden; /* Ẩn ban đầu */
                border-radius: 10px;
            }

            /* CSS cho phần nền xung quanh màn hình popup */
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 9998;
                visibility: hidden; /* Ẩn ban đầu */
            }

            .popup-list {
                list-style-type: disc;
                padding-left: 20px;
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
                        <li class="breadcrumb-item"><a href="#">Set Up</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Details</li>
                        <li class="breadcrumb-item active" aria-current="page">Review</li>
                    </ol>
                    </nav>
                </div>
            </div>

            <div class="guide">
                <p><a href="#">Take a Tour</a> to help you complete the meeting vote details. Create 1 vote for each of your meeting items so votes can occur at different times during the meeting and so the voting results are presented separately</p>
            </div>

            <form action="setup" method="post" enctype="multipart/form-data">
                <div class="form">
                    <h3>Campaign Details</h3>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label field" style="">Campaign title</label>
                        <input type="text" class="form-control" placeholder="Example: School Campaign" name="title" value="${nameText}" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label field">Campaign Description</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description" required>${descriptionText}</textarea>
                    </div>
                    <label for="exampleFormControlInput1" class="form-label field" style="">Campaign Image</label>
                </div>


                <div class="container">
                    <div class="preview">
                        <img id="img-preview" src="images/vote.jpg" style="max-height: 244px;max-width: 293px;"/>
                    </div>
                </div>
                <label class="voteimage" for="file-input">Upload Image</label>
                <input accept="image/*" type="file" id="file-input" name="image" />
                <br>
                <br>
                <label class="field">Campaign time</label>
                <div class="counttime">
                    The campaign ends after 
                    <input type="text" class="form-control time" id="exampleInputEmail1" placeholder="00" name="day" style="margin: 7px" required>:
                    <input type="text" class="form-control time" id="exampleInputEmail1" placeholder="00" name="hour" style="margin: 7px" required>:
                    <input type="text" class="form-control time" id="exampleInputEmail1" placeholder="00" name="minute" style="margin: 7px" required>:
                    <input type="text" class="form-control time" id="exampleInputEmail1" placeholder="00" name="second" style="margin: 7px" required>
                    days : hours : minutes : seconds
                </div>
                <p style="font-size: 12px; color: #999999" >The campaign will start immediately when you finish creating new campaign. <a href="#" id="openPopup">Check time format here</a></p>
                <p style="color: red">${errorMessage}</p>

                <label for="exampleFormControlInput1" class="form-label field" style="">Vote Type</label>
                <div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="voteType" id="singleVote" value="1" checked>
                        <label class="form-check-label" for="singleVote">
                            Single Vote
                        </label>
                        <p style="font-size: 12px; color: #999999" >Each voter chooses only one option from a list and the option with the most votes wins</p>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="voteType" id="rankedVote" value="2">
                        <label class="form-check-label" for="rankedVote">
                            Ranked Vote
                        </label>
                        <p style="font-size: 12px; color: #999999" > Voters rank candidates in order of preference and determine winner based on highest score</p>
                    </div>
                </div>

                <div>
                    <label for="exampleFormControlInput1" class="form-label field" style="">Vote Security</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="voteSecurity" id="publicVote" value="1" checked>
                        <label class="form-check-label" for="publicVote">
                            Public
                        </label>
                        <p style="font-size: 12px; color: #999999">Everyone can join the campaign and discuss</p>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="voteSecurity" id="securityVote" value="2">
                        <label class="form-check-label" for="securityVote">
                            Private
                        </label>
                        <p style="font-size: 12px; color: #999999">Only users who you invite to the campaign with the password can join</p>
                    </div>
                    <div class="password-container">
                        <label for="passwordInput">Password:</label>
                        <input type="password" id="passwordInput" name="passwordInput" disabled>
                    </div>
                </div>



                <button type="submit" class="btn btn-primary" style="margin-top: 20px">Continue to option details</button>
            </form>

            <div class="popup-container" id="popup">

                <h2>Time Format</h2>
                <p>Follow below rules to make it easier</p>
                <ul class="popup-list">
                    <li>All fields must be greater than or equal to 0</li>
                    <li>The "minutes" and "seconds" fields must be in the range of 0 to 59</li>
                    <li>The "hours" field must be in the range of 0 to 23</li>
                    <li>All fields must be entered as digits</li>
                </ul>
                <button id="closePopup">Close</button>
            </div>

            <div class="overlay" id="overlay"></div>

        </div>

        <script>
            const input = document.getElementById('file-input');
            const image = document.getElementById('img-preview');

            input.addEventListener('change', (e) => {
                if (e.target.files.length) {
                    const src = URL.createObjectURL(e.target.files[0]);
                    image.src = src;
                }
            });

            const openPopup = document.getElementById('openPopup');
            const closePopup = document.getElementById('closePopup');
            const popup = document.getElementById('popup');
            const overlay = document.getElementById('overlay');

            openPopup.addEventListener('click', function (e) {
                e.preventDefault();
                popup.style.visibility = 'visible'; // Hiển thị popup
                overlay.style.visibility = 'visible'; // Hiển thị overlay
            });

            closePopup.addEventListener('click', function () {
                popup.style.visibility = 'hidden'; // Ẩn popup
                overlay.style.visibility = 'hidden'; // Ẩn overlay
            });

            const publicVote = document.getElementById("publicVote");
            const securityVote = document.getElementById("securityVote");
            const passwordInput = document.getElementById("passwordInput");

            function showPasswordInput() {
                passwordInput.disabled = false;
            }

            function hidePasswordInput() {
                passwordInput.disabled = true;
                passwordInput.value = "";
            }

            securityVote.addEventListener("change", function () {
                if (securityVote.checked) {
                    showPasswordInput();
                } else {
                    hidePasswordInput();
                }
            });

            publicVote.addEventListener("change", function () {
                if (publicVote.checked) {
                    hidePasswordInput();
                } else {
                    showPasswordInput();
                }
            });
        </script>
    </body>
</html>
