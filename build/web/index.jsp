<%-- 
    Document   : addproduct
    Created on : Jul 14, 2020, 1:26:54 PM
    Author     : thuy
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Tsunami</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/shop-homepage.css" rel="stylesheet">
        <link rel="stylesheet" href="css/tsunamiStyle.css">

    </head>

    <body>
        <!-- Navigation -->
        <jsp:include page="navbar.jsp" />

        <!-- Page Content -->
        <%            if (request.getParameter("navActive") == null || request.getParameter("navActive").equals("home") || session.getAttribute("acc") == null) {
        %>

        <jsp:include page="home.jsp" />

        <%
        } else if (request.getParameter("navActive").equals("myaccount")) {
        %>

        <jsp:include page="myaccount.jsp" />

        <%
            }
        %>

        <!-- Footer -->
        <jsp:include page="footer.jsp" />

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>

