<%-- Valida que el usuario haya iniciado sesión --%>
<%@ include file="/includes/session-check.jsp" %>


<%-- Incluir el header de la página --%>
<%@ include file="/includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="/includes/sidebar.jsp" %>

    <div id="mainContent" class="flex-grow-1 p-3">
        <h2>Lista de aplicaiones</h2> <%-- Contenido por defecto --%>

        <div class="row mb-4">
            <div class="col-12">
                <div class="dashboard-card">
                    <c:if test="${sessionScope.tipoUsuario eq 'COLABORADOR' and sessionScope.rol=='admin'}">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h5 class="card-title">Aplicaciones registradas</h5>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNuevoCliente">
                                <i class="bi bi-plus-circle"></i> Nueva Apliación
                            </button>
                        </div>
                    </c:if>

                    <!--Inicio div para buscar-->
                    <div class="mb-3">
                        <input type="text" class="form-control" id="searchInput" placeholder="Buscar aplicacion...">
                    </div>

                    <!--Inicio tabla de clientes-->
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered align-middle" id="tablaAplicaciones">
                            <thead class="table-light">
                                <tr>
                                    <th>Aplicación</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>


                            <!-- Recorrer la lista de clientes -->
                            <c:forEach var="aplicacion" items="${aplicaciones}">
                                <tr>
                                    <td>${aplicacion.nombre}</td>
                                    <td class="text-center">
                                        <button class="btn btn-sm btn-outline-primary me-1 btn-ver-aplicaciones"
                                                data-id="${aplicacion.idAplicacion}"
                                                title="Lista de aplicaciones">
                                            <i class="bi bi-card-list"></i>
                                        </button>
                                        <button class="btn btn-sm btn-outline-success btn-agregar-aplicacion"
                                                data-id="${aplicacion.idAplicacion}"
                                                title="Agregar aplicación">
                                            <i class="bi bi-plus-circle"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!--Fin tabla de solicitudes-->

                </div>
            </div>
        </div>

        <!-- Inicio modal para nuevo cliente -->
        <div class="modal fade" id="modalNuevoCliente" tabindex="-1" aria-labelledby="modalNuevoClienteLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content rounded-3 shadow">

                    <!-- Header del modal -->
                    <div class="modal-header" style="background-color: var(--primary-color); color: var(--text-light);">
                        <h5 class="modal-title" id="modalNuevoClienteLabel">
                            <i class="bi bi-person-plus-fill me-2"></i>Nueva Aplicación
                        </h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <!-- Cuerpo del modal -->
                    <div class="modal-body p-4" style="background-color: var(--secondary-color); color: var(--text-dark);">
                        <form id="formAplicacion" action="aplicacion?accion=insertar" method="post" novalidate>
                            <div class="row g-3">
                                <!-- nombre aplicación -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Nombre Aplicación <span class="text-danger">*</span></label>
                                    <input type="text" name="nombreAplicacion" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese nombre de la aplicación</div>
                                </div>
                            </div>

                            <!-- Pie del modal -->
                            <div class="modal-footer mt-4" style="background-color: var(--secondary-color); border-top: 1px solid #ddd;">
                                <button type="button" class="btn" style="background-color: var(--primary-dark); color: var(--text-light);" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn" style="background-color: var(--accent-color); color: var(--text-light);">Guardar</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <!--Fin modal para nuevo cliente-->

    </div>

    <script>
        document.getElementById("searchInput").addEventListener("keyup", function () {
            const value = this.value.toLowerCase();
            const rows = document.querySelectorAll("#tablaAplicaciones tbody tr");
            rows.forEach(row => {
                const text = row.innerText.toLowerCase();
                row.style.display = text.includes(value) ? "" : "none";
            });
        });

        (() => {
            'use strict';
            const form = document.getElementById('formAplicacion');
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            });
        })();
    </script>
</div>

<%-- Incluir el pié de página --%>
<%@ include file="/includes/footer.jsp" %>