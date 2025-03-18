<%-- 
    Document   : product
    Created on : Dec 10, 2024, 11:48:04 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%
    Product p = (Product) request.getAttribute("p");
    ProductDAO pd = new ProductDAO();
    int sumQuantityOnHold = new CartDAO().getProductSumQuantity(p.getProductID()); // get on hold product w/o change db
    request.setAttribute("sumQuantityOnHold", sumQuantityOnHold);
%>
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
                                        <c:choose>
                                            <c:when test="${sessionScope.userAccount.getRole() == 'user'}">
                                                <li class="nav-item d_none">
                                                    <select id="menuSelect" style="text-align: center; appearance: none; background-color: transparent; border: none; color: white">
                                                        <option value="index.jsp" selected style="color: black; font-weight: bold">${sessionScope.userAccount.getRealName()}</option>
                                                        <!--<option value="changePassword.jsp" style="color: black; font-weight: bold">Change Password</option>-->
                                                        <option value="userPayment.jsp" style="color: black; font-weight: bold">View Order</option>
                                                        <option value="signOut?" style="color: black; font-weight: bold">Sign Out</option>
                                                    </select>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${sessionScope.userAccount.getRole() == 'admin'}">
                                                        <li class="nav-item d_none">
                                                            <select id="menuSelect" style="text-align: center; appearance: none; background-color: transparent; border: none; color: white">
                                                                <option value="index.jsp" selected style="color: black; font-weight: bold">${sessionScope.userAccount.getRealName()}</option>
                                                                <option value="productManage?" style="color: black; font-weight: bold">Manage Product</option>
                                                                <option value="userPayment.jsp" style="color: black; font-weight: bold">View Order</option>
                                                                <!--<option value="changePassword.jsp" style="color: black; font-weight: bold">Change Password</option>-->
                                                                <option value="signOut?" style="color: black; font-weight: bold">Sign Out</option>
                                                            </select>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="nav-item d_none">
                                                            <a class="nav-link" href="signIn.jsp">Sign In</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                        <script>
                                            const selectElement = document.getElementById('menuSelect');
                                            selectElement.addEventListener('change', (event) => {
                                                const selectedValue = event.target.value;
                                                // Thay thế 'https://example.com/' bằng URL tương ứng với mỗi option
                                                window.location.href = selectedValue;
                                            });

                                            window.onpopstate = function (event) {
                                                window.location.reload();
                                            };
                                        </script>                                        
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

        <!-- laptop1 -->
        <div class="laptop1">
            <div class="container" >
                <div class="row" style="width: 160%; margin-left: -30%">
                    <div class="col-md-6">
                        <div class="laptop1_img">
                            <figure><img src="${p.getImage()}" alt="#" style="max-width: 800px; height: auto"/></figure>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="titlepage" style="text-align: left">
                            <form action="addCart" method="get">
                                <h2 style="padding-top: 20px; text-align: center">${p.getProductName()}</h2>
                                <h3>Category: ${p.getCategoryName()}</h3>
                                <h3>Description: ${p.getCategoryDesc()}</h3>
                                <h3>In stock: ${p.getUnitsInStock() - sumQuantityOnHold}</h3>
                                <h3>Price: <del>${p.getUnitPrice()}₫</del> <strong>${p.getDiscountedPrice()}₫</strong> (Sales ${p.getDiscount()}%)</h3>
                                <c:choose>
                                    <c:when test="${p.getUnitsInStock() - sumQuantityOnHold > 0}">
                                        <p style="display: flex; justify-content: space-evenly">
                                            <input type="hidden" name="pid" value="${p.getProductID()}">
                                            <button onclick="decrementValue()" style="width: 5%; background-color: #48ca95">-</button> 
                                            <input type="number" name="quantity" id="quantity" value="1" style="width: 10%"> 
                                            <button onclick="incrementValue()" style="width: 5%; background-color: #48ca95">+</button> 
                                            <script>
                                                function incrementValue() {
                                                    event.preventDefault();
                                                    var value = document.getElementById('quantity').value;
                                                    value++;
                                                    document.getElementById('quantity').value = value;
                                                }
                                                function decrementValue() {
                                                    event.preventDefault();
                                                    var value = document.getElementById('quantity').value;
                                                    if (value > 1) {
                                                        value--;
                                                        document.getElementById('quantity').value = value;
                                                    }
                                                }
                                            </script>

                                            <button class="read_more" type="submit">Add to Cart</button>
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <h1 style="color: red; font-weight: bolder">Sold out</h1>
                                    </c:otherwise>
                                </c:choose>

                                <p>${err}</p>
                                <!--<p><a class="read_more" href="#">Buy now</a></p>-->
                            </form>

                            <!--                            <script>
                                                            function copyValue() {
                                                                let input1 = document.getElementById('quantity');
                                                                let input2 = document.getElementById('quantityOut');
                                                                input2.value = input1.value;
                                                            }
                                                        </script>-->
                            <c:choose>
                                <c:when test="${p.getUnitsInStock() - sumQuantityOnHold > 0}">
                                    <form action="orderDirectly" method="post">
                                        <input type="hidden" name="pid" value="${p.getProductID()}">
                                        <input type="hidden" name="quantity" id="quantityOut" value="">
                                        <button class="read_more" type="submit" onclick="
                                                var sourceValue = document.getElementById('quantity').value;
                                                document.getElementById('quantityOut').value = sourceValue;
                                                ">Buy now</button>
                                    </form>                                    
                                </c:when>                                   
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end laptop1 -->

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

