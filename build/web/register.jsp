<%-- 
    Document   : register
    Created on : Jun 15, 2020, 11:29:57 AM
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
                <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                <div class="col-md-8 col-lg-6">
                    <div class="login d-flex align-items-center py-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-9 col-lg-8 mx-auto">
                                    <h3 class="login-heading mb-4">Create new Account</h3>

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
                                                if (listCookie[i].getName().equals("rem")) {
                                                    rem = listCookie[i].getValue();
                                                }
                                                i++;
                                            }
                                        }
                                    %>

                                    <form action="register" method="post" id="myForm"  enctype = "multipart/form-data">
                                        <div class="form-label-group">
                                            <input type="text" id="user" class="form-control" 
                                                   required autofocus name="user" pattern="^(?=.{3,100}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$">
                                            <label for="user">Username</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="password" id="pass" class="form-control" 
                                                   required name="pass" pattern="^[A-Za-z]\w{6,20}$">
                                            <label for="pass">Password</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="text" id="name" class="form-control" 
                                                   required name="name" pattern="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*.{3,100}$">
                                            <label for="name">Name</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="email" id="mail" class="form-control" 
                                                   required name="mail" pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                                            <label for="mail">Email</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="tel" id="phone" class="form-control" name="phone"
                                                   pattern="/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/">
                                            <label for="phone">Phone Number</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="date" id="dob" class="form-control" required name="dob">
                                            <label for="dob">Date Of Birth</label>
                                        </div>

                                        Gender
                                        <div class="radio radio-tsunami radio-inline">
                                            <div>
                                                <input type="radio" id="male" name="gender" value="male" checked>
                                                <label for="male">Male</label>
                                            </div>
                                            <div>
                                                <input type="radio" id="female" name="gender" value="female">
                                                <label for="female">Female</label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="avatar">Avatar</label>
                                            <input type="file" class="form-control-file" id="avatar" name="avatar">
                                        </div>

                                        <%
                                            if (request.getAttribute("success") != null) {
                                        %>
                                        <div class="text-success m-2">${requestScope.success}</div>
                                        <%
                                            }
                                        %>

                                        <%
                                            if (request.getAttribute("error") != null) {
                                        %>
                                        <div class="text-danger m-2">${requestScope.error}</div>
                                        <%
                                            }
                                        %>

                                        <button class="btn btn-lg btn-tsunami btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Sign up</button>

                                        <div class="text-center">
                                            <a class="small text-tsunami" href="login.jsp">Already have an account? Sign in</a>
                                        </div>
                                    </form>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
