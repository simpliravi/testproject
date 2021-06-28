<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Add/Edit Flight</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/admin-header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/admin-topbar.jsp" ></jsp:include>

<%
if (request.getParameter("error") != null)
	out.print(request.getParameter("error"));
%>

<form name=frmFlight method=post action="admineditflightaction">
	<input type=hidden name=id value="${flight.ID }">
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td width=25%>Source*</td>
 		<td>
 			<select name=source>
 				<option value="0">Select source</option>
 				${sourceDropDown}
 			</select>
 		</td>
 	</tr>
 	<tr>
 		<td width=25%>Destination*</td>
 		<td>
 			<select name=destination>
 				<option value="0">Select destination</option>
 				${destinationDropDown}
 			</select>
 		</td>
 	</tr>
 	<tr>
 		<td width=25%>Airline*</td>
 		<td>
 			<select name=airline>
 				<option value="0">Select airline</option>
 				${airlineDropDown}
 			</select>
 		</td>
 	</tr>
 	<tr>
 		<td width=25%>Departure At*</td>
 		<td><input name=departure  maxlength=10 value="${flight.departure }"></td>
 	</tr>
 	<tr>
 		<td width=25%>Arrival At*</td>
 		<td><input name=arrival  maxlength=10 value="${flight.arrival }"></td>
 	</tr>
 	<tr>
 		<td width=25%>Duration*</td>
 		<td><input name=duration  maxlength=10 value="${flight.duration }"></td>
 	</tr>
 	
 	
 	<tr>
 		<td width=25%>Price*</td>
 		<td><input name=price type="numeric"  maxlength=6 value="${flight.price }"></td>
 	</tr>
 	<tr>
 		<td colspan=2>
 			<button>Save</button>
 		</td>
 	</tr>
 </table>
</form>

<jsp:include page="/WEB-INF/view/components/admin-footer.jsp"></jsp:include>
</body>
</html>