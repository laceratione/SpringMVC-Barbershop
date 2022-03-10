<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/table_notes.css">
</head>
<body>
<div align="center" style="margin-top:10%;">
    <h2>Search results</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date visit</th>
            <th>Phone</th>
            <th>Service type</th>
        </tr>
        <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.dateVisit}</td>
            <td>${client.phone}</td>
            <td>${client.serviceType}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>