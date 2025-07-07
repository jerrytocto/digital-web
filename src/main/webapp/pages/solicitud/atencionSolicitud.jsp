<!-- Modal para completar atención -->
<div class="modal fade" id="modalAtenderSolicitud" tabindex="-1" aria-labelledby="atencionLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content border-0 shadow-lg">

            <!-- Encabezado -->
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title" id="atencionLabel">
                    <i class="bi bi-tools me-2"></i>Atender Solicitud
                </h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>

            <!-- Cuerpo del modal -->
            <div class="modal-body bg-light">
                <form id="formAtencionSolicitud">
                    <input type="hidden" id="atencionIdSolicitud">
                    <input type="hidden" id="atencionIdColaborador">

                    <div class="row g-3">
                        <!-- Datos de la solicitud -->
                        <div class="col-md-12">
                            <label class="form-label fw-bold">Motivo</label>
                            <textarea class="form-control" id="atencionMotivo" rows="2" readonly></textarea>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Estado</label>
                            <input type="text" class="form-control" id="atencionEstado" readonly>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Aplicación</label>
                            <input type="text" class="form-control" id="atencionAplicacion" readonly>
                        </div>

                        <!-- Campos para registrar atención -->
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Hora de Inicio</label>
                            <input type="time" class="form-control" id="horaInicio" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Hora de Fin</label>
                            <input type="time" class="form-control" id="horaFin" required>
                        </div>
                        <div class="col-md-12">
                            <label class="form-label fw-bold">Actividades Realizadas</label>
                            <textarea class="form-control" id="actividadesRealizadas" rows="3" required></textarea>
                        </div>
                        <hr>
                        
                    </div>
                </form>
            </div>

            <!-- Pie del modal -->
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                    Cancelar
                </button>
                <button type="submit" form="formAtencionSolicitud" class="btn btn-success">
                    <i class="bi bi-check-circle me-1"></i>Guardar Atención
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const botonesAtencion = document.querySelectorAll('.btn-atender-solicitud');
        const contextPath = "/Digital";

        botonesAtencion.forEach(btn => {
            btn.addEventListener('click', function () {
                // Setear datos de la solicitud en el modal
                document.getElementById('atencionIdSolicitud').value = btn.dataset.id;
                document.getElementById('atencionIdColaborador').value = btn.dataset.idcolaboradoreditor;
                document.getElementById('atencionMotivo').value = btn.dataset.motivo;
                document.getElementById('atencionEstado').value = btn.dataset.estado;
                document.getElementById('atencionAplicacion').value = btn.dataset.aplicacion;

                // Limpiar campos de atención
                document.getElementById('horaInicio').value = "";
                document.getElementById('horaFin').value = "";
                document.getElementById('actividadesRealizadas').value = "";

                // Mostrar modal
                const modal = new bootstrap.Modal(document.getElementById('modalAtenderSolicitud'));
                modal.show();
            });
        });

        // Enviar datos al backend
        const form = document.getElementById('formAtencionSolicitud');
        form.addEventListener('submit', async function (e) {
            e.preventDefault();

            const payload = {
                idSolicitud: document.getElementById('atencionIdSolicitud').value,
                idColaborador: document.getElementById('atencionIdColaborador').value,
                horaInicio: document.getElementById('horaInicio').value,
                horaFin: document.getElementById('horaFin').value,
                actividades: document.getElementById('actividadesRealizadas').value
            };
            console.log("Datos enviados para la atención : ", payload);
            try {
                const res = await fetch(contextPath + "/atenciones?accion=insertar", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(payload)
                });

                if (res.ok) {
                    alert("Atención registrada con éxito.");
                    bootstrap.Modal.getInstance(document.getElementById('modalAtenderSolicitud')).hide();
                    // Opcional: recargar la lista
                    location.reload();
                } else {
                    alert("Error al registrar la atención.");
                }

            } catch (error) {
                console.error("Error en la solicitud:", error);
                alert("Error inesperado.");
            }
        });
    });
</script>