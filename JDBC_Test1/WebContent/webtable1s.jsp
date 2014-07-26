<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>    
<%@ page import="com.dan.test.webobjects.*" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String message = null;
	if (request.getAttribute("MESSAGE") != null) {
		message = (String)request.getAttribute("MESSAGE");	
	}
	
	List<WebTable1> webTable1s = null;
	if (request.getAttribute("WEBTABLE1S") != null) {
		webTable1s = (List<WebTable1>)request.getAttribute("WEBTABLE1S");
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Table 1s</title>
</head>
<body>
	<div style="color: #ff0000"><%= message != null ? message : "" %></div>
	
	<% if (webTable1s != null && webTable1s.size() > 0) { %>
	<table>
		<% for (WebTable1 webTable1 : webTable1s) { %>
			<tr>
				<td><%= webTable1.getSet() %></td>
				<td><%= webTable1.getKey() %></td>
				<td><%= webTable1.getValue() %></td>
			</tr>
		<% } %>
	</table> 
	<% } %>
</body>
</html>