<%-- 
    Document   : container
    Created on : Jul 11, 2020, 10:41:45 AM
    Author     : thuy
--%>

<%@page import="model.ProductGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/tsunamiStyle.css">
    </head>
    <body>
        <%
            if (request.getParameter("navActive") == null
                    || (request.getParameter("navActive").equals("home") && request.getParameter("service") == null)
                    || request.getParameter("service").equals("listall")) {
        %>

        <jsp:include page="listall.jsp" />

        <%
        } else if (request.getParameter("service").equals("productgroup")) {
        %>

        <jsp:include page="productgroup.jsp" />

        <%
            }
        %>
    </body>
</html>
