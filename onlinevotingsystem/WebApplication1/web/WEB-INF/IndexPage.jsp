<%-- 
    Document   : HomePage
    Created on : May 11, 2023, 10:26:01 AM
    Author     : ducan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>electriq</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="override.css">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
    </head>
    <style>
        .nav-item:hover {
            border-top: 1px solid white;
        }
    </style>

    <body>
        <!-- Navigation Bar - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
            <a class="navbar-brand bg-primary px-4 py-3" href="index">
                electriq
            </a>
            <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                +
            </button>
            <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item" hover-class="border-top border-lg-0">
                        <a class="nav-link px-4 px-lg-2 py-3" href="#about">About us</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="#team">Our team</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="#feedback">Testimonials</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-4 px-lg-2 py-3" href="#contact">Contact</a>
                    </li>
                </ul>
            </div><!-- .navbar-collapse -->

        </div><!-- .navbar-collapse -->

        <div class="navbar-collapse collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto me-0 me-lg-3 ">
                <li class="nav-item">
                    <a class="nav-link px-4 px-lg-2 py-3" href="login">Log in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-4 px-lg-2 py-3" href="register">Register</a>
                </li>
            </ul>
        </div><!-- .navbar-collapse -->
    </nav>

    <!-- Header - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <header class="py-7 text-center text-white bg-trees">
        <div class="container-fluid">
            <p class="lead fw-normal">"Learn to code with the wisest master !"</p>
        </div><!-- .container -->
    </header>

    <!-- Featured - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <section class="py-5" id="about">
        <div class="bg-light">
            <div class="container py-5">
                <div class="row h-100 align-items-center py-5">
                    <div class="col-lg-6">
                        <h1 class="display-4">electriq</h1>
                        <p class="lead text-muted mb-0">Empower Your Voice, Energize Your Vote with <b><i>electriq!</i></b></p>
                    </div>
                    <div class="col-lg-6 d-none d-lg-block"><img src="https://bootstrapious.com/i/snippets/sn-about/illus.png" alt="" class="img-fluid"></div>
                </div>
            </div>
        </div>

        <div class="bg-white py-5">
            <div class="container py-5">
                <div class="row align-items-center mb-5">
                    <div class="col-lg-6 order-2 order-lg-1"><i class="fa fa-bar-chart fa-2x mb-3 text-primary"></i>
                        <h2 class="font-weight-light">User-Friendly Interface</h2>
                        <p class="font-italic text-muted mb-4">Cast your vote with ease and confidence.</p><a href="login" class="btn btn-light px-5 rounded-pill shadow-sm">Learn More</a>
                    </div>
                    <div class="col-lg-5 px-5 mx-auto order-1 order-lg-2"><img src="https://bootstrapious.com/i/snippets/sn-about/img-1.jpg" alt="" class="img-fluid mb-4 mb-lg-0"></div>
                </div>
                <div class="row align-items-center">
                    <div class="col-lg-5 px-5 mx-auto"><img src="https://bootstrapious.com/i/snippets/sn-about/img-2.jpg" alt="" class="img-fluid mb-4 mb-lg-0"></div>
                    <div class="col-lg-6"><i class="fa fa-leaf fa-2x mb-3 text-primary"></i>
                        <h2 class="font-weight-light">Real-Time Results</h2>
                        <p class="font-italic text-muted mb-4">Experience the thrill of real-time updates.<br>Stay informed and witness the power of collective decision-making.</p><a href="login" class="btn btn-light px-5 rounded-pill shadow-sm">Learn More</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="bg-light py-5 align-seft-center" id="team">
            <div class="container py-5">
                <div class="row mb-4">
                    <div class="col-lg-5">
                        <h2 class="display-4 font-weight-light">Our team</h2>
                        <p class="font-italic text-muted">Big things in here.</p>
                    </div>
                </div>

                <div class="row text-center ">
                    <!-- Team item-->
                    <div class="col">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0">Khanh Hoang</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->

                    <!-- Team item-->
                    <div class="col">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0">Toan Bui</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->

                    <!-- Team item-->
                    <div class="col">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0">Anh Duc</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->

                    <!-- Team item-->
                    <div class="col">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0">Vinh The</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->
                    <!-- Team item-->
                    <div class="col">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0">Thuan Quang</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->
                    <!-- Team item-->
                    <div class="col">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0">Hanh Dinh</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->
                </div>
            </div>
        </div>


    </section>

    <!-- Feedback - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <section class="py-5 " id="feedback">
        <footer class="container">
            <h2 class="display-4 text-center">People say _</h2>

            <div class="row justify-content-around mt-5 mb-3">
                <div class="col-md-5 ">
                    <blockquote class="blockquote p-3 font-italic bg-light">
                        <p>electriq really good!</p>
                        <footer class="blockquote-footer">W3club</footer>
                    </blockquote>
                </div><!-- .col -->

                <div class="col-md-5">
                    <blockquote class="blockquote p-3 font-italic bg-light">
                        <p>I love this website.</p>
                        <footer class="blockquote-footer">Albert English</footer>
                    </blockquote>
                </div><!-- .col -->

                <div class="col-md-5">
                    <blockquote class="blockquote p-3 font-italic bg-light">
                        <p>Everything we vote can be found in electriq.</p>
                        <footer class="blockquote-footer">Mozqito DevOps Network</footer>
                    </blockquote>
                </div><!-- .col -->

                <div class="col-md-5">
                    <blockquote class="blockquote p-3 font-italic bg-light">
                        <p>Amazing voting! Fake news don't even exist.</p>
                        <footer class="blockquote-footer">Isaac New York</footer>
                    </blockquote>
                </div><!-- .col -->

                <div class="col-md-5">
                    <blockquote class="blockquote p-3 font-italic bg-light">
                        <p>Awesome!!!</p>
                        <footer class="blockquote-footer">Nicola Tester</footer>
                    </blockquote>
                </div><!-- .col -->

                <div class="col-md-5">
                    <blockquote class="blockquote p-3 font-italic bg-light">
                        <p>Like others said: "This website is the best!"</p>
                        <footer class="blockquote-footer">Ada Lovely</footer>
                    </blockquote>
                </div>
            </div>
        </footer>
    </section>

    <!-- Subscription - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <section class="py-5 bg-light" id="contact">
        <div class="container">
            <h2 class="display-4 text-center">And you _</h2>

            <div class="row justify-content-around mt-5 mb-3">
                <div class="col-md-7">
                    <form class="p-3 p-md-5 bg-light border border-5">
                        <div class="lead text-center">
                            Unlock Your Voting Power <br>Join Electriq Today and Make Your Voice Count!
                        </div><!-- .lead -->
                        <div class="input-group mt-4" style="justify-content: center">
                            <span class="input-group-btn">
                                <a href="register" class="btn btn-secondary rounded-0">Join us</a>
                            </span>
                        </div><!-- register -->
                    </form>
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
</body>

</html>
