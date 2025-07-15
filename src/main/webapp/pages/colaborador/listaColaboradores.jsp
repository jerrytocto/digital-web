<%-- Valida que el usuario haya iniciado sesión --%>
<%@ include file="/includes/session-check.jsp" %>


<%-- Incluir el header de la página --%>
<%@ include file="/includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="/includes/sidebar.jsp" %>

    <div id="mainContent" class="flex-grow-1 p-3">
        <h2>Lista de colaboradores</h2> <%-- Contenido por defecto --%>


        <div class="row mb-4">
            <div class="col-12">
                <div class="dashboard-card">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="card-title">Colaboradores registrados</h5>
                        <c:if test="${sessionScope.tipoUsuario eq 'COLABORADOR' and sessionScope.rol=='admin'}">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNuevoColaborador">
                                <i class="bi bi-plus-circle"></i> Nuevo colaborador
                            </button>
                        </c:if>
                    </div>

                    <!--Inicio div para buscar-->
                    <div class="mb-3">
                        <input type="text" class="form-control" id="searchInput" placeholder="Buscar colaborador...">
                    </div>

                    <!--Inicio tabla de clientes-->
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered align-middle" id="tablaColaboradores">
                            <thead class="table-light">
                                <tr>
                                    <th>Tipo Cliente</th>
                                    <th>Correo</th>
                                    <th>Teléfono</th>
                                    <th>Tipo Documento</th>
                                    <th>Número Documento</th>
                                    <th>Fecha Registro</th>
                                    <th>Solicitudes Asignadas</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>


                            <!-- Recorrer la lista de clientes -->
                            <c:forEach var="colaborador" items="${colaboradores}">
                                <tr>
                                    <td>${colaborador.nombres} ${ colaborador.apellidoPaterno}</td>
                                    <td>${colaborador.correo}</td>
                                    <td>${colaborador.telefono}</td>
                                    <td>${colaborador.tipoDocumento}</td>
                                    <td>${colaborador.numeroDocumento}</td>
                                    <td>${colaborador.fechaRegistro}</td>
                                    <td class="text-center">
                                        <a href="solicitudes?accion=solicitudesPorColaborador&id=${colaborador.idUsuario}" 
                                           class="btn btn-sm btn-outline-info me-1">
                                            <i class="bi bi-eye"></i>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <button type="button" 
                                                class="btn btn-sm btn-outline-success me-1 btn-editar-colaborador"
                                                data-id="${colaborador.idUsuario}"
                                                data-nombre="${colaborador.nombres}"
                                                data-apellido-paterno="${colaborador.apellidoPaterno}"
                                                data-apellido-materno="${colaborador.apellidoMaterno}"
                                                data-tipo-documento="${colaborador.tipoDocumento}"
                                                data-numero-documento="${colaborador.numeroDocumento}"
                                                data-telefono="${colaborador.telefono}"
                                                data-correo="${colaborador.correo}"
                                                data-username="${colaborador.usename}"
                                                data-tipo-colaborador="${colaborador.tipoColaborador}">
                                            <i class="bi bi-pencil-square"></i>
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


        <!-- Inicio modal para nuevo colaborador -->
        <div class="modal fade" id="modalNuevoColaborador" tabindex="-1" aria-labelledby="modalNuevoColaboradorLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content rounded-3 shadow">

                    <!-- Header del modal -->
                    <div class="modal-header" style="background-color: var(--primary-color); color: var(--text-light);">
                        <h5 class="modal-title" id="modalNuevoColaboradorLabel">
                            <i class="bi bi-person-plus-fill me-2"></i>Nuevo Colaborador
                        </h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <!-- Cuerpo del modal -->
                    <div class="modal-body p-4" style="background-color: var(--secondary-color); color: var(--text-dark);">
                        <form id="formColaborador" action="colaborador?accion=registrar" method="post" novalidate>
                            <div class="row g-3">

                                <!-- Id de la persona para validar si es un nuevo o edición -->
                                <input type="hidden" name="idUsuario" id="idUsuario">

                                <!-- Nombres -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Nombre <span class="text-danger">*</span></label>
                                    <input type="text" name="nombre" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Apellido paterno -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Apellido paterno <span class="text-danger">*</span></label>
                                    <input type="text" name="apellidoPaterno" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Apellido materno -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Apellido materno <span class="text-danger">*</span></label>
                                    <input type="text" name="apellidoMaterno" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                                <!-- Tipo de documento -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Tipo documento <span class="text-danger">*</span></label>
                                    <select name="tipoDocumento" class="form-select" required>
                                        <option value="">Seleccione</option>
                                        <option value="DNI">DNI</option>
                                        <option value="Carnet de extranjería">Carnet de extranjería</option>
                                    </select>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Número documento -->
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Número documento <span class="text-danger">*</span></label>
                                    <input type="text" name="numeroDocumento" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Teléfono -->
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Teléfono <span class="text-danger">*</span></label>
                                    <input type="tel" name="telefono" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Tipo de Colaborador -->
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Tipo de colaborador <span class="text-danger">*</span></label>
                                    <select name="tipoColaborador" class="form-select" required>
                                        <option value="">Seleccione</option>
                                        <option value="analista">Analista</option>
                                        <option value="programador">Programador</option>
                                        <option value="admin">Admin</option>
                                    </select>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Correo -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Correo <span class="text-danger">*</span></label>
                                    <input type="email" name="correo" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese un correo válido</div>
                                </div>

                                <!-- username -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Username <span class="text-danger">*</span></label>
                                    <input type="text" name="username" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese el username</div>
                                </div>

                                <!-- Contraseña -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Contraseña <span class="text-danger">*</span></label>
                                    <input type="password" name="password" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese su contraseña</div>
                                </div>

                                <!-- Confirmación de contraseña -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Confirmación contraseña <span class="text-danger">*</span></label>
                                    <input type="password" name="confirmacionPassword" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese confirmación de la contraseña </div>
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
        <!--Fin modal para nuevo colaborador-->
    </div>
