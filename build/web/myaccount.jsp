<%-- 
    Document   : container
    Created on : Jul 11, 2020, 10:41:45 AM
    Author     : thuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/avatarStyle.css">
    </head>
    <body>
        <div class="container container-tsunami">

            <div class="row">

                <div class="col-lg-3">
                    <div class="avatar-wrapper">
                        <img class="profile-pic" src="${sessionScope.acc.avatar}" />

                        <form action="editAvatar" method="post" id="myAvatarForm" enctype = "multipart/form-data">
                            <div class="upload-button">
                                <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                            </div>
                            <input class="file-upload" type="file" name="avatar"/>
                        </form>

                    </div>

                    <h1 class="my-4 text-center">${sessionScope.acc.name}</h1>

                    <div class="list-group">
                        <li class="list-group-item collapsed 
                            ${param.service.equals("aboutme") || param.service.equals("bankacc") || param.service.equals("alladdress") || param.service.equals("changepass") 
                              ?"active":""}" 
                            data-toggle="collapse" data-target="#profile-services">
                            My profile
                            <span class="arrow"></span>
                        </li>
                        <ul class="list-group p-0 mb-0 
                            ${param.service.equals("aboutme") || param.service.equals("bankacc") || param.service.equals("alladdress") || param.service.equals("changepass")
                              ?"":"collapse"}" 
                            id="profile-services">
                            <a href="index.jsp?navActive=myaccount&service=aboutme" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("aboutme") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> About me
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=bankacc" class="text-dark">

                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("bankacc") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Bank accounts
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=alladdress" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("alladdress") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> All address
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=changepass" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("changepass") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Change password
                                </li>
                            </a>
                        </ul>
                        <!--/.list-group aboutme-->

                        <li class="list-group-item collapsed 
                            ${param.service.equals("oncart") || param.service.equals("checkout") || 
                              param.service.equals("onship") || param.service.equals("received") || param.service.equals("feedbacked") 
                              ?"active":""}" 
                            data-toggle="collapse" data-target="#cart-services">
                            My cart
                            <span class="arrow"></span>
                        </li>
                        <ul class="list-group p-0 mb-0 
                            ${param.service.equals("oncart") || param.service.equals("checkout") || 
                              param.service.equals("onship") || param.service.equals("received") || param.service.equals("feedbacked") 
                              ?"":"collapse"}" 
                            id="cart-services">
                            <a href="index.jsp?navActive=myaccount&service=oncart" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("oncart") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> On Cart
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=checkout" class="text-dark">

                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("checkout") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Check Out
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=onship" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("onship") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> On shipping
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=received" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("received") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Received
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=feedbacked" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("feedbacked") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Feedbacked
                                </li>
                            </a>
                        </ul>
                        <!--./list-group mycart-->

                        <li class="list-group-item collapsed 
                            ${param.service.equals("shopinfo") || param.service.equals("allproducts") || param.service.equals("addproduct") 
                              ?"active":""}" 
                            data-toggle="collapse" data-target="#shop-services">
                            My shop
                            <span class="arrow"></span>
                        </li>
                        <ul class="list-group p-0 mb-0 
                            ${param.service.equals("shopinfo") || param.service.equals("allproducts") || param.service.equals("addproduct")
                              ?"":"collapse"}" 
                            id="shop-services">
                            <a href="index.jsp?navActive=myaccount&service=shopinfo" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("shopinfo") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Shop info
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=allproducts" class="text-dark">

                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("allproducts") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> All products
                                </li>
                            </a>
                            <a href="index.jsp?navActive=myaccount&service=addproduct" class="text-dark">
                                <li class="list-group-item border-top-0 
                                    ${param.service.equals("addproduct") ?"active":""}">
                                    <i class="fa fa-angle-double-right" aria-hidden="true"></i> Add new product
                                </li>
                            </a>
                        </ul>
                        <!--/.list-group myshop-->
                    </div>
                </div>
                <!-- /.col-lg-3 -->

                <div class="col-lg-9" style="padding: 2%">
                    <%
                        if ((request.getParameter("navActive").equals("myaccount") && request.getParameter("service") == null)
                                || request.getParameter("service").equals("aboutme")) {
                    %>

                    <jsp:include page="aboutme.jsp" />

                    <%
                    } else if (request.getParameter("service").equals("bankacc")) {
                    %>

                    <jsp:include page="bankacc.jsp" />

                    <%
                    } else if (request.getParameter("service").equals("alladdress")) {
                    %>

                    <jsp:include page="alladdress.jsp" />

                    <%
                    } else if (request.getParameter("service").equals("changepass")) {
                    %>

                    <jsp:include page="changepass.jsp" />

                    <%
                    } else if (request.getParameter("service").equals("shopinfo")) {
                    %>

                    <jsp:include page="shopinfo.jsp" />

                    <%
                    } else if (request.getParameter("service").equals("allproducts")) {
                    %>

                    <jsp:include page="allproducts.jsp" />

                    <%
                    } else if (request.getParameter("service").equals("addproduct")) {
                    %>

                    <jsp:include page="addproduct.jsp" />
                    
                    <%
                    } else if (request.getParameter("service").equals("oncart")) {
                    %>

                    <jsp:include page="oncart.jsp" />
                    
                    <%
                    } else if (request.getParameter("service").equals("checkout")) {
                    %>

                    <jsp:include page="checkout.jsp" />

                    <%
                        }
                    %>
                </div>
                <!-- /.col-lg-9 -->
            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <script src="https://use.fontawesome.com/56a1666710.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                var readURL = function (input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('.profile-pic').attr('src', e.target.result);
                        };

                        reader.readAsDataURL(input.files[0]);
                    }
                };

                $(".file-upload").on('change', function () {
                    readURL(this);
//                    console.log($(".file-upload"));
                    $("#myAvatarForm").submit();
                    readURL(this);
                });

                $(".upload-button").on('click', function () {
                    $(".file-upload").click();
                });
            });
        </script>
    </body>
</html>
