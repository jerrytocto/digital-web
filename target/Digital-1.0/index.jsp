<%@ include file="includes/header.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">
    <%@ include file="includes/sidebar.jsp" %>
    <div id="mainContent">
        <div class="container-fluid">
            <div class="row mb-4">
                <div class="col-12">
                    <h2 class="mb-4">Dashboard</h2>


                    <%-- Mostrar error si existe --%>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${error}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                </div>

                <!-- Tarjetas de resumen --%>
                            <div class="row mb-4">
                <!-- Inicio resumen clientes -->
                <div class="col-lg-3 col-md-6 mb-3">
                    <div class="dashboard-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="card-title">Clientes</h5>
                                <h3>${numeroClientes != null ? numeroClientes : '0'}</h3>
                                <p class="text-success mb-0"><i class="bi bi-arrow-up"></i> 12% este mes</p>
                            </div>
                            <div class="bg-primary bg-opacity-10 p-3 rounded-circle">
                                <i class="bi bi-people fs-1 text-primary"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fin resumen clientes -->

                <!-- Inicio resumen Aplicaciones -->
                <div class="col-lg-3 col-md-6 mb-3">
                    <div class="dashboard-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="card-title">Aplicaciones</h5>
                                <h3>${numeroAplicaciones != null ? numeroAplicaciones : '0'}</h3>
                                <p class="text-success mb-0"><i class="bi bi-arrow-up"></i> 8% este mes</p>
                            </div>
                            <div class="bg-info bg-opacity-10 p-3 rounded-circle">
                                <i class="bi bi-app fs-1 text-info"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fin resumen Aplicaciones -->

                <!-- Inicio resumen solicitudes -->
                <div class="col-lg-3 col-md-6 mb-3">
                    <div class="dashboard-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="card-title">Solicitudes</h5>
                                <h3>${totalSolicitudes != null ? totalSolicitudes : '0'}</h3>
                                <p class="text-warning mb-0"><i class="bi bi-arrow-down"></i> 5% este mes</p>
                            </div>
                            <div class="bg-warning bg-opacity-10 p-3 rounded-circle">
                                <i class="bi bi-file-earmark-text fs-1 text-warning"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fin resumen Solicitudes -->

                <!-- Inicio resumen solicitude pendientes -->
                <div class="col-lg-3 col-md-6 mb-3">
                    <div class="dashboard-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="card-title">Pendientes</h5>
                                <h3>${totalSolicitudesPendientes != null ? totalSolicitudesPendientes : '0'}</h3>
                                <p class="text-danger mb-0"><i class="bi bi-exclamation-triangle"></i> Requiere atención</p>
                            </div>
                            <div class="bg-danger bg-opacity-10 p-3 rounded-circle">
                                <i class="bi bi-hourglass-split fs-1 text-danger"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Inicio resumen solicitude pendientes -->
            </div>
            <!-- Fin tarjeta de solicitudes -->

            <!-- Tabla de últimas solicitudes -->
            <div class="row mb-4">
                <div class="col-12">
                    <div class="dashboard-card">
                        <h5 class="card-title">Últimas solicitudes</h5>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Cliente</th>
                                        <th>Aplicación</th>
                                        <th>Fecha</th>
                                        <th>Estado</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>#1254</td>
                                        <td>Empresa ABC</td>
                                        <td>Sistema Inventario</td>
                                        <td>18/05/2025</td>
                                        <td><span class="badge bg-success">Completado</span></td>
                                        <td>
                                            <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#1253</td>
                                        <td>Corp XYZ</td>
                                        <td>CRM Personalizado</td>
                                        <td>17/05/2025</td>
                                        <td><span class="badge bg-warning text-dark">En proceso</span></td>
                                        <td>
                                            <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#1252</td>
                                        <td>Servicios Técnicos</td>
                                        <td>App Móvil</td>
                                        <td>16/05/2025</td>
                                        <td><span class="badge bg-danger">Pendiente</span></td>
                                        <td>
                                            <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#1251</td>
                                        <td>Distribuidora Central</td>
                                        <td>Portal Web</td>
                                        <td>15/05/2025</td>
                                        <td><span class="badge bg-success">Completado</span></td>
                                        <td>
                                            <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i></button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-end">
                            <a href="${pageContext.request.contextPath}/pages/solicitudes.jsp" class="btn btn-primary">Ver todas</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Fin tabla de últimas solicitudes -->
        </div>
    </div>
</div>
<%@ include file="includes/footer.jsp" %>