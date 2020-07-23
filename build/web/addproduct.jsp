<%-- 
    Document   : addproduct
    Created on : Jul 14, 2020, 1:26:54 PM
    Author     : thuy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Category"%>
<%@page import="dal.ProductDAO"%>
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
        <div class="card border-dark mb-3">
            <div class="card-header">
                <h5>Add new Product</h5>
                <p>Provide initial information of product for customer to see</p>
            </div>
            <div class="card-body text-dark">

                <form action="addproduct" method="post" style="text-align: center" enctype = "multipart/form-data">
                    <!-----------------------------------------------------Basic Infomation-------------------------------------------------------------------->
                    <h5 class="text-tsunami m-3">Basic Information</h5>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">Product Name*</label>
                        <input type="text" class="form-control col-8"  
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="name" >
                    </div>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">Description*</label>
                        <textarea class="form-control col-8" rows="3" placeholder="Give some description about product for customer to know..."
                                  onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                                  required name="description"></textarea>
                    </div>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">Category*</label>
                        <select class="form-control col-8" name="category" id="category"
                                onchange="setBrandSelect()">
                            <option value="0"> 
                                --- Others ---
                            </option>
                            <%
                                request.setCharacterEncoding("UTF-8");
                                ProductDAO da = new ProductDAO();
                                ArrayList<Category> categoryList = da.getCategoryList();
                                request.setAttribute("categoryList", categoryList);
                            %>
                            <c:forEach items="${requestScope.categoryList}" var="s">
                                <option value="${s.id}"> 
                                    ${s.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="brand-container">
                        <c:forEach items="${requestScope.categoryList}" var="s">
                            <div class="form-group row brand-list-item" id="brand-${s.id}">
                                <label class="col-3 col-form-label">Brand*</label>
                                <select class="form-control col-8" name="brand">
                                    <c:forEach items="${s.brandList}" var="brand">
                                        <option value="${brand.id}"> 
                                            ${brand.name}
                                        </option>
                                    </c:forEach>
                                    <option value="0"> 
                                        --- No Brand ---
                                    </option>
                                </select>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">Cost*</label>
                        <input type="text" class="form-control col-8"  
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="cost">
                    </div>


                    <!-----------------------------------------------------Sales Infomation-------------------------------------------------------------------->
                    <hr><hr><h5 class="text-tsunami m-3" >Sales Information</h5>

                    <div id="div-classify-list">

                        <div id="classify-default">
                            <div class="form-group row">
                                <label class="col-3 col-form-label">Price*</label>
                                <input type="text" class="form-control col-8"  
                                       onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                                       name="classify-default-price" >
                            </div>
                            <div class="form-group row">
                                <label class="col-3 col-form-label">Quantity*</label>
                                <input type="text" class="form-control col-8"  
                                       onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                                       name="classify-default-quantity" >
                            </div>
                            <div class="form-group row">
                                <label class="col-3 col-form-label">Classify</label>
                                <button type="button" class="btn btn-classify col-8" onclick="addClassifyGroup(); loadClassifyTable()">
                                    <i class="fa fa-plus-circle" aria-hidden="true"></i> Add new Classify Group
                                </button>
                            </div>
                        </div>
                        <!--#classify-default-->


                        <div class="form-group row classify-group">
                            <span class="btn position-absolute span-btn-close" onclick="closeClassifyGroup(this); loadClassifyTable()"><i class="fa fa-times" aria-hidden="true"></i></span>
                            <label class="col-3 col-form-label classify-group-label">Classify Group ...</label>

                            <div class="col-8 div-classify-group rounded-sm py-3">
                                <div class="form-group row classify-group-name">
                                    <label class="col-4 col-form-label">Classify Group Name</label>
                                    <input type="text" class="form-control col-7"  
                                           onkeyup="loadClassifyImg(); loadClassifyTable()"
                                           name="classify-group-name" 
                                           placeholder="Enter the classify name, example: Color, Size,...">
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Product Classify</label>
                                </div>
                                <div class="form-group row classify-item">
                                    <label class="col-4 col-form-label"></label>
                                    <input type="text" class="form-control col-7"  
                                           onkeyup="loadClassifyImg(); loadClassifyTable()"
                                           name="classify-item-name" 
                                           placeholder="Enter classification, example: Green, Yellow, Pink,...">
                                </div>
                                <div class="form-group row div-add-classify-item">
                                    <label class="col-4 col-form-label"></label>
                                    <button type="button" class="btn btn-classify col-7" onclick="addNewClassifyItem(this); loadClassifyTable()">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>  
                                        <span>Added(1/20)</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!--.classify-group-->


                        <div class="form-group row" id="div-add-classify-group">
                            <label class="col-3 col-form-label">Classify Group 2</label>
                            <button type="button" class="btn btn-classify col-8" onclick="addClassifyGroup(); loadClassifyTable()">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i> Add
                            </button>
                        </div>
                        <!--#div-add-classify-group-->

                        <div class="form-group row" id="table-type-1">
                            <label class="col-3 col-form-label">List Classify Information</label>
                            <div class="col-8 px-0">
                                <table border="1" width="100%" class="table-classify">
                                    <tr>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <tr class="tr-item">
                                        <td class="td-name text-muted" style="background: #ECE9E6;">xx</td>
                                        <td class="td-price">
                                            <input type="text" name="price-item" required class="input-none-style">
                                        </td>
                                        <td class="td-quantity">
                                            <input type="text" name="quantity-item" required class="input-none-style">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <!--#div-list-classify-info type1-->

                        <div class="form-group row" id="table-type-2">
                            <label class="col-3 col-form-label">List Classify Information</label>
                            <div class="col-8 px-0">
                                <table border="1" width="100%" class="table-classify">
                                    <tr>
                                        <th>Name</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <tr class="tr-item">
                                        <td class="td-name text-muted" rowspan="3" style="background: #ECE9E6;">xxxxxxxxxxx</td>
                                        <td class="td-name text-muted" style="background: #ECE9E6;">xx</td>
                                        <td class="td-price">
                                            <input type="text" name="price-item" required class="input-none-style">
                                        </td>
                                        <td class="td-quantity">
                                            <input type="text" name="quantity-item" required class="input-none-style">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <!--#div-list-classify-info type2-->
                    </div>
                    <!--#div-classify-list-->


                    <!-----------------------------------------------------Images Management-------------------------------------------------------------------->
                    <hr><hr><h5 class="text-tsunami m-3">Images Management</h5>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">Images of Product</label>
                        <div class="col-8 d-inline-flex flex-wrap p-1">
                            <div class="card-input-img-container m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgCover" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>
                            <div class="card-input-img-container m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgCover" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>
                            <div class="card-input-img-container m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgCover" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>
                            <div class="card-input-img-container m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgCover" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>
                            <div class="card-input-img-container m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgCover" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>
                            <div class="card-input-img-container m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgCover" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>

                        </div>
                    </div>

                    <div class="form-group row" id="classify-images">
                        <label class="col-3 col-form-label">Images of Classify Group 1</label>
                        <div class="col-8 d-inline-flex flex-wrap p-1">
                            <div class="card-input-img-container item-img m-3">
                                <div class="input-img-container">
                                    <img class="img-display" src="" onload="loadDisplayImg(this)"/>
                                    <i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>
                                    <input type="file" name="imgItem" required onchange="readURL(this)">
                                </div>
                                <label class="m-1"> Image Cover </label>
                            </div>
                        </div>
                    </div>


                    <!-----------------------------------------------------Others Infomation-------------------------------------------------------------------->
                    <hr><hr><h5 class="text-tsunami m-3">Others Information</h5>
                    <div class="form-group row">
                        <label class="col-3 col-form-label">State</label>
                        <input class="form-control col-8" type="text" name="state" required>
                    </div>


                    <!-----------------------------------------------------Submit-------------------------------------------------------------------->
                    <hr><hr>
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
            </div>
            <!----.card-body---->
        </div>
        <!--./card-->

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="js/addproductJS.js"></script>
    </body>
</html>
