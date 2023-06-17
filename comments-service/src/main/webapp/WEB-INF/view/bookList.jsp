<%--
  Created by IntelliJ IDEA.
  User: IQHOSSAN
  Date: 6/12/2023
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book</title>
</head>
<body>
<h1>Books currently in the shop</h1>
  <table>
    <tr><th>Id</th><th>Title</th><th>ISBN</th><th>Author</th><th>Price</th></tr>
    <c:forEach var="book" items="${books}" >
      <tr>
        <td>${book.id}</td>
        <td>${book.title}</td>
        <td>${book.ISBN}</td>
        <td>${book.author}</td>
        <td>${book.price}</td>
        <td>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <a href="books/${book.id}">Edit</a>
        </sec:authorize>
        </td>
      </tr>
    </c:forEach>
  </table>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="books/add"> Add a Book</a><br/>
  </sec:authorize>

<a href="logout">Logout</a>
</body>
</html>
