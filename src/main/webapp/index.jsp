
<%-- Valida que el usuario haya iniciado sesi�n --%>
<%@ include file="includes/session-check.jsp" %>


<%-- Incluir el header de la p�gina --%>
<%@ include file="includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="includes/sidebar.jsp" %>

    <div id="mainContent">

        <%-- Incluir el sidebar  --%>
        <%@ include file="pages/dashboard.jsp" %>

    </div>
</div>


<%-- Incluir el pi� de p�gina --%>
<%@ include file="includes/footer.jsp" %>
