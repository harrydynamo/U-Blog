<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!--
	TODO: 4.13. Check if user is logged in or not. If not then redirect user to the default page i.e index.jsp.
	(Hint: You need to handle NullPointerException.)
	(Hint: Make use of the email id stored in the session object to check if user is logged in or not.)
-->

<!--
	TODO: 4.14. Design the "Home" page with the following properties.
	    1. Title of the page should be "Home Page"
	    2. If a user is logged in then display the string before @ in the user email id on the web page.
	        For example, if email id is example@gmail.com, then display "Logged In as example" in the
	        top left corner of the web page as shown on the learn platform.
	    3. Provide five hyperlinks to the Create.jsp page, Search.jsp page, Delete.jsp page, Filter.jsp page
	        and Logout.jsp page. They should be provided in the same order as shown on the learn platform with
	        some spacing between them.
-->
<html>
    <head>
        <title>Home Page</title>
    </head>

    <body>
        <%
            if (session.getAttribute("email") == null) {
                response.sendRedirect("index.jsp");
                return;
            }
        %>
        <%
            String username = session.getAttribute("email").toString().substring(0, session.getAttribute("email").toString().indexOf("@"));
            username = (username.charAt(0)+"").toUpperCase()+username.substring(1).toLowerCase();
        %>
        <p>Logged In as <%= username %> </p>
        <ul>
            <li><a href="/ublog/Create.jsp">Create</a></li>
            <li><a href="/ublog/Search.jsp">Search</a></li>
            <li><a href="/ublog/Delete.jsp">Delete</a></li>
            <li><a href="/ublog/Filter.jsp">Filter</a></li>
            <li><a href="/Logout.jsp">Logout</a></li>
        </ul>
    </body>
</html>