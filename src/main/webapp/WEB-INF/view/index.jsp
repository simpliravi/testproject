<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away </title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

<%
if (request.getParameter("error") != null)
	out.print(request.getParameter("error"));
%>
${error }
<form>
<table border=0 cellspacing=2 cellpadding=4>
	<tr>
		<td width="35%">
		 From<br>
		 <select name=source>
		 	<option value="0">Select Source</option>
		 	${sourceDropDown }
		 </select>
		</td>
		<td width="35%">
		 To<br>
		 <select name=destination>
		 	<option value="0">Select Destination</option>
		 	${destinationDropDown }
		 </select>
		</td>
		<td>
			<button>Submit</button>
		</td>
	</tr>
</table>
</form>
<br>
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Source</b></td>
 		<td><b>Destination</b></td>
 		<td><b>Airline</b></td>
 		<td><b>Duration</b></td>
 		<td><b>Cost</b></td>
 		<td>&nbsp;</td>
 	</tr>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.sourceRow.name }</td>
	 		<td>${item.destinationRow.name }</td>
	 		<td>${item.airlineRow.name }</td>
	 		<td>Dep.${item.departure} Arr.${item.arrival } (${item.duration })</td>
 			<td>${item.price }</td>
 	  		<td>
 	  			<a href="bookflight?id=${item.ID}">Book Flight</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
 	
 	</table>

<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>