<%-- 
    Document   : Detail
    Created on : Jun 16, 2023, 5:51:23 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.VotingObject" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="override.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="css/ChoiceList.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="path/to/your/javascript/file.js"></script>

        <style>
            .input-row {
                display: flex;
                flex-wrap: wrap;
                margin-top: 50px;
                margin-left: 30px;
            }

            .input-wrapper {
                margin-bottom: 20px;
                margin-right: 10px;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            #votersTable {
                width: 100%; /* Chiều rộng tổng thể của bảng */
            }

            th:first-child,
            td:first-child {
                width: 10%; /* Chiều rộng cột số thứ tự */
            }

            th:nth-child(2),
            td:nth-child(2) {
                width: 90%; /* Chiều rộng cột email */
            }

            .popup-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 9999;
            }

            .popup-content {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                padding: 40px;
                width: 800px;
                max-width: 90%;
                /* Cập nhật chiều dài dọc cho pop-up */
                max-height: 80%;
                overflow-y: auto;
                text-align: center;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            .popup-content .row {
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                justify-content: center;
            }

            .popup-content .col-12.col-md-4 {
                background-color: #F2E4F2;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                padding: 57px;
            }

            .popup-content .col-12.col-md-4 label {
                margin-bottom: 10px;
            }

            .popup-content .avatar {
                width: 100px;
                height: 100px;
                margin-bottom: 10px;
            }

            .popup-content .col-12.col-md-4 .upload-button {
                display: inline-block;
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .popup-content .col-12.col-md-4 .upload-button:hover {
                background-color: #45a049;
            }

            .popup-content .col-12.col-md-8 {

                display: flex;
                flex-direction: column;
                padding: 50px;
            }

            .popup-content .col-12.col-md-8 .form-group {
                display: flex;
                align-items: center;
            }

            .popup-content .col-12.col-md-8 h6 {
                margin: 0;
                margin-right: 10px;
                min-width: 100px; /* Độ rộng tối thiểu của thẻ h6 */
            }

            .popup-content .col-12.col-md-8 input,
            .popup-content .col-12.col-md-8 textarea {
                flex-grow: 1;
                margin: 13px;
                /* Để ô input và textarea có cùng độ rộng và cho chúng gần vào hai thẻ h6 */
            }

            .col-12.col-md-4 .upload-button {
                display: inline-block;
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease;
                margin-top: 20px;
            }

            /* Add hover effect for the "Upload Image" button */
            .col-12.col-md-4 .upload-button:hover {
                background-color: #45a049;
            }

            input[type="submit"],
            button[type="submit"] {
                background-color: #4CAF50;
                color: #fff;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
                margin-top: 50px;
            }

            input[type="submit"]:hover,
            button[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Nút Close */
            button[type="button"] {
                background-color: #f44336;
                color: #fff;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
                margin-left: 50px;
                margin-top: 50px;
            }

            button[type="button"]:hover {
                background-color: #d32f2f;
            }

            .button-dl {
                padding: 8px 20px;
                cursor: pointer;
                background-color: #5E90F2;
                color: #fff;
                border: none;
                border-radius: 4px;
            }

            button-dl:hover {
                background-color: #0459c5;
            }

            /* Styling for the file name display */
            #fileLabel {
                margin-left: 10px;
            }

            /* Styling for the "Download Template" button */
            #downloadTemplateButton {
                margin-top: 20px;
                background-color: #3DBE29;
            }

            #downloadTemplateButton:hover {
                background-color: #2f961e;
            }

            .button-ip {
                padding: 8px 20px;
                cursor: pointer;
                background-color: #00cc00;
                color: #fff;
                border: none;
                border-radius: 4px;
            }

            button-ip:hover {
                background-color: #0459c5;
            }

            #rowNumberInput {
                margin-top: 20px;
                padding: 8px 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
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
                        <li class="breadcrumb-item"><a style="color: #3366ff ; font-weight:bold "  href="#">Details</a></li>                       
                        <li class="breadcrumb-item active" aria-current="page">Review</li>
                    </ol>
                    </nav>
                </div>


            </div>
            <div class="gray-div flex-container" style="background-color: #B3C8F2;border-radius: 10px">
                <div class="gray-div-content">
                    <div class="container" style="background: white;border-radius: 10px;margin-top: 50px">
                        <h3 style="color: #056CF2;margin-top: 30px">First step: Add all candidates</h3>
                        <div class="row">
                            <div class="col-12 col-md-4">
                                <div class="option-add" style="margin-top: 40px">
                                    <form id="form-add" action="choicelist" method="post" enctype="multipart/form-data">

                                        Name <input type="text" name="objectname" style="margin: 13px;width: 210px" required/>
                                        Image<div class="option-infor" style="margin-top:12px">
                                            <div class="container">
                                                <div class="preview">
                                                    <label class="label-detail" for="file-input" style="margin-bottom: 10px">Upload Image</label>
                                                    <input accept="image/*" type="file" id="file-input" name="image" onchange="previewImage1(event)" />
                                                    <img class="avatar" id="img-preview" src="images/avatar.jsp" />
                                                    PNG or JPG
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                Description<textarea name="des" style="margin: 13px; width: 210px; height: 100px" required></textarea>
                                            </div>
                                        </div>
                                        <div class="container">
                                            <div id="inputContainer"></div>
                                            <input type="submit" id="addButton" value="Add" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-12 col-md-8">
                                <div class="input-row">
                                    <c:forEach items="${votingobject}" var="item">
                                        <div class="input-wrapper">
                                            <input type="text" class="form-control short-input" value="${item.getVotingObjectName()}" disabled />

                                            <a href="#popupOverlay?${item.getVotingObjectId()}" onclick="openPopup(${item.getVotingObjectId()})"><i class="fas fa-edit" style="color: black"></i></a>
                                            <a href="#" onclick="deleteQuestion(${item.getVotingObjectId()}); return false;"><i style="color: black" class="fas fa-trash-alt"></i></a>
                                        </div>
                                        <div class="popup-overlay" id="popupOverlay?${item.getVotingObjectId()}">
                                            <div class="popup-content">
                                                <form id="formEdit" method="post" action="updatecandidate?id=${item.getVotingObjectId()}" enctype="multipart/form-data">
                                                    <div class="row">
                                                        <h3>Edit Candidate</h3>

                                                        <div class="col-12 col-md-4">                                                                                                                                     
                                                            <img class="avatar" id="img-preview-${item.getVotingObjectId()}" src="${item.getImgPath()}" name="imgUpdate" />
                                                            <input style="display: none" accept="image/*" type="file" id="file-input-${item.getVotingObjectId()}" name="imgUpdate" onchange="previewImage(event, ${item.getVotingObjectId()})" />
                                                            <label class="label-detail" for="file-input-${item.getVotingObjectId()}" style="margin-bottom: 10px">Upload Image</label>
                                                        </div>
                                                        <div class="col-12 col-md-8" style="background-color: #B3C8F2">
                                                            <div class="form-group">
                                                                <h6>Name</h6>
                                                                <input type="text" name="nameUpdate" style="margin: 13px" value="${item.getVotingObjectName()}" required/>
                                                            </div>
                                                            <div class="form-group">
                                                                <h6>Description</h6>
                                                                <textarea name="desUpdate" style="margin: 13px;height: 100px" required>${item.getVotingObjectDescription()}</textarea>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <input type="submit" value="Save" onclick="showAlert()"/>
                                                    <button type="button" onclick="closePopup(${item.getVotingObjectId()})">Close</button>
                                                </form>

                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="background-color: white;border-radius: 10px;margin-top: 30px">
                        <c:if test="${voteSecurity=='2'}">
                            <h3 style="color: #056CF2;margin-left: 30px">Next step: Invite Voters</h3>
                            <h5 style="margin-left: 30px">By import excel</h5>
                            <button class="button-dl" style="margin:30px" onclick="downloadExcel()">Download Template</button>
                            <input style="margin-left: 50px" type="file" id="fileInput">
                            <button class="button-dl" style="margin-left: -50px" onclick="importExcel()">Import</button>

                            <br><br>
                            <h5 style="margin-left: 30px">Or by create manually</h5>
                            <input style="margin-top:20px;margin-left:30px" type="number" id="rowNumberInput" placeholder="Enter number of rows">
                            <button class="button-dl" onclick="createTable()">Create</button>
                            <br><br>
                            <table style="margin-left:30px;width: 700px" id="votersTable">
                                <tr>
                                    <th>No</th>
                                    <th>Email</th>
                                </tr>
                            </table>

                            <button class="button-dl" style="margin-top:30px;margin-left: 30px;margin-bottom: 30px" onclick="validateList()">Validate List</button>
                            <button class="button-ip" id="myButton" disabled onclick="sendEmail()">Invite</button>
                            <div id="inviteSuccess" style="color: green"></div>    
                        </c:if>
                    </div>

                    <div style="background-color: white;margin-top: 50px;border-radius: 10px;margin-bottom: 50px">
                        <h3 style="color: #056CF2;margin-left: 30px">Final step: Question for Campaign</h3>
                        <form id="questionForm" method="get" action="review">
                            <div class="mb-3">
                                <label for="exampleFormControlTextarea1" class="form-label" style="font-weight: bold;margin-left: 30px">Question</label>
                                <textarea style="margin-left: 30px;width: 700px" class="form-control question" id="exampleFormControlTextarea1" rows="3" required name="questionforcam"></textarea>
                            </div>
                            <input style="margin-left: 30px;background-color: #3333ff;margin-top: 20px;margin-bottom: 30px;" type="submit" class="btn btn-primary" value="Review"/>
                        </form>
                    </div>
                </div>






                <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
                <script>

                                // Function to preview the uploaded image
                                function previewImage1(event) {
                                    var input = event.target;
                                    var file = input.files[0];
                                    var imgPreview = input.parentNode.querySelector(".avatar");

                                    if (file) {
                                        var reader = new FileReader();
                                        reader.onload = function () {
                                            imgPreview.src = reader.result;
                                        };
                                        reader.readAsDataURL(file);
                                    } else {
                                        // Nếu không có tệp tin ảnh được chọn, đặt ảnh mặc định
                                        imgPreview.src = 'images/avatar.jpg';
                                    }
                                }

                                window.addEventListener('DOMContentLoaded', (event) => {
                                    var imgPreview = document.querySelector('.avatar');
                                    imgPreview.src = 'images/avatar.jpg';
                                });

                                function previewImage(event, candidateId) {
                                    var input = event.target;
                                    var file = input.files[0];
                                    var imgPreview = document.getElementById("img-preview-" + candidateId);

                                    if (file) {
                                        var reader = new FileReader();
                                        reader.onload = function () {
                                            imgPreview.src = reader.result;
                                        };
                                        reader.readAsDataURL(file);
                                    } else {
                                        // If no image file is selected, set a default image
                                        imgPreview.src = 'images/avatar.jpg';
                                    }
                                }

                                $(document).ready(function () {
                                    $('#form-add').submit(function (event) {
                                        event.preventDefault(); // Ngăn chặn việc gửi yêu cầu POST mặc định

                                        var formData = new FormData(this); // Tạo đối tượng FormData từ form

                                        $.ajax({
                                            url: '/OnlineVotingSystem/choicelist', // Lấy đường dẫn action từ thuộc tính action của form
                                            type: "POST", // Lấy phương thức method từ thuộc tính method của form
                                            data: formData,
                                            processData: false,
                                            contentType: false,
                                            success: function (response) {
                                                // Hiển thị thông báo thành công bằng SweetAlert
                                                Swal.fire({
                                                    icon: 'success',
                                                    title: 'Success',
                                                    text: 'Add successful!',
                                                    showConfirmButton: false,
                                                    timer: 1500
                                                }).then(function () {
                                                    // Tải lại trang
                                                    location.reload();
                                                });
                                            },
                                            error: function (xhr, status, error) {
                                            }
                                        });
                                    });
                                });

                                function updateLink(votingObjectId) {
                                    var url = 'choicelist';
                                    url += '?did=' + votingObjectId;
                                    window.location.href = url;
                                }


                                function deleteQuestion(questionId) {
                                    if (confirm('Bạn có chắc chắn muốn xóa câu hỏi này không?')) {
                                        var xhr = new XMLHttpRequest();
                                        xhr.onreadystatechange = function () {
                                            if (xhr.readyState === 4) {
                                                if (xhr.status === 200) {
                                                    // Hiển thị SweetAlert thông báo thành công
                                                    Swal.fire('Thành công', 'Câu hỏi đã được xóa', 'success').then(function () {
                                                        // Tải lại trang
                                                        location.reload();
                                                    });
                                                    // Cập nhật giao diện ngay tại đây nếu cần thiết
                                                } else {
                                                    // Xử lý lỗi nếu cần thiết
                                                }
                                            }
                                        };
                                        xhr.open('GET', 'deleteoption?voId=' + questionId, true);
                                        xhr.send();
                                    }
                                }


                                function importExcel() {
                                    const fileInput = document.getElementById("fileInput");
                                    const file = fileInput.files[0];

                                    if (file) {
                                        const reader = new FileReader();

                                        reader.onload = function (e) {
                                            const data = new Uint8Array(e.target.result);
                                            const workbook = XLSX.read(data, {type: "array"});
                                            const worksheet = workbook.Sheets[workbook.SheetNames[0]];
                                            const jsonData = XLSX.utils.sheet_to_json(worksheet, {header: 1});

                                            const table = document.getElementById("votersTable");

                                            // Xóa dữ liệu hiện tại trong bảng
                                            while (table.rows.length > 1) {
                                                table.deleteRow(1);
                                            }

                                            for (let i = 0; i < jsonData.length; i++) {
                                                const row = table.insertRow();
                                                const cell1 = row.insertCell(0);
                                                const cell2 = row.insertCell(1);

                                                cell1.innerHTML = i;
                                                cell2.contentEditable = true;

                                                const emailValue = jsonData[i][0];
                                                cell2.innerHTML = emailValue ? emailValue : "";
                                            }
                                        };

                                        reader.readAsArrayBuffer(file);
                                    }
                                }

                                function createTable() {
                                    const rowNumberInput = document.getElementById("rowNumberInput");
                                    const numRows = parseInt(rowNumberInput.value);

                                    if (!isNaN(numRows) && numRows >= 0) {
                                        const table = document.getElementById("votersTable");
                                        const currentRowCount = table.rows.length - 1;

                                        if (numRows > currentRowCount) {
                                            // Thêm hàng mới
                                            for (let i = currentRowCount + 1; i <= numRows; i++) {
                                                const row = table.insertRow();
                                                const cell1 = row.insertCell(0);
                                                const cell2 = row.insertCell(1);

                                                cell1.innerHTML = i;
                                                cell2.contentEditable = true;
                                            }
                                        } else if (numRows < currentRowCount) {
                                            // Xóa hàng thừa
                                            while (table.rows.length - 1 > numRows) {
                                                table.deleteRow(table.rows.length - 1);
                                            }
                                        }
                                    } else {
                                        alert("Please enter a valid number of rows.");
                                    }
                                }

                                function validateList() {
                                    const table = document.getElementById("votersTable");
                                    const emailList = [];

                                    for (let i = 1; i < table.rows.length; i++) {
                                        const emailCell = table.rows[i].cells[1];
                                        const email = emailCell.innerHTML.trim();

                                        if (email !== "") {
                                            emailList.push(email);
                                        }
                                    }

                                    if (emailList.length > 0) {
                                        $.ajax({
                                            type: "POST",
                                            url: '/OnlineVotingSystem/validatevoters',
                                            data: JSON.stringify(emailList),
                                            contentType: "application/json",
                                            success: function (response) {
                                                alert(response); // Hiển thị thông báo từ server

                                                // Xử lý kết quả từ server
                                                if (response === "All emails are valid") {
                                                    document.getElementById("myButton").disabled = false;
                                                } else {
                                                    document.getElementById("myButton").disabled = true;
                                                }
                                            },
                                            error: function (xhr, status, error) {
                                                console.log("Error status:", xhr.status); // Trạng thái của response
                                                console.log("Error message:", error); // Thông báo lỗi

                                                // Hiển thị thông báo lỗi từ server trong phần xử lý lỗi (error handling)
                                                alert(xhr.responseText);
                                                document.getElementById("errorContainer").innerText = xhr.responseText;
                                                document.getElementById("myButton").disabled = true;
                                            }
                                        });
                                    } else {
                                        alert("No emails to validate.");
                                    }
                                }


                                function sendEmail() {
                                    var xhr = new XMLHttpRequest();
                                    xhr.open("GET", "addvoter", true);
                                    xhr.send();
                                    var inviteSuccessElement = document.getElementById("inviteSuccess");
                                    inviteSuccessElement.innerText = "Invite success";
                                }

                                function openPopup(id) {
                                    document.getElementById("popupOverlay?" + id).style.display = "block";
                                }

                                function closePopup(id) {
                                    // Get the popup overlay element
                                    var popupOverlay = document.getElementById("popupOverlay?" + id);
                                    // Hide the popup by setting display to "none"
                                    popupOverlay.style.display = "none";
                                    window.history.pushState({}, document.title, window.location.href.split("#")[0]);
                                }

                                function showAlert() {
                                    // Gửi form thông qua AJAX để update dữ liệu.
                                    // Ở đây tôi sử dụng thư viện jQuery để đơn giản hóa quá trình gửi dữ liệu.
                                    $.ajax({
                                        url: $("#formEdit").attr("action"),
                                        method: "POST",
                                        data: new FormData($("#formEdit")[0]),
                                        processData: false,
                                        contentType: false,
                                        success: function (data) {
                                            // Thành công, hiển thị thông báo.
                                            alert("Update candidate successfully!");
                                        },
                                        error: function (error) {
                                            // Xử lý lỗi nếu cần.
                                            console.error("Error updating candidate:", error);
                                        }
                                    });
                                }

                                function downloadExcel() {
                                    window.location.href = '/OnlineVotingSystem/downloadexcel';
                                }

                </script>
                </body>
                </html>
