<%-- 
    Document   : oncart
    Created on : Jul 23, 2020, 8:21:31 AM
    Author     : thuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="p-2 border border-warning">
            <div class="border-dark m-1 p-3" style="background: linen">
                <h5 class="text-center text-tsunami">ORDERS LIST ON CART</h5>
                <hr><hr>
                <div class="row">
                    <h6 class="d-inline col-5 align-self-center pl-5">Product</h6>
                    <h6 class="d-inline col-2 align-self-center text-center">Price</h6>
                    <h6 class="d-inline col-2 align-self-center text-center">Quantity</h6>
                    <h6 class="d-inline col-2 align-self-center text-center">Total Cost</h6>
                    <h6 class="d-inline col-1 align-self-center text-center">Action</h6>
                </div>
            </div>

            <c:forEach items="${sessionScope.orderListOnCart}" var="order">
                <div class="border-dark m-1 p-3" style="background: linen">
                    <div class="row">
                        <div class="d-inline col-5">
                            <img src="${order.img}" alt="imgCover" style="width: 100px; height: 100px;" class="mr-3">
                            <p class="d-inline">${order.product.group.name} | ${order.product.item1.name} | ${order.product.item2.name}</p>
                        </div>
                        <div class="d-inline col-2 align-self-center text-center">${order.product.price}</div>
                        <div class="d-inline col-2 align-self-center text-center">${order.quantity}</div>
                        <div class="d-inline col-2 align-self-center text-center">${order.product.price * order.quantity}</div>
                        <div class="d-inline col-1 align-self-center text-center"><i class="fa fa-trash fa-2x" aria-hidden="true"></i></div>
                    </div>
                    <hr><hr>
                    <form class="row">
                        <div class="col-9">
                            <div class="row">
                                <h6 class="col-5 pl-5">Payment Method: </h6>
                                <select name="cardID" class="col-6">
                                    <c:forEach items="${sessionScope.bankaccList}" var="card">
                                        <option value="${card.id}">${card.bank} | ${card.holder} | ${card.number}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br>
                            <div class="row">
                                <h6 class="col-5 pl-5">Your Address: </h6>
                                <select name="addressID" class="col-6">
                                    <c:forEach items="${sessionScope.addressList}" var="address">
                                        <option value="${address.id}">${address} | ${address.phone}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="submit" class="col-2 d-inline btn btn-tsunami float-right align-self-center" 
                               value="Check Out!" style="height: 50px; width: 75px;">
                    </form>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
