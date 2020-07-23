<%-- 
    Document   : productgroup
    Created on : Jul 22, 2020, 12:30:01 PM
    Author     : thuy
--%>

<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.ProductDAO"%>
<%@page import="model.ProductGroup"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/formStyle.css">
    </head>
    <body>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            ProductDAO da = new ProductDAO();
            ProductGroup group = da.getPGbyID(id);
            request.setAttribute("group", group);
            ArrayList<Product> list = da.getProductListOfGroup(id);
            request.setAttribute("list", list);
        %>

        <div class="m-5">
            <div class="mt-5 mx-5 text-tsunami"> Home | ${group.category.name} | ${group.brand.name}</div>
            <div class="border-dark mb-5 mt-2 mx-3 row" style="background: linen">
                <div class="col-4 m-5">
                    <div class="mb-3">
                        <img id="imgMain" src="${group.images.get(0)}" alt="imgCover" style="width: 100%; height: 450px;">
                    </div>
                    <div class="d-flex justify-content-between">
                        <img class="imgComp" src="${group.images.get(1)}" alt="imgCover" style="width: 75px; height: 75px;"
                             onmouseenter="mouseEnterImg(this)" onmouseleave="mouseLeaveImg()" onmousedown="mouseDownImg(this)">
                        <img class="imgComp" src="${group.images.get(2)}" alt="imgCover" style="width: 75px; height: 75px;"
                             onmouseenter="mouseEnterImg(this)" onmouseleave="mouseLeaveImg()" onmousedown="mouseDownImg(this)">
                        <img class="imgComp" src="${group.images.get(3)}" alt="imgCover" style="width: 75px; height: 75px;"
                             onmouseenter="mouseEnterImg(this)" onmouseleave="mouseLeaveImg()" onmousedown="mouseDownImg(this)">
                        <img class="imgComp" src="${group.images.get(4)}" alt="imgCover" style="width: 75px; height: 75px;"
                             onmouseenter="mouseEnterImg(this)" onmouseleave="mouseLeaveImg()" onmousedown="mouseDownImg(this)">
                        <img class="imgComp" src="${group.images.get(5)}" alt="imgCover" style="width: 75px; height: 75px;"
                             onmouseenter="mouseEnterImg(this)" onmouseleave="mouseLeaveImg()" onmousedown="mouseDownImg(this)">
                    </div>
                </div>

                <div class="col-6 my-5 mr-5">
                    <h3 class="text-dark">${group.name}</h3>
                    <p class="d-inline mr-2 text-tsunami">4.9/5</p><span class="text-tsunami">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                    <p class="d-inline ml-2 pl-2 border-left border-secondary">1.7k Sold</p>
                    <div class="rounded my-3 p-4" style="background: whitesmoke">
                        <div class="price-container">
                            <h3 class="text-tsunami price-info" id="price-null-null">${list.get(0).price} đ </h3>
                            <c:forEach items="${list}" var="product">
                                <h3 class="text-tsunami price-info" id="price-${product.item1.id==null?'null':product.item1.id}-${product.item2.id==null?'null':product.item2.id}">
                                    ${product.price} đ 
                                </h3>
                            </c:forEach>
                        </div>
                        <i class="fas fa-money-bill-wave"></i> Where is cheaper then go out there to buy
                    </div>
                    <div class="m-2 row">
                        <p class="d-inline col-3 text-muted">Shop</p>
                        <a href="" class="d-inline col-7">${group.shop.brand}</a>
                    </div>
                    <div class="m-2 row">
                        <p class="d-inline col-3 text-muted">Description</p>
                        <p class="d-inline col-7">${group.description}</p>
                    </div>
                    <div class="m-2 row">
                        <p class="d-inline col-3 text-muted">State</p>
                        <p class="d-inline col-7">${group.state}</p>
                    </div>

                    <form action="addOrder" method="post">
                        <input type="hidden" name="groupID" value="${group.id}">
                        <input type="hidden" name="img" value="${group.images.get(0)}">
                        
                        <div class="m-2 row">
                            <p class="d-inline col-3 text-muted">${group.classify1.name}</p>
                            <div class="col-7 btn-item-1" >
                                <c:forEach items="${group.classify1.list}" var="item">
                                    <button type="button" class="btn btn-light" style="width:100px; height: 45px" 
                                            onmousedown="clickBtnItem1(this, '${item.id}', '${item.img}')"
                                            onmouseenter="mouseEnterBtn('${item.img}')"
                                            onmouseleave="mouseLeaveBtn()">
                                        ${item.name}
                                    </button>
                                </c:forEach>
                                <input id="item1" type="hidden" name="item1ID"> 
                            </div>
                        </div>
                        <!--item1-->
                        <div class="m-2 row">
                            <p class="d-inline col-3 text-muted">${group.classify2.name}</p>
                            <div class="col-7 btn-item-2" >
                                <c:forEach items="${group.classify2.list}" var="item">
                                    <button type="button" class="btn btn-light" style="width:100px; height: 45px" 
                                            onmousedown="clickBtnItem2(this, '${item.id}')">
                                        ${item.name}
                                    </button>
                                </c:forEach>
                                <input id="item2" type="hidden" name="item2ID"> 
                            </div>
                        </div>
                        <!--item2-->
                        <div class="my-4 mx-2 row">
                            <p class="d-inline col-3 text-muted">Quantity</p>
                            <div class="col-7">
                                <table border="1" width="40%">
                                    <tr>
                                        <td>
                                            <button type="button" class="m-0 btn-non-style" onclick="minus()">
                                                <i class="fa fa-minus" aria-hidden="true"></i>
                                            </button>
                                        </td>
                                        <td>
                                            <input id="input-quantity" type="text" name="quantity" class="m-0 input-none-style" 
                                                   pattern="[0-9]+" required width="100%" style="width: 100%; text-align: center;" value="10">
                                        </td>
                                        <td>
                                            <button type="button" class="m-0 btn-non-style">
                                                <i class="fa fa-plus" aria-hidden="true" onclick="plus()"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                                <div class="quan-container d-inline">
                                    <%
                                        int totalQuan = 0;
                                        for (Product p: list) {
                                            totalQuan += p.getQuantity();
                                        }
                                    %>
                                    <inline class="quan-info" id="quan-null-null"><%=totalQuan%></inline>
                                    <c:forEach items="${list}" var="product">
                                        <inline class="quan-info" id="quan-${product.item1.id==null?'null':product.item1.id}-${product.item2.id==null?'null':product.item2.id}">
                                            ${product.quantity} 
                                        </inline>
                                    </c:forEach>
                                    in Stock
                                </div>
                            </div>
                        </div>
                        <!--quantity-->
                        
                        <input type="submit" class="btn btn-tsunami float-right" value="Add to Cart">
                    </form>
                </div>
            </div>
        </div>


        <script src="https://use.fontawesome.com/56a1666710.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="js/productgroupJS.js"></script>
    </body>
</html>
