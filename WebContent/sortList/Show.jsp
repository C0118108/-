<%@page import="sortList.Yaoya"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show.jsp</title>
</head>
<body>

	<h3>商品一覧</h3>

	<table border="1">
		<%
			List<Yaoya> ylist = (ArrayList<Yaoya>) request.getAttribute("ylist");
			for (Yaoya y : ylist) {
		%>
		<tr>
			<td><%=y.getFood_id()%></td>
			<td><%=y.getName()%></td>
			<td><%=y.getType_id()%></td>
			<td><%=y.getCost()%></td>
		</tr>

		<%
			}
		%>
	</table>

	<p><a href="Top.html">戻る</a></p>

</body>
</html>