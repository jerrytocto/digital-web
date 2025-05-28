<!-- Sidebar vertical -->
<div id="sidebar">
    <h4><i class="bi bi-grid-1x2 me-2"></i>Menú</h4>
    <div class="accordion accordion-flush" id="accordionSidebar">
        <!-- Dashboard -->
        <div class="accordion-item">
            <div class="nav-link">
                <a href="#" class="nav-link" onclick="loadPage('dashboard.jsp')">
                    <i class="bi bi-house"></i> Dashboard
                </a>
            </div>
        </div>
        
        <!-- Clientes -->
        <div class="accordion-item">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#clientesCollapse">
                    <i class="bi bi-people me-2"></i> Clientes
                </button>
            </h2>
            <div id="clientesCollapse" class="accordion-collapse collapse">
                <div class="accordion-body">
                    <a href="${pageContext.request.contextPath}/pages/clientes.jsp" class="nav-link">
                        <i class="bi bi-list-ul"></i> Listar todos
                    </a>
                    <a href="#" onclick="loadPage('cliente/nuevoCliente.jsp')" class="nav-link">
                        <i class="bi bi-person-plus"></i> Nuevo cliente
                    </a>
                </div>
            </div>
        </div>
        
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
        
        <!-- Solicitudes -->
        <div class="accordion-item">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#solicitudesCollapse">
                    <i class="bi bi-file-earmark-text me-2"></i> Solicitudes
                </button>
            </h2>
            <div id="solicitudesCollapse" class="accordion-collapse collapse">
                <div class="accordion-body">
                    <a href="${pageContext.request.contextPath}/pages/solicitudes.jsp" class="nav-link">
                        <i class="bi bi-card-list"></i> Ver todas
                    </a>
                    <a href="${pageContext.request.contextPath}/pages/solicitudes-pendientes.jsp" class="nav-link">
                        <i class="bi bi-hourglass-split"></i> Pendientes
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
    </div>
</div>