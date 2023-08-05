<%-- 
    Document   : JoinCampaign
    Created on : Jun 21, 2023, 4:15:42 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="css/styleLogin.css" rel="stylesheet" type="text/css"/>
        <title>electriq - log in</title>
        <script src="link">
            $(function () {
                $('input, select').on('focus', function () {
                    $(this).parent().find('.input-group-text').css('border-color', '#80bdff');
                });
                $('input, select').on('blur', function () {
                    $(this).parent().find('.input-group-text').css('border-color', '#ced4da');
                });
            });

        </script>
    </head>
    <body class="bg-light">
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0" style="background-color: #5f5f5f">
            <a class="navbar-brand bg-primary px-4 py-3" href="home">
                electriq
            </a>
        </nav>
        <!-- Navbar-->
        <header class="header">
            <nav class="navbar navbar-expand-lg navbar-light py-3">
                <div class="container">
                    <!-- Navbar Brand -->
                    <a href="#" class="navbar-brand">
                        <img src="https://bootstrapious.com/i/snippets/sn-registeration/logo.svg" alt="logo" width="150">
                    </a>
                </div>
            </nav>
        </header>

        <div class="card text-center" style="width: 500px; margin: auto">
            <div class="card-header h5  align-middle">Join to <b>${c.getName()}</b> Campaign</div>

            <div class="card-body px-5">
                <p class="card-text py-2">Enter Password</p>
                <form action="join" method="post">
                    <c:choose>
                        <c:when test="${empty error1}">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" name="password" placeholder="Password" value="${password}" required>
                                <label for="floatingInput">Password</label>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control is-invalid" id="floatingInput" name="password" placeholder="Password" value="${password}" required>
                                <label for="floatingInput">Password</label>
                                <div class="invalid-feedback">
                                    Incorrect Password.
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" id="cId" name="cId" value="${c.getId()}">
                    <!-- Submit Button -->
                    <div style="width: 100%">
                        <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                            <input type="submit" value="Join" class="font-weight-bold btn btn-primary btn-block py-2"  style="width: 100%;  font-weight: bold">
                        </div>
                    </div>
                </form>
            </div>
            ${msg}
    </body>

</form>
</body>
</html>
