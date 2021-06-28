<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away - Booking Confirmation</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>


<br><br>
Your Flight  by ${flight.airlineRow.name} , from ${flight.sourceRow.name} to ${flight.destinationRow.name}, on ${booking.flightDate}  has been booked for ${booking.price}.<br><br>
Check your <A href="memberbookings">See Your Bookings</A>
 
<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>