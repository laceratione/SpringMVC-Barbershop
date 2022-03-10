<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
      <link rel="stylesheet" href="css/login.css">
      <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
      <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
      <title>Login</title>
</head>
<body>
  <c:url value="/j_spring_security_check" var="loginUrl"/>
  <div class="main">
    <p class="sign" align="center">Login</p>
    <form action="${loginUrl}" method="post">
      <input name="j_username" class="un" type="text" align="center" placeholder="Username">
      <input name="j_password" class="pass" type="password" align="center" placeholder="Password">
      <button type="submit" class="submit">Login</button>
    </div>
</body>
</html>