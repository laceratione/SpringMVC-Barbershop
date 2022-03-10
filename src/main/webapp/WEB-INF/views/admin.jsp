<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
  <title>Admin</title>
  <link rel="stylesheet" href="css/table_notes.css">
</head>

<body>
<div align="center" style="margin-top:10%;">
  <table>
    <thead>
    <th>ID</th>
    <th>UserName</th>
    <th>Password</th>
    <th>Roles</th>
    <th>Action</th>
    </thead>
    <c:forEach items="${users}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.password}</td>
        <td>
          <c:forEach items="${user.roles}" var="role">
            ${role.name};
          </c:forEach>
        </td>
        <td>
            <a href="/deleteUser?id=${user.id}" class="button">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>