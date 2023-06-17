<%--
  Created by IntelliJ IDEA.
  User: IQHOSSAN
  Date: 6/12/2023
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${msg} a Book</title>
</head>
<body>

<c:choose>
    <c:when test="${msg == 'Update'}">
        <c:set var="formAction" value="../books/${book.id}" />
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="../books" />
    </c:otherwise>
</c:choose>
    <form:form action="${formAction}" method="post" modelAttribute="book">
        <form:errors path="*" cssClass="error" />
        <table border="1">
            <tr>
                <td>Title:</td>
                <td> <form:input path="title" value="${book.title}" />
                <form:errors path="title" cssClass="error"/> </td>
                <!-- input type="text" name="title" value="${book.title}" / -->
            </tr>
            <tr>
                <td>ISBN:</td>
                <td><input type="text" name="ISBN" value="${book.ISBN}" /> </td>
            </tr>
            <tr>
                <td>Author:</td>
                <td><input type="text" name="author" value="${book.author}" /> </td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" value="${book.price}" /> </td>
            </tr>
            <tr>
                <td> <input type="submit" value="${msg}"/> </td>
            </tr>
        </table>
    </form:form>
    <c:if test="${msg == 'Update'}">
    <form:form action="delete?bookId${book.id}" method="post">
    <!-- form action="delete?bookId=${book.id}" method="post" -->
        <button type="submit">Delete</button>
        </form:form>
    </c:if>
</body>
</html>
