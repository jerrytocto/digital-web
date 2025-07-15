<%-- Valida que el usuario haya iniciado sesión --%>
<%@ include file="/includes/session-check.jsp" %>


<%-- Incluir el header de la página --%>
<%@ include file="/includes/header.jsp" %>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex" id="layout">

    <%-- Incluir el sidebar  --%>
    <%@ include file="/includes/sidebar.jsp" %>

    <div id="mainContent" class="flex-grow-1 p-3">
        <h2>Lista de Trabajadores</h2> <%-- Contenido por defecto --%>


        <div class="row mb-4">
            <div class="col-12">
                <div class="dashboard-card">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="card-title">Colaboradores registrados</h5>
                        <c:if test="${sessionScope.tipoUsuario eq 'COLABORADOR' and sessionScope.rol=='admin'}">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNuevoTrabajador">
                                <i class="bi bi-plus-circle"></i> Nuevo Trabajador
                            </button>
                        </c:if>
                    </div>

                    <!--Inicio div para buscar-->
                    <div class="mb-3">
                        <input type="text" class="form-control" id="searchInput" placeholder="Buscar colaborador...">
                    </div>

                    <!--Inicio tabla de trabajadores-->
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered align-middle" id="tablaTrabajadores">
                            <thead class="table-light">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Cliente</th>
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
                            <c:forEach var="trabajador" items="${trabajadores}">

                                <tr>
                                    <td>${trabajador.nombres} ${ trabajador.apellidoPaterno}</td>
                                    <td>${trabajador.cliente.razonSocial}</td>
                                    <td>${trabajador.correo}</td>
                                    <td>${trabajador.telefono}</td>
                                    <td>${trabajador.tipoDocumento}</td>
                                    <td>${trabajador.numeroDocumento}</td>
                                    <td>${trabajador.fechaRegistro}</td>
                                    <td class="text-center">
                                        <a href="solicitudes?accion=solicitudesPorColaborador&id=${trabajador.idUsuario}" 
                                           class="btn btn-sm btn-outline-info me-1">
                                            <i class="bi bi-eye"></i>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <button type="button" 
                                                class="btn btn-sm btn-outline-success me-1 btn-editar-trabajador"
                                                data-id="${trabajador.idUsuario}"
                                                data-nombre="${trabajador.nombres}"
                                                data-apellido-paterno="${trabajador.apellidoPaterno}"
                                                data-apellido-materno="${trabajador.apellidoMaterno}"
                                                data-tipo-documento="${trabajador.tipoDocumento}"
                                                data-numero-documento="${trabajador.numeroDocumento}"
                                                data-telefono="${trabajador.telefono}"
                                                data-correo="${trabajador.correo}"
                                                data-username="${trabajador.usename}"
                                                data-id-cliente="${trabajador.cliente.idCliente}">

                                            <i class="bi bi-pencil-square"></i>
                                        </button>

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <!--Fin tabla de trabajadores-->

                </div>
            </div>
        </div>

        <!-- Inicio modal para nuevo trabajador -->
        <!-- Modal para nuevo trabajador -->
        <div class="modal fade" id="modalNuevoTrabajador" tabindex="-1" aria-labelledby="modalNuevoTrabajadorLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content rounded-3 shadow">

                    <!-- Header del modal -->
                    <div class="modal-header" style="background-color: var(--primary-color); color: var(--text-light);">
                        <h5 class="modal-title" id="modalNuevoTrabajadorLabel">
                            <i class="bi bi-person-plus-fill me-2"></i>Nuevo Trabajador
                        </h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <!-- Cuerpo del modal -->
                    <div class="modal-body p-4" style="background-color: var(--secondary-color); color: var(--text-dark);">
                        <form id="formTrabajador" action="trabajador?accion=registrar" method="post" novalidate>
                            <div class="row g-3">
                                <!-- Nombres -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Nombre <span class="text-danger">*</span></label>
                                    <input type="text" name="nombreTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Apellido paterno -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Apellido paterno <span class="text-danger">*</span></label>
                                    <input type="text" name="apellidoPaternoTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Apellido materno -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Apellido materno <span class="text-danger">*</span></label>
                                    <input type="text" name="apellidoMaternoTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Tipo de documento -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Tipo documento <span class="text-danger">*</span></label>
                                    <select name="tipoDocumentoTrabajador" class="form-select" required>
                                        <option value="">Seleccione</option>
                                        <option value="DNI">DNI</option>
                                        <option value="Carnet de extranjería">Carnet de extranjería</option>
                                    </select>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Número documento -->
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Número documento <span class="text-danger">*</span></label>
                                    <input type="text" name="numeroDocumentoTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Teléfono -->
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Teléfono <span class="text-danger">*</span></label>
                                    <input type="tel" name="telefonoTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Username (autogenerado) -->
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Username <span class="text-danger">*</span></label>
                                    <input type="text" name="usernameTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Correo -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Correo <span class="text-danger">*</span></label>
                                    <input type="email" name="correoTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Ingrese un correo válido</div>
                                </div>

                                <!-- Contraseña -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Contraseña <span class="text-danger">*</span></label>
                                    <input type="password" name="passwordTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>

                                <!-- Confirmación de contraseña -->
                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Confirmar contraseña <span class="text-danger">*</span></label>
                                    <input type="password" name="confirmacionPasswordTrabajador" class="form-control" required>
                                    <div class="invalid-feedback">Las contraseñas no coinciden</div>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label fw-bold">Cliente <span class="text-danger">*</span></label>
                                    <select name="idClienteTrabajdor" class="form-select" required>
                                        <option value="">Seleccione</option>
                                        <c:forEach var="cliente" items="${clientes}">
                                            <option value="${cliente.idCliente}">${cliente.razonSocial}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>

                            <!-- Footer -->
                            <div class="modal-footer mt-4">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--Fin modal para nuevo trabajador-->
    </div>
