
<%-- Valida que el usuario haya iniciado sesión --%>
<%@ include file="includes/session-check.jsp" %>


<%-- Incluir el header de la página --%>
<%@ include file="includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="includes/sidebar.jsp" %>

    <div id="mainContent" class="flex-grow-1 p-3">
        <%@ include file="pages/dashboard.jsp" %> <%-- Contenido por defecto --%>
    </div>
</div>


<%-- Incluir el pié de página --%>
<%@ include file="includes/footer.jsp" %>
