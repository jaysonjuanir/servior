<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FirstServlet</title>
</head>
<body>
Enter Username and Email ID using GET method
<h3>Notice the queryString(uname="name"&email="email" in the URL)</h3>
<form action="FirstServlet" method="get">
Username: <input type="text" name="uname"><br/>
Email ID: <input type="text" name="email"><br/>
<input type="submit"> <br/>
</form>
<br/>
<br/>
<br/>
<br/>

Enter Username and Email ID using POST method
<h3>Notice the queryString is not present in the URL as the query string is sent in the body</h3>
<form action="FirstServlet" method="post">
Username: <input type="text" name="uname"><br/>
Email ID: <input type="text" name="email"><br/>
<input type="submit"> <br/>

</form>
<br/>
<br/>
<p>&copy 2016 Preetham</p>
</body>
</html>