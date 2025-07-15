

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
                                        <button class="btn btn-sm btn-outline-primary me-1 btn-ver-aplicaciones"
                                                data-id="${cliente.idCliente}"
                                                title="Lista de aplicaciones">
                                            <i class="bi bi-card-list"></i>
                                        </button>
                                        <button class="btn btn-sm btn-outline-success btn-agregar-aplicacion"
                                                data-id="${cliente.idCliente}"
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

        <!-- Inicio del modal para ver Aplicaciones pro cliente -->
        <div class="modal fade" id="modalAplicacionesCliente" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title" id="tituloModalAplicaciones">Aplicaciones</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div id="listaAplicacionesCliente">
                            <!-- Aquí se insertarán las aplicaciones con JS -->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fin del modal para ver Aplicaciones -->

        <!-- Inicio del modal para Añadir Aplicación a un cliente-->
        <div class="modal fade" id="modalAgregarAplicacion" tabindex="-1" aria-labelledby="modalAgregarAplicacionLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="modalAgregarAplicacionLabel">Agregar Aplicación</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formAgregarAplicacion">
                            <input type="hidden" id="idClienteAgregar">
                            <div class="mb-3">
                                <label for="selectAplicaciones" class="form-label">Seleccione una aplicación:</label>
                                <select class="form-select" id="selectAplicaciones" required>
                                    <option value="">-- Seleccione una aplicación --</option>
                                    <!-- Las opciones se cargarán dinámicamente -->
                                </select>
                            </div>
                        </form>
                        <div id="mensajeAplicacion" class="mt-2"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" form="formAgregarAplicacion" class="btn btn-success">
                            <i class="bi bi-plus-circle me-1"></i> Agregar
                        </button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fin del modal para Añadir Aplicación -->

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

        // Ver las aplicaciones que tiene una empresa-cliente
        document.addEventListener('DOMContentLoaded', function () {
            const contextPath = "/Digital"; // Ajusta si es necesario

            document.querySelectorAll('.btn-ver-aplicaciones').forEach(boton => {
                boton.addEventListener('click', async () => {
                    const idCliente = boton.dataset.id;
                    const nombreCliente = boton.dataset.nombre;

                    try {
                        const res = await fetch(contextPath + "/aplicacion?accion=listarPorCliente&idCliente=" + idCliente);
                        if (!res.ok)
                            throw new Error("Error al obtener aplicaciones");

                        const aplicaciones = await res.json();
                        console.log("Aplicaciones del cliente:", aplicaciones);

                        const contenedor = document.getElementById("listaAplicacionesCliente");
                        const titulo = document.getElementById("tituloModalAplicaciones");

                        titulo.textContent = `Aplicaciones de ${nombreCliente}`;
                        contenedor.innerHTML = "";

                        if (aplicaciones.length === 0) {
                            contenedor.innerHTML = "<p class='text-muted'>No hay aplicaciones registradas para este cliente.</p>";
                        } else {
                            const ul = document.createElement("ul");
                            ul.className = "list-group";

                            aplicaciones.forEach(app => {
                                const li = document.createElement("li");
                                li.className = "list-group-item";
                                li.textContent = app.nombre;
                                ul.appendChild(li);
                            });

                            contenedor.appendChild(ul);
                        }

                        const modal = new bootstrap.Modal(document.getElementById('modalAplicacionesCliente'));
                        modal.show();

                    } catch (err) {
                        console.error("Error al cargar aplicaciones:", err);
                        alert("Hubo un error al cargar las aplicaciones.");
                    }
                });
            });
        });





        // Modal para vender una aplicación para un cliente
        document.addEventListener("DOMContentLoaded", () => {
            const contextPath = "/Digital";

            const botonesAgregar = document.querySelectorAll(".btn-agregar-aplicacion");
            const selectAplicaciones = document.getElementById("selectAplicaciones");
            const inputIdCliente = document.getElementById("idClienteAgregar");
            const mensaje = document.getElementById("mensajeAplicacion");
            const modalAgregar = new bootstrap.Modal(document.getElementById("modalAgregarAplicacion"));

            botonesAgregar.forEach(btn => {
                btn.addEventListener("click", async () => {
                    const idCliente = btn.getAttribute("data-id");
                    inputIdCliente.value = idCliente;
                    mensaje.innerHTML = "";

                    try {
                        const res = await fetch(contextPath + "/aplicacion?accion=listarNoCompradas&idCliente="+idCliente);
                        const aplicaciones = await res.json();

                        // Limpiar y cargar las opciones
                        selectAplicaciones.innerHTML = `<option value="">-- Seleccione una aplicación --</option>`;
                        aplicaciones.forEach(app => {
                            const option = document.createElement("option");
                            option.value = app.idAplicacion;
                            option.textContent = app.nombre;
                            selectAplicaciones.appendChild(option);
                        });

                        modalAgregar.show();
                    } catch (err) {
                        console.error("Error al cargar aplicaciones:", err);
                        mensaje.innerHTML = `<div class="alert alert-danger">Error al cargar aplicaciones</div>`;
                    }
                });
            });

            document.getElementById("formAgregarAplicacion").addEventListener("submit", async (e) => {
                e.preventDefault();
                mensaje.innerHTML = "";

                const idAplicacion = selectAplicaciones.value;
                const idCliente = inputIdCliente.value;

                if (!idAplicacion) {
                    mensaje.innerHTML = `<div class="alert alert-warning">Seleccione una aplicación.</div>`;
                    return;
                }

                try {
                    const res = await fetch(contextPath + "/venta?accion=registrar", {
                        method: "POST",
                        headers: {"Content-Type": "application/json"},
                        body: JSON.stringify({
                            idCliente: parseInt(idCliente),
                            idAplicacion: parseInt(idAplicacion)
                        })
                    });

                    if (!res.ok)
                        throw new Error("No se pudo guardar la aplicación");

                    mensaje.innerHTML = `<div class="alert alert-success">Aplicación agregada correctamente.</div>`;
                    setTimeout(() => modalAgregar.hide(), 1500);

                } catch (err) {
                    console.error("Error al guardar aplicación:", err);
                    mensaje.innerHTML = `<div class="alert alert-danger">Error al guardar. Intente nuevamente.</div>`;
                }
            });
        });
    </script>

</div>


<%-- Incluir el pié de página --%>
<%@ include file="/includes/footer.jsp" %>
