
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="css/styleLogin.css" rel="stylesheet" type="text/css"/>
        <title>electriq - forgotten password</title>
        
    </head>
    <body style="justify-content: center" class="bg-light">
        <!-- navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0" style="background-color: #5f5f5f">
            <a class="navbar-brand bg-primary px-4 py-3" href="index">
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
            <div class="card-header h5  align-middle" style ="background-color: blue">Password Reset</div>

            <div class="card-body px-5">

                <p class="card-text py-2">Enter your email address <br> and we'll send you an OTP to reset password</p>
                <form action="forgot_password" method="post">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" name="email" placeholder="Email Address">
                        <label for="floatingInput"> Email Address</label>
                    </div>

                    <!-- divide text -->
                    <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                        <div class="border-bottom w-100 mr-5"></div>
                    </div>

                    <!-- submit button -->
                    <div class="form-group col-lg-12 mx-auto mb-0 text-center py-3">
                        <input type="submit" value="Search" class="font-weight-bold btn btn-primary btn-block py-2"  style="width: 75%;  font-weight: bold">
                    </div>
                </form>
            </div>
        </div>


        <div>${resp}</div>
    </body>
</html>
