<!-- Modal Detalle Solicitud -->
<div class="modal fade" id="modalDetalleSolicitud" tabindex="-1" aria-labelledby="detalleLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content border-0 shadow-lg">

            <!-- Encabezado con color dinámico -->
            <div class="modal-header text-white" id="detalleModalHeader">
                <h5 class="modal-title" id="detalleLabel">Detalle de la Solicitud</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <!-- Cuerpo del modal -->
            <div class="modal-body bg-light">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label fw-bold">ID Solicitud</label>
                        <input type="text" class="form-control" id="detalleId" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Fecha de Registro</label>
                        <input type="text" class="form-control" id="detalleFecha" readonly>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label fw-bold">Motivo</label>
                        <textarea class="form-control" id="detalleMotivo" rows="2" readonly></textarea>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Estado</label>
                        <input type="text" class="form-control" id="detalleEstado" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Aplicación</label>
                        <input type="text" class="form-control" id="detalleAplicacion" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Tipo de Solicitud</label>
                        <input type="text" class="form-control" id="detalleTipo" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Trabajador</label>
                        <input type="text" class="form-control" id="detalleTrabajador" readonly>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        const botonesVer = document.querySelectorAll('.btn-ver-detalles-solicitudes');

        botonesVer.forEach(btn => {
            btn.addEventListener('click', function () {
                // Obtener atributos del botón
                document.getElementById('detalleId').value = btn.dataset.id;
                document.getElementById('detalleMotivo').value = btn.dataset.motivo;
                document.getElementById('detalleFecha').value = btn.dataset.fecha;
                document.getElementById('detalleEstado').value = btn.dataset.estado;
                document.getElementById('detalleAplicacion').value = btn.dataset.aplicacion;
                document.getElementById('detalleTipo').value = btn.dataset.tipo;
                document.getElementById('detalleTrabajador').value = btn.dataset.trabajador;

                // Cambiar color de encabezado según estado
                const estado = btn.dataset.estado.toLowerCase();
                const header = document.getElementById('detalleModalHeader');
                header.className = 'modal-header text-white'; // reset base classes

                switch (estado) {
                    case 'sin asignar':
                        header.classList.add('bg-secondary');
                        break;
                    case 'pendiente':
                        header.classList.add('bg-warning', 'text-dark');
                        break;
                    case 'en proceso':
                        header.classList.add('bg-primary');
                        break;
                    case 'cerrado':
                        header.classList.add('bg-success');
                        break;
                    default:
                        header.classList.add('bg-light', 'text-dark');
                }

                // Mostrar el modal
                const modal = new bootstrap.Modal(document.getElementById('modalDetalleSolicitud'));
                modal.show();
            });
        });
    });
</script>
