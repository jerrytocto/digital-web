<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("pages/login.jsp");
        return;
    }
%>
