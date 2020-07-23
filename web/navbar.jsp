<%-- 
    Document   : header
    Created on : Jul 11, 2020, 10:43:03 AM
    Author     : thuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top header">
            <div class="container">
                <a class="navbar-brand" href="#">Tsunami</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item ${(param.navActive.equals('home') || param.navActive == null) ?'active':''}">
                            <a class="nav-link" href="index.jsp?navActive=home">Home
                                <!--<span class="sr-only">(current)</span>-->
                            </a>
                        </li>

                        <%
                            if (session.getAttribute("acc") != null) {
                        %>

                        <li class="nav-item ${param.navActive.equals('myaccount')?'active':''}">
                            <a class="nav-link" href="index.jsp?navActive=myaccount&service=aboutme">My account</a>
                        </li>
                        <li class="nav-item ${param.navActive.equals('mycart')?'active':''}">
                            <a class="nav-link" href="index.jsp?navActive=myaccount&service=oncart">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">
                                <i class="fa fa-sign-out" aria-hidden="true"></i>
                            </a>
                        </li>

                        <%
                        } else {
                        %>

                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">
                                <i class="fa fa-sign-in" aria-hidden="true"></i>
                            </a>
                        </li>

                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>

        <script src="https://use.fontawesome.com/56a1666710.js"></script>
    </body>
</html>
