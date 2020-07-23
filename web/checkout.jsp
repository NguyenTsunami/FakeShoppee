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
                <h5 class="text-center text-tsunami">ORDERS LIST CHECKED OUT</h5>
                <hr><hr>
                <div class="row">
                    <h6 class="d-inline col-5 align-self-center pl-5">Product</h6>
                    <h6 class="d-inline col-2 align-self-center text-center">Price</h6>
                    <h6 class="d-inline col-2 align-self-center text-center">Quantity</h6>
                    <h6 class="d-inline col-2 align-self-center text-center">Total Cost</h6>
                </div>
            </div>

            <c:forEach items="${sessionScope.orderListCheckout}" var="order">
                <div class="border-dark m-1 p-3" style="background: linen">
                    <div class="row">
                        <div class="d-inline col-5">
                            <img src="${order.img}" alt="imgCover" style="width: 100px; height: 100px;" class="mr-3">
                            <p class="d-inline">${order.product.group.name} | ${order.product.item1.name} | ${order.product.item2.name}</p>
                        </div>
                        <div class="d-inline col-2 align-self-center text-center">${order.product.price}</div>
                        <div class="d-inline col-2 align-self-center text-center">${order.quantity}</div>
                        <div class="d-inline col-2 align-self-center text-center">${order.product.price * order.quantity}</div>
                    </div>
                    <hr><hr>

                    <div class="col-9">
                        <div class="row">
                            <h6 class="col-5 pl-5">Payment Method: </h6>
                            <p class="col-6">${order.card.bank} | ${order.card.holder} | ${order.card.number}</p>
                        </div>
                        <br>
                        <div class="row">
                            <h6 class="col-5 pl-5">Your Address: </h6>
                            <p class="col-6">${order.address} | ${order.address.phone}</p>
                        </div>
                    </div>

                </div>
            </c:forEach>
        </div>
    </body>
</html>
