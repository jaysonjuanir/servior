<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.person.dto.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false"%>



<html>
	<head>
		<title>index</title>
	</head>
	<body style="padding:2% 10% 10% 10%">
	
				<div><c:out value="${requestScope.thisShit}"/></div>
		<div>
			<div class="row">
				<div class="column column-6">
				
				<c:set var="persons" value='${requestScope["persons"]}' />
				<c:forEach var="person" items="${persons}" >
					<c:set var="personId" value='${person.getId()}' />
					<div><c:out value="${personId}"/></div>
				</c:forEach>
					<span style="color:blue"><c:out value="${persons}"/></span>
				</div>
				
				</div>
			</div>
			
		</div>

	</body>
</html>