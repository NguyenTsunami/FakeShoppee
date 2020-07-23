<%-- 
    Document   : container
    Created on : Jul 11, 2020, 10:41:45 AM
    Author     : thuy
--%>

<%@page import="model.Category"%>
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
        <div class="container">

            <div class="row">

                <div class="col-lg-3">

                    <h3 class="my-4 text-tsunami text-center">Let's shopping!</h3>
                    <div class="list-group">

                        <%
                            request.setCharacterEncoding("UTF-8");
                            ProductDAO da = new ProductDAO();
                            ArrayList<Category> categoryList = da.getCategoryList();
                            request.setAttribute("categoryList", categoryList);
                        %>
                        <c:forEach items="${requestScope.categoryList}" var="s">
                            <a href="index.jsp?category=${s.id}" 
                               class="list-group-item ${param.category==s.id?'active':''}">
                                ${s.name}
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <!-- /.col-lg-3 -->

                <div class="col-lg-9">

                    <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <img class="d-block img-fluid" src="images/cover/cover1.jpg" alt="First slide" style="width: 1020px; height: 500px">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="images/cover/cover2.png" alt="Second slide" style="width: 1020px; height: 500px">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="images/cover/cover3.png" alt="Third slide" style="width: 1020px; height: 500px">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <div class="row">
                        <%
                            int categoryID = 0;
                            if (request.getParameter("category") != null) {
                                categoryID = Integer.parseInt(request.getParameter("category"));
                            }
                            ProductDAO pda = new ProductDAO();
                            ArrayList<ProductGroup> listPG = pda.getAllProductGroupViaCatID(categoryID);
                            request.setAttribute("listPG", listPG);
                        %>
                        <c:forEach items="${requestScope.listPG}" var="group">
                            <div class="col-lg-3 col-md-6 mb-4">
                                <a href="index.jsp?navActive=home&service=productgroup&id=${group.id}">
                                    <div class="card h-100 card-tsunami">
                                        <img class="card-img-top" src="${group.images.get(0)}" alt="imgCover" style="width: 100%; height: 150px;">
                                        <div class="card-body">
                                            <h5 class="card-title">
                                                <p class="text-tsunami">${group.name}</p>
                                            </h5>
                                            <h6 class="card-subtitle mb-2 text-muted">${group.brand.name}</h6>
                                            <div class="buy d-flex justify-content-between align-items-center" style="z-index: 1">
                                                <div class="text-success"><h5 class="mt-1">$125</h5></div>
                                                <div class="text-info"><h5 class="mt-1">Sold: 10</h5></div>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>


                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    </body>
</html>