</div>


<script>
    //Código para realizar la búsqueda
    document.getElementById("searchInput").addEventListener("keyup", function () {
        const value = this.value.toLowerCase();
        const rows = document.querySelectorAll("#tablaTrabajadores tbody tr");
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(value) ? "" : "none";
        });
    });

    // código para enviar el formulario
    (() => {
        const form = document.getElementById('formTrabajador');

        const nombreInput = form.querySelector("input[name='nombreTrabajador']");
        const apellidoPaternoInput = form.querySelector("input[name='apellidoPaternoTrabajador']");
        const usernameInput = form.querySelector("input[name='usernameTrabajador']");
        const passwordInput = form.querySelector("input[name='passwordTrabajador']");
        const confirmInput = form.querySelector("input[name='confirmacionPasswordTrabajador']");

        // Autocompletar username
        function actualizarUsername() {
            const nombre = nombreInput.value.trim().toLowerCase().replace(/\s+/g, '');
            const apellido = apellidoPaternoInput.value.trim().toLowerCase().replace(/\s+/g, '');
            usernameInput.value = nombre && apellido ? `${nombre}.${apellido}` : "";
                    }

                    nombreInput.addEventListener("input", actualizarUsername);
                    apellidoPaternoInput.addEventListener("input", actualizarUsername);

                    form.addEventListener("submit", function (e) {
                        if (!form.checkValidity()) {
                            e.preventDefault();
                            e.stopPropagation();
                        }

                        if (passwordInput.value !== confirmInput.value) {
                            e.preventDefault();
                            confirmInput.setCustomValidity("Las contraseñas no coinciden");
                            confirmInput.reportValidity();
                        } else {
                            confirmInput.setCustomValidity("");
                        }

                        form.classList.add("was-validated");
                    });
                })();


</script>

<%-- Incluir el pié de página --%>
<%@ include file="/includes/footer.jsp" %>