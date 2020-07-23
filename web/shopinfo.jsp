<%-- 
    Document   : shopinfo
    Created on : Jul 13, 2020, 4:10:36 PM
    Author     : thuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/formStyle.css">
    </head>
    <body>
        <div class="card border-dark mb-3">
            <div class="card-header">
                <h5>Basic Infomation</h5>
                <p>View Shop Status and update your Shop Profile</p>
            </div>
            <div class="card-body text-dark">
                <%
                    if (session.getAttribute("shop") == null) {
                %>

                <div class="text-center">
                    You have not owned a shop! Do you want to create a shop? 
                    <a class="text-tsunami collapsed" data-toggle="collapse" data-target="#from-create-shop" href="">
                        Create now! <i class="fa fa-caret-square-o-down" aria-hidden="true"></i>
                    </a>
                </div>

                <form class="collapse text-center m-3" id="from-create-shop" action="createshop" method="post">
                    <h4 class="text-tsunami m-5"><i>The biggest secret of an offer to sell is PREPARATION</i></h4>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Brand*:</label>
                        <input type="text" class="form-control col-8" value="${sessionScope.acc.username}" required name="shop_brand">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Description:</label>
                        <textarea class="form-control col-8" rows="3" name="shop_description"></textarea>
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Address*:</label>
                        <input type="text" class="form-control col-8" required name="shop_address">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Phone:</label>
                        <input type="tel" class="form-control col-8" name="shop_phone">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Facebook:</label>
                        <input type="url" class="form-control col-8" name="shop_facebook">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Instagram:</label>
                        <input type="url" class="form-control col-8" name="shop_insta">
                    </div>

                    <%
                        if (request.getAttribute("create_shop_error") != null) {
                    %>
                    <div class="text-danger m-2" id="alert-error">${requestScope.create_shop_error}</div>
                    <%
                        }
                    %>

                    <button class="btn btn-lg btn-tsunami btn-block btn-login text-uppercase font-weight-bold mb-2" 
                            style="width: 30%; margin: auto" type="submit">Create</button>

                </form>

                <%
                } else {
                %>

                <form action="editShop" method="post" style="text-align: center">
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Brand*:</label>
                        <input type="text" class="form-control col-8" value="${sessionScope.shop.brand}" readonly name="shop_brand">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Description:</label>
                        <textarea class="form-control col-8 font-italic" rows="3" name="shop_description">${sessionScope.shop.description}</textarea>
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Address*:</label>   
                        <input type="text" class="form-control col-8" required value="${sessionScope.shop.address}" name="shop_address">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Phone:</label>
                        <input type="tel" class="form-control col-8" value="${sessionScope.shop.phone}" name="shop_phone">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Facebook:</label>
                        <a href="${sessionScope.shop.facebook}" class="mr-5"><img src="https://img.icons8.com/bubbles/50/000000/facebook-new.png"/></a>
                        <input type="url" class="form-control col-6" value="${sessionScope.shop.facebook}" name="shop_facebook">
                    </div>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Instagram:</label>
                        <a href="${sessionScope.shop.insta}" class="mr-5"><img src="https://img.icons8.com/bubbles/50/000000/instagram-new.png"/></a>
                        <input type="url" class="form-control col-6" value="${sessionScope.shop.insta}" name="shop_insta">
                    </div>

                    <%
                        if (request.getAttribute("success") != null) {
                    %>
                    <div class="text-success m-2" id="alert-success">${requestScope.success}</div>
                    <%
                        }
                    %>

                    <%
                        if (request.getAttribute("error") != null) {
                    %>
                    <div class="text-danger m-2" id="alert-error">${requestScope.error}</div>
                    <%
                        }
                    %>

                    <button class="btn btn-lg btn-tsunami btn-block btn-login text-uppercase font-weight-bold mb-2" 
                            style="width: 30%; margin: auto" type="submit">Save change</button>
                </form>
                <%
                    }
                %>
            </div>
        </div>
        <!--./card-->
    </body>
</html>
