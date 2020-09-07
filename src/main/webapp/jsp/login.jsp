<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Login
    </title>
</head>

<body>
<h1>Login</h1>
<hr/>

<fieldset>
    <legend>Login Form</legend>
    <form name="loginForm" method="POST" action="/controller/">
        <input type="hidden" name="command" value="login"/> Login:<br/>
        <input type="text" name="login" value=""/> <br/>Password:<br/>
        <input type="password" name="password" value=""/> <br/>
        ${errorLoginPassMessage} <br/>
        <input type="submit" value="Log in"/>
    </form>
</fieldset>
</body>
</html>