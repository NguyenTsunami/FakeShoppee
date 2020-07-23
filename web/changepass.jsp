<%-- 
    Document   : changepass
    Created on : Jul 12, 2020, 12:39:54 AM
    Author     : thuy
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/formStyle.css">
    </head>
    <body>
        <div class="card border-dark mb-3" style="width: ">
            <div class="card-header">
                <h5>Change password</h5>
                <p>Choose a strong password and don't reuse it for other accounts.</p>
            </div>
            <div class="card-body text-dark">
                <form action="editPass" method="post" class="m-2" style="text-align: center">

                    <div class="form-group row">
                        <label for="oldPass" class="col-3 col-form-label">Old password:</label>
                        <input type="password" id="oldPass" class="form-control col-8" 
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="oldPass" pattern="^[A-Za-z]\w{6,20}$" value="${requestScope.oldPass}">
                    </div>

                    <div class="form-group row">
                        <label for="newPass" class="col-3 col-form-label">New password:</label>
                        <input type="password" id="newPass" class="form-control col-8" 
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="newPass" pattern="^[A-Za-z]\w{6,20}$" value="${requestScope.newPass}">
                    </div>

                    <div class="form-group row">
                        <label for="confirmPass" class="col-3 col-form-label">Confirm new password:</label>
                        <input type="password" id="confirmPass" class="form-control col-8" 
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="confirmPass" pattern="^[A-Za-z]\w{6,20}$" value="${requestScope.confirmPass}">
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
            </div>
        </div>
        <!--./card-->
    </body>
</html>
