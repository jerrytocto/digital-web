<%-- Valida que el usuario haya iniciado sesión --%>
<%@ include file="/includes/session-check.jsp" %>


<%-- Incluir el header de la página --%>
<%@ include file="/includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="/includes/sidebar.jsp" %>

    <div id="mainContent" class="flex-grow-1 p-3">
        <h2>Lista de solicitudes</h2> <%-- Contenido por defecto --%>

        <div class="row mb-4">
            <div class="col-12">
                <div class="dashboard-card">
                    <div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
                        <!-- Filtro por Estado -->
                        <div>
                            <label for="filtroEstado" class="form-label fw-bold mb-1">Filtrar por Estado:</label>
                            <select id="filtroEstado" class="form-select" style="min-width: 200px;">
                                <option value="">Todos</option>
                                <option value="Sin asignar">Sin asignar</option>
                                <option value="Pendiente">Pendiente</option>
                                <option value="En proceso">En proceso</option>
                                <option value="Cerrado">Cerrado</option>
                            </select>
                        </div>

                        <!-- Filtro por Aplicación -->
                        <div>
                            <label for="filtroAplicacion" class="form-label fw-bold mb-1">Filtrar por Aplicación:</label>
                            <select id="filtroAplicacion" class="form-select" style="min-width: 200px;">
                                <option value="">Todas</option>
                                <c:forEach var="app" items="${aplicaciones}">
                                    <option value="${app.nombre}">${app.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Filtro para Cliente-->

                        <div>
                            <label for="filtroCliente" class="form-label fw-bold mb-1">Filtrar por Cliente:</label>
                            <select id="filtroCliente" class="form-select" style="min-width: 200px;">
                                <option value="">Todas</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.razonSocial}">${cliente.razonSocial}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>




                    <!--Inicio div para buscar-->
                    <div class="mb-3">
                        <input type="text" class="form-control" id="searchInput" placeholder="Buscar Solicitud...">
                    </div>

                    <!--Inicio tabla de clientes-->
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered align-middle" id="tablaSolicitudes">
                            <thead class="table-light">
                                <tr>
                                    <th>Id Solicitud</th>
                                    <th>Cliente</th>
                                    <th>Aplicación</th>
                                    <th>Descripción</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>


                            <!-- Recorrer la lista de clientes -->
                            <c:forEach var="solicitud" items="${solicitudes}">
                                <tr>
                                    <td>${solicitud.idSolicitud}</td>
                                    <td>${solicitud.getTrabajador().getCliente().getRazonSocial()}</td>
                                    <td>${solicitud.getAplicacion().getNombre()}</td>
                                    <td>${solicitud.getMotivo()}</td>
                                    <td>${solicitud.getFechaRegistro()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${solicitud.estado == 'Sin asignar'}">
                                                <span class="badge bg-secondary">Sin asignar</span>
                                            </c:when>
                                            <c:when test="${solicitud.estado == 'Pendiente'}">
                                                <span class="badge bg-warning text-dark">Pendiente</span>
                                            </c:when>
                                            <c:when test="${solicitud.estado == 'En proceso'}">
                                                <span class="badge bg-primary">En proceso</span>
                                            </c:when>
                                            <c:when test="${solicitud.estado == 'Cerrado'}">
                                                <span class="badge bg-success">Cerrado</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-light text-dark">${solicitud.estado}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td class="text-center">
                                        <!--Botón para ver los detalles de la solicitud, este pasa como parámetros los valores de la solicitud-->
                                        <c:choose>
                                            <c:when test="${not empty solicitud.trabajador}">
                                                <button class="btn btn-sm btn-outline-info me-1 btn-ver-detalles-solicitudes"
                                                        data-id="${solicitud.idSolicitud}"
                                                        data-motivo="${solicitud.motivo}"
                                                        data-fecha="${solicitud.fechaRegistro}"
                                                        data-estado="${solicitud.estado}"
                                                        data-aplicacion="${solicitud.aplicacion.nombre}"
                                                        data-tipo="${solicitud.tipoSolicitud.nombre}" 
                                                        data-trabajador="${solicitud.trabajador.nombres} ${solicitud.trabajador.apellidoPaterno}">
                                                    <i class="bi bi-eye"></i>
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-sm btn-outline-info me-1 btn-ver-detalles-solicitudes"
                                                        data-id="${solicitud.idSolicitud}"
                                                        data-motivo="${solicitud.motivo}"
                                                        data-fecha="${solicitud.fechaRegistro}"
                                                        data-estado="${solicitud.estado}"
                                                        data-aplicacion="${solicitud.aplicacion.nombre}"
                                                        data-tipo="${solicitud.tipoSolicitud.nombre}" 
                                                        data-trabajador="No asignado">
                                                    <i class="bi bi-eye"></i>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>

                                        <!--Fin del botón para ver los detalles de dicha solicitud-->

                                        <!--Inicio del botón para editar una solicitud-->
                                        <button class="btn btn-sm btn-outline-success me-1 btn-asignar-solicitud" data-id="${solicitud.idSolicitud}">
                                            <i class="bi bi-pencil-square"></i>
                                        </button>
                                        <!--Fin del botón para editar una solicitud-->

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!--Fin tabla de solicitudes-->

                </div>
            </div>
        </div>

        <!-- Inicio modal para nueva solicitud -->
        <%@ include file="modalNuevaSolicitud.jsp" %>
        <!--Fin modal para nuevo cliente-->

        <!-- Inicio modal para la lista de solicitudes  -->
        <%@ include file="detalleSolicitud.jsp" %>
        <!--Fin modal para la lista de solicitudes-->




    </div>

    <script>
        document.getElementById("searchInput").addEventListener("keyup", function () {
            const value = this.value.toLowerCase();
            const rows = document.querySelectorAll("#tablaSolicitudes tbody tr");

            rows.forEach(row => {
                const text = row.innerText.toLowerCase();
                row.style.display = text.includes(value) ? "" : "none";
            });
        });

        //Código para los filtros
        document.addEventListener('DOMContentLoaded', function () {
            const filtroEstado = document.getElementById('filtroEstado');
            const filtroAplicacion = document.getElementById('filtroAplicacion');
            const filtroCliente = document.getElementById('filtroCliente');
            const tabla = document.getElementById('tablaSolicitudes');
            const filas = tabla.querySelectorAll('tbody tr');

            function aplicarFiltros() {
                const estadoSeleccionado = filtroEstado.value.toLowerCase();
                const appSeleccionada = filtroAplicacion.value.toLowerCase();
                const clienteSeleccionado = filtroCliente.value.toLowerCase();

                filas.forEach(fila => {
                    const estadoFila = fila.children[5].innerText.trim().toLowerCase(); // Estado en la columna 6
                    const appFila = fila.children[2].innerText.trim().toLowerCase();    // Aplicación en la columna 3
                    const clienteFila = fila.children[1].innerText.trim().toLowerCase();    // Aplicación en la columna 2

                    const coincideEstado = !estadoSeleccionado || estadoFila === estadoSeleccionado;
                    const coincideApp = !appSeleccionada || appFila === appSeleccionada;
                    const coincideCliente = !clienteSeleccionado || clienteFila === clienteSeleccionado;

                    fila.style.display = (coincideEstado && coincideApp && coincideCliente) ? '' : 'none';
                });
            }

            filtroEstado.addEventListener('change', aplicarFiltros);
            filtroAplicacion.addEventListener('change', aplicarFiltros);
            filtroCliente.addEventListener('change', aplicarFiltros);
        });

    </script>
</div>


<%-- Incluir el pié de página --%>
<%@ include file="/includes/footer.jsp" %>
