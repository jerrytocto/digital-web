
<div class="modal fade" id="modalNuevaSolicitud" tabindex="-1" aria-labelledby="modalNuevaSolicitud" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content rounded-3 shadow">

            <!-- Header del modal -->
            <div class="modal-header" style="background-color: var(--primary-color); color: var(--text-light);">
                <h5 class="modal-title" id="modalNuevaSolicitudLabel">
                    <i class="bi bi-person-plus-fill me-2"></i>Nueva Solicitud
                </h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <!-- Cuerpo del modal -->
            <div class="modal-body p-4" style="background-color: var(--secondary-color); color: var(--text-dark);">
                <form id="formSolicitud" action="solicitudes?accion=registrar" method="post" novalidate>
                    <div class="row g-3">
                        <!-- Tipo de Solicitud -->
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Tipo de Solicitud <span class="text-danger">*</span></label>
                            <select id="selectTipoSolicitud" name="tipoSolicitud" class="form-select" required>
                                <option value="">Cargando...</option>
                            </select>
                            <div class="invalid-feedback">Campo obligatorio</div>
                        </div>

                        <!-- Descripción -->
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Descripción <span class="text-danger">*</span></label>
                            <textarea name="descripcion" rows="2" class="form-control" required></textarea>
                            <div class="invalid-feedback">Campo obligatorio</div>
                        </div>

                        <!-- Aplicación -->
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Aplicación <span class="text-danger">*</span></label>
                            <select id="selectAplicacion" name="aplicacion" class="form-select" required>
                                <option value="">Seleccione un cliente primero</option>
                            </select>
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

<script>

    (() => {
        'use strict';
        const form = document.getElementById('formSolicitud');

        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    })();

    document.addEventListener("DOMContentLoaded", function () {
        const modal = document.getElementById("modalNuevaSolicitud");
        const tipoSolicitudSelect = document.getElementById("selectTipoSolicitud");
        const aplicacionSelect = document.getElementById("selectAplicacion");
        const botonAbrirModal = document.getElementById("btnAbrirModalSolicitud");

        const contextPath = "/Digital";
        const empresaId = "${sessionScope.tipoUsuario == 'TRABAJADOR' ? sessionScope.empresaId : ''}";

        if (!botonAbrirModal)
            return; // Si no existe el botón, sal del script sin hacer nada

        botonAbrirModal.addEventListener("click", function (e) {
            e.preventDefault();

            console.log("empresaId de sesión:", empresaId);

            Promise.all([
                fetch(contextPath + "/tipo-solicitudes?accion=listarTodos"),
                fetch(contextPath + "/aplicacion?accion=listarPorCliente&idCliente=" + empresaId)
            ])
                    .then(async ([resTipo, resAplicaciones]) => {
                        const tipos = await resTipo.json();
                        const aplicaciones = await resAplicaciones.json();

                        tipoSolicitudSelect.innerHTML = '<option value="">Seleccione</option>';
                        tipos.forEach(tipo => {
                            const option = document.createElement("option");
                            option.value = tipo.idTipoSolicitud;
                            option.textContent = tipo.nombre;
                            tipoSolicitudSelect.appendChild(option);
                        });

                        aplicacionSelect.innerHTML = '<option value="">Seleccione</option>';
                        if (aplicaciones.length === 0) {
                            aplicacionSelect.innerHTML = '<option value="">No hay aplicaciones disponibles</option>';
                            return;
                        }

                        aplicaciones.forEach(app => {
                            const option = document.createElement("option");
                            option.value = app.idAplicacion;
                            option.textContent = app.nombre;
                            aplicacionSelect.appendChild(option);
                        });

                        const bootstrapModal = new bootstrap.Modal(modal);
                        bootstrapModal.show();
                    })
                    .catch(error => {
                        console.error("Error al cargar datos para el formulario:", error);
                        alert("Ocurrió un error al cargar los datos.");
                    });
        });
    });




</script>