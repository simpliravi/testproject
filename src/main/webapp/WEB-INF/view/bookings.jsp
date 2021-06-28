<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away - Your Bookings</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

<br><br>Total ${list.size()} bookings<br>

<table border=1 cellspacing=2 cellpadding=4>
 		<tr>
 		<td><b>Date Of Booking</b></td>
 		<td><b>Source</b></td>
 		<td><b>Destination</b></td>
 		<td><b>Airline</b></td>
 		<td><b>Cost</b></td>
 	</tr>
	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.bookingDate }</td>
	 		<td>${item.flightRow.sourceRow.name }</td>
	 		<td>${item.flightRow.destinationRow.name }</td>
	 		<td>Dep.${item.flightRow.departure} Arr.${item.flightRow.arrival } (${item.flightRow.duration })</td>
 			<td>${item.price }</td>
 	  	</tr>
 	  </c:forEach> 	
</table> 		

<br>
<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>