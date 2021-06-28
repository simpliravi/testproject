<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away - Make Flight Booking</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

${error }

<br><br>
Your card will be charged an amount of ${flightPrice}<br><br>
Please confirm your flight details below:<br><br>
<b>Date:</b> ${flightDate}<br>
<b>Airline:</b> ${flightAirline }<br>
<b>Sector:</b> ${sourceName} - ${destinationName}<br>
<b>Departure:</b> ${flightDeparture }<br>
<b>Arrival:</b> ${flightArrival }<br>
<b>Duration:</b> ${flightDuration }<br><br>

<a href="completepurchase">Click to complete booking</a>

<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>