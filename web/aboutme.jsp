<%-- 
    Document   : aboutme
    Created on : Jul 12, 2020, 12:39:16 AM
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
                <h5>About me</h5>
                <p>Manage profile information for account security</p>
            </div>
            <div class="card-body text-dark">

                <form action="editProfile" method="post" style="text-align: center">
                    <div class="form-group row">
                        <label for="user" class="col-3 col-form-label">Username:</label>
                        <input type="text" class="form-control col-8" readonly value="${sessionScope.acc.username}" name="user">
                    </div>

                    <div class="form-group row">
                        <label for="name" class="col-3 col-form-label">Full name:</label>
                        <input type="text" id="name" class="form-control col-8" value="${sessionScope.acc.name}"
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="name" pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*.{3,100}$">
                    </div>

                    <div class="form-group row">
                        <label for="mail" class="col-3 col-form-label">Email:</label>
                        <input type="email" id="mail" class="form-control col-8" value="${sessionScope.acc.mail}"
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               required name="mail" pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                    </div>

                    <div class="form-group row">
                        <label for="phone" class="col-3 col-form-label">Phone Number:</label>
                        <input type="tel" id="phone" class="form-control col-8" name="phone" value="${sessionScope.acc.phone}"
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();"
                               pattern="/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/">
                    </div>

                    <div class="form-group row">
                        <label for="dob" class="col-3 col-form-label">Date Of Birth:</label>
                        <input type="date" id="dob" class="form-control col-8" required name="dob" value="${sessionScope.acc.dob}"
                               onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();">
                    </div>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">Gender:</label>
                        <div class="radio radio-tsunami radio-inline mb-2 col-8">
                            <div>
                                <input type="radio" id="male" name="gender" value="male" ${sessionScope.acc.gender?"checked":""}
                                       onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();">
                                <label for="male">Male</label>
                            </div>
                            <div>
                                <input type="radio" id="female" name="gender" value="female" ${sessionScope.acc.gender?"":"checked"}
                                       onchange="document.getElementById('alert-success').remove(); document.getElementById('alert-error').remove();">
                                <label for="female">Female</label>
                            </div>
                        </div>
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
