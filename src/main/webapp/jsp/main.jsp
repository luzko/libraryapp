<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<section class="login-block">
    <div class="container">
        <h3 class="text-center">Hello!</h3>
        <br/>
        <h2 class="text-center">${user}, hello!</h2>
        <div>
            <a href="controller?command=logout" class="btn btn-login float-right">Logout</a>
        </div>
    </div>
</section>
</body>
</html>