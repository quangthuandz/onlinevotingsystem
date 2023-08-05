<%-- 
    Document   : CandidateDetail
    Created on : Jun 22, 2023, 6:32:53 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="popupOverlay"></div>
        <div id="popupContainer">
            <div class="row">
                <h3>Details</h3>
                <h4 id="popupInput"></h4>
                <div class="col-12 col-md-4">
                    <div class="container">
                        <div class="preview">
                            <label class="label-detail" for="file-input">Upload Image</label>
                            <input accept="image/*" type="file" id="file-input" onchange="previewImage(event)" />
                            <img class="avatar" id="img-preview" src="images/avatar.jsp" />
                            PNG or JPG
                        </div>
                    </div>
                </div>
                <div class="col-6 col-md-8">
                    <textarea></textarea>
                </div>
                <button type="button" class="add-detail-button"">Save</button>
            </div>
        </div>
    </body>
</html>
