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
                <h5>Bank Account</h5>
                <p>Manage all your card, shopping more convenient!</p>
            </div>
            <div class="card-body text-dark">
                <table width="40%">
                    <tr>
                        <th>HOLDER FULL NAME</th>
                        <th>CARD NUMBER</th>
                        <th>EXPIRATION DATE</th>
                        <th>CVV</th>
                        <th>BANK</th>
                        <th>ACTION</th>
                    </tr>

                    <c:forEach items="${sessionScope.bankaccList}" var="s">
                        <tr>
                            <td>${s.holder}</td>
                            <td>${s.number}</td>
                            <td>${s.expiration}</td>
                            <td>${s.CVV}</td>
                            <td>${s.bank}</td>
                            <td><a href="deleteBankAcc?id=${s.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <hr><hr>
                <div>
                    <form action="addBankAcc" method="post" style="text-align: center">
                        <h5 class="text-tsunami">Add new Card</h5>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Holder's full name:</label>
                            <input type="text" class="form-control col-8" value="${sessionScope.acc.name}"
                                   required name="holder">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Card number: </label>
                            <input type="text" class="form-control col-8" value="${sessionScope.acc.phone}"
                                   required name="number">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Expiration date: </label>
                            <input type="date" class="form-control col-8" 
                                   required name="expiration">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">CVV: </label>
                            <input type="text" class="form-control col-8" placeholder="230..."
                                   required name="CVV">
                        </div>
                        <div class="form-group row">
                            <label class="col-3 col-form-label">Bank: </label>
                            <input type="text" class="form-control col-8" placeholder="TMCP..."
                                   required name="bank">
                        </div>

                        <input class="btn btn-tsunami" type="submit" value="Add" style="width: 30%;">
                    </form>
                </div>
            </div>
        </div>
        <!--./card-->
    </body>
</html>
