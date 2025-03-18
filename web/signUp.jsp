<%-- 
    Document   : signUp
    Created on : Dec 11, 2024, 12:51:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>cla</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

        <style>
            body {
                display: in;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .login-form button {
                font-weight: bold;
                color: white;
                background-color: royalblue;
                border: none;
                border-radius: 10px;
                padding: 10px 20px;
            }

            .login-form button a {
                color: white;
            }

            .small-button {
                padding: 5px 10px;
            }

            .large-button {
                width: 100%;
            }
        </style>
    </head>
    <!-- body -->
    <body class="main-layout inner_posituong computer_page">
        <!-- loader  -->
        <div class="loader_bg">
            <div class="loader"><img src="images/loading.gif" alt="#" /></div>
        </div>
        <!-- end loader -->
        <!-- header -->
        <header>
            <!-- header inner -->
            <div class="header">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                            <div class="full">
                                <div class="center-desk">
                                    <div class="logo">
                                        <a href="index.jsp"><img src="images/logo.png" alt="#" style="border-radius: 50%;"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
                            <nav class="navigation navbar navbar-expand-md navbar-dark ">
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarsExample04">
                                    <ul class="navbar-nav mr-auto">
                                        <form action="search" method="get">
                                            <li class="nav-link">                                             
                                                <input type="hidden" name="mode" value="query">
                                                <input type="text" name="name" value="" placeholder="Enter a product name...">
                                                <button type="submit">
                                                    <i class="fa fa-search" aria-hidden="true"></i>
                                                </button>
                                            </li>
                                        </form>

                                        <li class="nav-item active">
                                            <a class="nav-link" href="index.jsp">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="search?cid=1&mode=all">All Products</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="categories.jsp">Categories</a>
                                        </li>                                        
                                    </ul>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- end header inner -->
        <!-- end header -->

        <!-- laptop  section -->
        <div class="laptop" style="margin-bottom: 90px">
            <div class="container">
                <form action="signUp" method="get">
                    <div class="row" style="justify-content: center">                    
                        <div class="login-form" style="border: 5px solid black; border-radius: 10px; padding: 20px; width: 50%; background-color: wheat">
                            <p style="text-align: center; width: 100%; border-radius: 10px">
                                <input type="text" name="email" placeholder="   Email" value="${email}" style="width: 100%; border-radius: 10px"></p>
                            <p>&nbsp;</p>
                            <p style="text-align: center; width: 100%; border-radius: 10px">
                                <input type="text" name="realname" placeholder="   Real Name" value="${realname}" style="width: 100%; border-radius: 10px"></p>
                            <p>&nbsp;</p>
                            <p style="text-align: center; width: 100%; border-radius: 10px">
                                <input type="text" name="username" placeholder="   Username" value="${username}" style="width: 100%; border-radius: 10px"></p>
                            <p>&nbsp;</p>
                            <p style="text-align: center; width: 100%; border-radius: 10px">
                                <input type="password" name="password" placeholder="   Password" value="${password}" style="width: 100%; border-radius: 10px"></p>
                            <p>&nbsp;</p>
                            <p style="text-align: center; width: 100%; border-radius: 10px">
                                <input type="password" name="repassword" placeholder="   Confirm Password" value="${repassword}" style="width: 100%; border-radius: 10px"></p>
                            <p>&nbsp;</p>

                            <p>${err}</p>
                            <p>&nbsp;</p>
                            <p><button class="large-button" type="submit">Sign Up</button></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- end laptop  section -->

        <!--  footer -->
        <footer>
            <div class="footer">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
                            <img class="logo1" src="images/logo1.png" alt="#"/>
                            <ul class="social_icon">
                                <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin-square" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                            </ul>
                        </div>
                        <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
                            <h3>About Us</h3>
                            <ul class="about_us">
                                <li>dolor sit amet, consectetur<br> magna aliqua. Ut enim ad <br>minim veniam, <br> quisdotempor incididunt r</li>
                            </ul>
                        </div>
                        <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
                            <h3>Contact Us</h3>
                            <ul class="conta">
                                <li>dolor sit amet,<br> consectetur <br>magna aliqua.<br> quisdotempor <br>incididunt ut e </li>
                            </ul>
                        </div>
                        <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
                            <form class="bottom_form">
                                <h3>Newsletter</h3>
                                <input class="enter" placeholder="Enter your email" type="text" name="Enter your email">
                                <button class="sub_btn">subscribe</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="copyright">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <p>© 2019 All Rights Reserved. Design by<a href="https://html.design/"> Free Html Templates</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- end footer -->
        <!-- Javascript files-->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery-3.0.0.min.js"></script>
        <!-- sidebar -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>

