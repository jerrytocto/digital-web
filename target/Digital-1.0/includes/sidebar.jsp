
<!-- Sidebar vertical -->
<div id="sidebar">
    <h4><i class="bi bi-grid-1x2 me-2"></i>Menú</h4>
    <div class="accordion accordion-flush" id="accordionSidebar">

        <!-- Dashboard: solo para COLABORADOR ADMIN -->
        <c:if test="${sessionScope.tipoUsuario eq 'COLABORADOR' and sessionScope.rol=='admin'}">
            <div class="accordion-item">
                <div class="nav-link">
                    <a href="${pageContext.request.contextPath}/dashboard" class="nav-link">
                        <i class="bi bi-house"></i> Dashboard
                    </a>
                </div>
            </div>
        </c:if>

        <!-- Clientes -->
        <c:if test="${sessionScope.tipoUsuario eq 'COLABORADOR'}">
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#clientesCollapse">
                        <i class="bi bi-people me-2"></i> Clientes
                    </button>
                </h2>
                <div id="clientesCollapse" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <a href="${pageContext.request.contextPath}/cliente?accion=listar" class="nav-link">
                            <i class="bi bi-list-ul"></i> Listar todos
                        </a>
                        <c:if test="${sessionScope.rol=='admin'}">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNuevoCliente">
                                <i class="bi bi-person-plus"></i> Nuevo cliente
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- Solicitudes: accesible para todos -->
        <div class="accordion-item">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#solicitudesCollapse">
                    <i class="bi bi-file-earmark-text me-2"></i> Solicitudes
                </button>
            </h2>
            <div id="solicitudesCollapse" class="accordion-collapse collapse">
                <div class="accordion-body">
                    <a href="${pageContext.request.contextPath}/solicitudes?accion=listarTodas" class="nav-link">
                        <i class="bi bi-card-list"></i> Ver todas
                    </a>
                    <c:if test="${sessionScope.tipoUsuario ne 'TRABAJADOR' and sessionScope.rol=='admin'}">
                        <a href="${pageContext.request.contextPath}/pages/solicitudes-pendientes.jsp" class="nav-link">
                            <i class="bi bi-hourglass-split"></i> Pendientes
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.tipoUsuario eq 'TRABAJADOR'}">
                        <a href="${pageContext.request.contextPath}/pages/solicitudes-pendientes.jsp" class="nav-link">
                            <i class="bi bi-hourglass-split"></i> Pendientes
                        </a>
                        <button type="button" class="btn btn-primary" id="btnAbrirModalSolicitud">
                            <i class="bi bi-plus-circle"></i> Nueva solicitud
                        </button>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Aplicaciones, Reportes, Asignaciones, Configuración: solo para COLABORADOR ADMIN -->
        <c:if test="${sessionScope.tipoUsuario eq 'COLABORADOR' and sessionScope.rol=='admin'}">
            <!-- Aplicaciones -->
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#appsCollapse">
                        <i class="bi bi-app me-2"></i> Aplicaciones
                    </button>
                </h2>
                <div id="appsCollapse" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <a href="${pageContext.request.contextPath}/pages/aplicaciones.jsp" class="nav-link">
                            <i class="bi bi-grid"></i> Ver todas
                        </a>
                        <a href="${pageContext.request.contextPath}/pages/aplicaciones-nueva.jsp" class="nav-link">
                            <i class="bi bi-plus-square"></i> Nueva aplicación
                        </a>
                    </div>
                </div>
            </div>

            <!-- Reportes -->
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#reportesCollapse">
                        <i class="bi bi-bar-chart me-2"></i> Reportes
                    </button>
                </h2>
                <div id="reportesCollapse" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <a href="${pageContext.request.contextPath}/pages/reportes-general.jsp" class="nav-link">
                            <i class="bi bi-graph-up"></i> General
                        </a>
                        <a href="${pageContext.request.contextPath}/pages/reportes-mensual.jsp" class="nav-link">
                            <i class="bi bi-calendar-month"></i> Mensual
                        </a>
                    </div>
                </div>
            </div>

            <!-- Asignaciones -->
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#asignacionesCollapse">
                        <i class="bi bi-bar-chart me-2"></i> Asignaciones
                    </button>
                </h2>
                <div id="asignacionesCollapse" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <a href="${pageContext.request.contextPath}/pages/asignaciones-nueva.jsp" class="nav-link">
                            <i class="bi bi-graph-up"></i> Nueva Asignación
                        </a>
                    </div>
                </div>
            </div>

            <!-- Configuración -->
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#configCollapse">
                        <i class="bi bi-gear me-2"></i> Configuración
                    </button>
                </h2>
                <div id="configCollapse" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <a href="${pageContext.request.contextPath}/pages/configuracion-usuarios.jsp" class="nav-link">
                            <i class="bi bi-person-gear"></i> Usuarios
                        </a>
                        <a href="${pageContext.request.contextPath}/pages/configuracion-sistema.jsp" class="nav-link">
                            <i class="bi bi-sliders"></i> Sistema
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

    </div>
</div>
