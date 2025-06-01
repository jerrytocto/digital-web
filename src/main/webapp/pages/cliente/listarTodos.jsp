

<%-- Valida que el usuario haya iniciado sesión --%>
<%@ include file="/includes/session-check.jsp" %>


<%-- Incluir el header de la página --%>
<%@ include file="/includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="/includes/sidebar.jsp" %>

    <div id="mainContent" class="flex-grow-1 p-3">
        <h2>Lista de clientes</h2> <%-- Contenido por defecto --%>
        <div class="row mb-4">
            <div class="col-12">
                <div class="dashboard-card">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="card-title">Clientes registrados</h5>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNuevoCliente">
                            <i class="bi bi-plus-circle"></i> Nuevo Cliente
                        </button>
                    </div>



                    <!--Inicio div para buscar-->
                    <div class="mb-3">
                        <input type="text" class="form-control" id="searchInput" placeholder="Buscar cliente...">
                    </div>

                    <!--Inicio tabla de clientes-->
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered align-middle" id="tablaClientes">
                            <thead class="table-light">
                                <tr>
                                    <th>Tipo Cliente</th>
                                    <th>Razón Social</th>
                                    <th>Tipo Documento</th>
                                    <th>Número Documento</th>
                                    <th>Teléfono</th>
                                    <th>Correo</th>
                                    <th>Dirección</th>
                                    <th>Fecha Registro</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>


                            <!-- Recorrer la lista de clientes -->
                            <c:forEach var="cliente" items="${clientes}">
                                <tr>
                                    <td>${cliente.tipoCliente}</td>
                                    <td>${cliente.razonSocial}</td>
                                    <td>${cliente.tipoDocumento}</td>
                                    <td>${cliente.numeroDocumento}</td>
                                    <td>${cliente.telefono}</td>
                                    <td>${cliente.correo}</td>
                                    <td>${cliente.direccion}</td>
                                    <td>${cliente.fechaRegistro}</td>
                                    <td class="text-center">
                                        <button class="btn btn-sm btn-outline-info me-1"><i class="bi bi-eye"></i></button>
                                        <button class="btn btn-sm btn-outline-success me-1"><i class="bi bi-pencil-square"></i></button>
                                        <button class="btn btn-sm btn-outline-danger"><i class="bi bi-trash"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!--Fin tabla de solicitudes-->
                </div>
            </div>
        </div>

        <!-- Modal para nuevo cliente -->
        <!-- Modal para nuevo cliente -->
        <div class="modal fade" id="modalNuevoCliente" tabindex="-1" aria-labelledby="modalNuevoClienteLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content rounded-3 shadow">

                    <!-- Header del modal -->
                    <div class="modal-header" style="background-color: var(--primary-color); color: var(--text-light);">
                        <h5 class="modal-title" id="modalNuevoClienteLabel">
                            <i class="bi bi-person-plus-fill me-2"></i>Nuevo Cliente
                        </h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <!-- Cuerpo del modal -->
                    <div class="modal-body p-4" style="background-color: var(--secondary-color); color: var(--text-dark);">
                        <form id="formCliente" action="cliente?accion=registrar" method="post" novalidate>
                            <div class="row g-3">
                                <!-- Tipo de Cliente -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Tipo de Cliente <span class="text-danger">*</span></label>
                                    <select name="tipoCliente" class="form-select" required>
                                        <option value="">Seleccione</option>
                                        <option value="Natural">Natural</option>
                                        <option value="Jurídico">Jurídico</option>
                                    </select>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Razón Social -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Razón Social <span class="text-danger">*</span></label>
                                    <input type="text" name="razonSocial" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Tipo Documento -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Tipo de Documento <span class="text-danger">*</span></label>
                                    <select name="tipoDocumento" class="form-select" required>
                                        <option value="">Seleccione</option>
                                        <option value="DNI">DNI</option>
                                        <option value="RUC">RUC</option>
                                    </select>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Número Documento -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Número de Documento <span class="text-danger">*</span></label>
                                    <input type="text" name="numeroDocumento" class="form-control" required pattern="\d{8,11}" title="Debe contener entre 8 y 11 dígitos">
                                    <div class="invalid-feedback">Debe tener entre 8 y 11 dígitos</div>
                                </div>

                                <!-- Teléfono -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Teléfono <span class="text-danger">*</span></label>
                                    <input type="tel" name="telefono" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Correo -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Correo <span class="text-danger">*</span></label>
                                    <input type="email" name="correo" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese un correo válido</div>
                                </div>

                                <!-- Dirección -->
                                <div class="col-12">
                                    <label class="form-label fw-bold">Dirección <span class="text-danger">*</span></label>
                                    <textarea name="direccion" rows="2" class="form-control" required></textarea>
                                    <div class="invalid-feedback">Campo obligatorio</div>
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
            const rows = document.querySelectorAll("#tablaClientes tbody tr");

            rows.forEach(row => {
                const text = row.innerText.toLowerCase();
                row.style.display = text.includes(value) ? "" : "none";
            });
        });

        (() => {
            'use strict';
            const form = document.getElementById('formCliente');

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
