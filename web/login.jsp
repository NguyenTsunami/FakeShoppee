<%-- 
    Document   : login
    Created on : Jun 15, 2020, 12:11:45 AM
    Author     : thuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/formStyle.css">
        <link rel="stylesheet" href="css/tsunamiStyle.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row no-gutter">
                <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image">

                </div>
                <div class="col-md-8 col-lg-6">
                    <div class="login d-flex align-items-center py-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-9 col-lg-8 mx-auto">
                                    <h3 class="login-heading mb-4">Welcome to <a href="index.jsp" class="text-tsunami">Tsunami</a>!</h3>

                                    <%
                                        Cookie[] listCookie = request.getCookies();
                                        String user = "";
                                        String pass = "";
                                        String rem = "";
                                        int i = 0;
                                        if (listCookie != null) {
                                            while (i < listCookie.length) {
                                                if (listCookie[i].getName().equals("user")) {
                                                    user = listCookie[i].getValue();
                                                }
                                                if (listCookie[i].getName().equals("pass")) {
                                                    pass = listCookie[i].getValue();
                                                }
                                                if (listCookie[i].getName().equals("pass")) {
                                                    rem = listCookie[i].getValue();
                                                    if (rem != null) {
                                                        request.setAttribute("user", user);
                                                        request.setAttribute("pass", pass);
                                                    }
                                                }
                                                i++;
                                            }
                                        }
                                    %>

                                    <form action="login" method="post">
                                        <div class="form-label-group">
                                            <input type="text" id="user" class="form-control" placeholder="Username" required autofocus name="user" value="${requestScope.user}">
                                            <label for="user">Username</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="password" id="pass" class="form-control" placeholder="Password" required name="pass" value="${requestScope.pass}">
                                            <label for="pass">Password</label>
                                        </div>

                                        <div class="checkbox checkbox-tsunami checkbox-circle">
                                            <input type="checkbox" checked id="rem" value="ON" name="rem">
                                            <label class="" for="rem">Remember password</label>
                                        </div>

                                        <%
                                            if (request.getAttribute("error") != null) {
                                        %>
                                        <div class="text-danger m-2">${requestScope.error}</div>
                                        <%
                                            }
                                        %>

                                        <button class="btn btn-lg btn-tsunami btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Sign in</button>

                                    </form>

                                    <div class="text-center">
                                        <a class="small text-tsunami" href="register.jsp">New to Tsunami? Join now</a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://use.fontawesome.com/56a1666710.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </body>
</html>