</div>

<script>
    //Código para realizar la búsqueda
    document.getElementById("searchInput").addEventListener("keyup", function () {
        const value = this.value.toLowerCase();
        const rows = document.querySelectorAll("#tablaColaboradores tbody tr");
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(value) ? "" : "none";
        });
    });

    // código para enviar el formulario
    (() => {
        'use strict';
        const form = document.getElementById('formColaborador');
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    })();


    const nombreInput = document.querySelector("input[name='nombre']");
    const apellidoPaternoInput = document.querySelector("input[name='apellidoPaterno']");
    const usernameInput = document.querySelector("input[name='username']");
    const passwordInput = document.querySelector("input[name='password']");
    const confirmacionInput = document.querySelector("input[name='confirmacionPassword']");
    const form = document.getElementById("formColaborador");

    function actualizarUsername() {
        const nombre = nombreInput.value.trim().toLowerCase().replace(/\s+/g, '');
        const apellido = apellidoPaternoInput.value.trim().toLowerCase().replace(/\s+/g, '');
        usernameInput.value = (nombre && apellido) ? `${nombre}.${apellido}` : "";
            }

            nombreInput.addEventListener("input", actualizarUsername);
            apellidoPaternoInput.addEventListener("input", actualizarUsername);

            form.addEventListener("submit", function (e) {
                const password = passwordInput.value;
                const confirmacion = confirmacionInput.value;

                if (password !== confirmacion) {
                    e.preventDefault();
                    confirmacionInput.setCustomValidity("Las contraseñas no coinciden");
                    confirmacionInput.reportValidity();
                } else {
                    confirmacionInput.setCustomValidity(""); // Limpio
                }
            });


            // Código para tomar los datos del colaborador que se quiere editar
            document.querySelectorAll(".btn-editar-colaborador").forEach(btn => {
                btn.addEventListener("click", () => {
                    // Asignar los valores al formulario
                    document.getElementById("formColaborador").action = "colaborador?accion=editar"; // Cambiar la acción

                    document.getElementById("idUsuario").value = btn.dataset.id;
                    document.querySelector("input[name='nombre']").value = btn.dataset.nombre;
                    document.querySelector("input[name='apellidoPaterno']").value = btn.dataset.apellidoPaterno;
                    document.querySelector("input[name='apellidoMaterno']").value = btn.dataset.apellidoMaterno;
                    document.querySelector("select[name='tipoDocumento']").value = btn.dataset.tipoDocumento;
                    document.querySelector("input[name='numeroDocumento']").value = btn.dataset.numeroDocumento;
                    document.querySelector("input[name='telefono']").value = btn.dataset.telefono;
                    document.querySelector("input[name='correo']").value = btn.dataset.correo;
                    document.querySelector("input[name='username']").value = btn.dataset.username;
                    document.querySelector("select[name='tipoColaborador']").value = btn.dataset.tipoColaborador;

                    // Limpiar campos de contraseña si es edición
                    document.querySelector("input[name='password']").value = "";
                    document.querySelector("input[name='confirmacionPassword']").value = "";

                    // Abrir el modal
                    const modal = new bootstrap.Modal(document.getElementById("modalNuevoColaborador"));
                    modal.show();
                });
            });
</script>

<%-- Incluir el pié de página --%>
<%@ include file="/includes/footer.jsp" %>
