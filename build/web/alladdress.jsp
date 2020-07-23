<%-- 
    Document   : alladdress
    Created on : Jul 12, 2020, 12:39:38 AM
    Author     : thuy
--%>

<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/tableStyle.css">
        <link rel="stylesheet" href="css/formStyle.css">
    </head>
    <body>
        <div class="card border-dark mb-3">
            <div class="card-header">
                <h5>All Address</h5>
                <p>Manage all your address, shopping more convenient!</p>
            </div>
            <div class="card-body text-dark">
                <table width="40%">
                    <tr>
                        <th>ID</th>
                        <th>ADDRESS</th>
                        <th>PHONE</th>
                        <th>ACTION</th>
                    </tr>

                    <c:forEach items="${sessionScope.addressList}" var="s">
                        <tr>
                            <td>${s.id}</td>
                            <td>${s}</td>
                            <td>${s.phone}</td>
                            <td><a href="deleteAddress?id=${s.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <hr><hr>
                <div>
                    <form action="addAddress" method="post" style="text-align: center">
                        <h5 class="text-tsunami">Add new Address</h5>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Full name:</label>
                            <input type="text" class="form-control col-8" value="${sessionScope.acc.name}"
                                   required name="fullname">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Phone:</label>
                            <input type="text" class="form-control col-8" value="${sessionScope.acc.phone}"
                                   required name="phone">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Provincial: </label>
                            <input type="text" class="form-control col-8" placeholder="Hanoi"
                                   required name="provincial">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">District: </label>
                            <input type="text" class="form-control col-8" placeholder="Thach That"
                                   required name="district">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Commune: </label>
                            <input type="text" class="form-control col-8" placeholder="Thach Hoa"
                                   required name="commune">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Apartment: </label>
                            <input type="text" class="form-control col-8" placeholder="Thon 8"
                                   required name="apartment">
                        </div>

                        <input class="btn btn-tsunami" type="submit" value="Add" style="width: 30%;">
                    </form>
                </div>
            </div>
        </div>
        <!--./card-->
    </body>
</html>
