<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link rel="stylesheet" href="css/new_note.css">
</head>
<body>
    <div align="center" class="container">
        <div class="card">
        <form:form class="card-form" action="save" method="post" modelAttribute="client">
            <table border="0" cellpadding="5">
                <tr>
                    <td>Id: </td>
                    <td>
                        ${client.id}
                        <form:hidden path="id" class="input-field"/>
                    </td>
                </tr>
                <tr>
                    <td>Full name: </td>
                    <td>
                        <form:input path="name" class="input-field"/>
                    </td>
                </tr>
                <tr>
                    <td>Date and time of visit: </td>
                    <td><form:input path="dateVisit" class="input-field"/></td>
                </tr>
                <tr>
                    <td>Phone number: </td>
                    <td><form:input path="phone" class="input-field"/></td>
                </tr>
                <tr>
                    <td>Service type: </td>
                    <td>
                        <form:select path="serviceType">
                          <form:option value="haircut">haircut</form:option>
                          <form:option value="manicure">manicure</form:option>
                          <form:option value="solarium">solarium</form:option>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Save" class="action-button"></td>
                </tr>
            </table>
        </form:form>
        </div>
    </div>

</body>
</html>