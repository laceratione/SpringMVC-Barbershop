<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <title>Главная</title>
  <link rel="stylesheet" href="css/index.css" type="text/css">
</head>
<body>
<div id="feedback-form">
    <div>
            <sec:authorize access="!isAuthenticated()">
                <h2 class="header">Welcome to Hairdress App</h2>
                <form action="/login">
                    <button type="submit" id="button">Войти</button>
                </form>
                <form action="/registration">
                    <button type="submit" id="button">Регистрация</button>
                </form>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('ADMIN')">
                <h2 class="header">Welcome, <sec:authentication property="principal.username"/></h2>
                <form action="/admin">
                    <button type="submit" id="button">Пользователи</button>
                </form>
                <form action="/clients">
                    <button type="submit" id="button">Заявки на посещение</button>
                </form>
                <form action="/logout">
                    <button type="submit" id="button">Выйти</button>
                </form>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('USER')">
                <h2 class="header">Welcome, <sec:authentication property="principal.username"/></h2>
                <form action="/newNote">
                    <button type="submit" id="button">Сделать заявку</button>
                </form>
                <form action="/logout">
                    <button type="submit" id="button">Выйти</button>
                </form>
            </sec:authorize>
    </div>
</div>
</body>
</html>