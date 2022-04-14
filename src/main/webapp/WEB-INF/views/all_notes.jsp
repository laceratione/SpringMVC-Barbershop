<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/table_notes.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<div align="center">
    <h2>Application management</h2>
    <form method="get" action="search">
        <input type="text" name="keyword"/>
        <input type="submit" value="Search" class="button"/>
        <a href="/newNote" class="button">New note</a>
        <a href="/" class="button">Home page</a>
    </form>

    <div class="container">
    <table border="1" cellpadding="5">

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date visit</th>
            <th>Phone</th>
            <th>Service type</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${clients}" var="client">
                <tr>
                    <td><c:out value="${client.id}"/></td>
                    <td><c:out value="${client.name}"/></td>
                    <td><c:out value="${client.dateVisit}"/></td>
                    <td><c:out value="${client.phone}"/></td>
                    <td><c:out value="${client.serviceType}"/></td>
                    <td>
                        <a href="/editNote?id=${client.id}" class="button">Edit</a>
                        <a href="/deleteNote?id=${client.id}" class="button">Delete</a>
                    </td>
                </tr>
        </c:forEach>
    </table>
    </div>
</div>
</body>
</html>