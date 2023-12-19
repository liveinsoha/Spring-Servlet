<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
</head>
<body>
<a href="/index.html">메인</a> <!--webapp아래애 있는 index.html로 이동한다. 직접 요청 이것이 url-->
<table>
<thead>
<th>id</th>
<th>username</th>
<th>age</th>
</thead>
<tbody>
<c:forEach var="item" items="${members}">
<tr>
<td>${item.id}</td>
<td>${item.username}</td>
<td>${item.age}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>