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
                    <h5 class="fw-bold">Atenciones Registradas</h5>
                    <div id="contenedorAtenciones" class="mt-3">
                        <p class="text-muted">No hay atenciones registradas.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        const botonesVer = document.querySelectorAll('.btn-ver-detalles-solicitudes');
        const contextPath = "/Digital";

        // Función reutilizable para acceder a propiedades anidadas de forma segura
        function safe(obj, path, fallback = 'No disponible') {
            try {
                return path.split('.').reduce((acc, key) => acc && acc[key], obj) ?? fallback;
            } catch {
                return fallback;
        }
        }


        function crearAtencionCard(atencion) {
            const nombres = safe(atencion, 'asignacion.colaborador.nombres');
            const apellido = safe(atencion, 'asignacion.colaborador.apellidoPaterno');
            const fechaInicio = safe(atencion, 'fechaHoraInicio');
            const fechaFin = safe(atencion, 'fechaHoraFin');
            const descripcion = safe(atencion, 'descripcion');

            const card = document.createElement("div");
            card.className = "card mb-2 shadow-sm";

            const body = document.createElement("div");
            body.className = "card-body p-2";

            const fila1 = document.createElement("div");
            fila1.className = "d-flex justify-content-between";
            fila1.innerHTML = "<strong>Colaborador:</strong><span> " + nombres + " " + apellido + "</span>";

            const fila2 = document.createElement("div");
            fila2.className = "d-flex justify-content-between";
            fila2.innerHTML = "<strong>Inicio:</strong><span>" + fechaInicio + "</span>";

            const fila3 = document.createElement("div");
            fila3.className = "d-flex justify-content-between";
            fila3.innerHTML = "<strong>Fin:</strong><span>" + fechaFin + "</span>";

            const actividadesDiv = document.createElement("div");
            actividadesDiv.className = "mt-2";
            actividadesDiv.innerHTML = "<strong>Actividades:</strong><p class='mb-0'>" + descripcion + "</p>";

            body.appendChild(fila1);
            body.appendChild(fila2);
            body.appendChild(fila3);
            body.appendChild(actividadesDiv);
            card.appendChild(body);

            return card;
        }



        botonesVer.forEach(btn => {
            btn.addEventListener('click', async function () {
                const idSolicitud = btn.dataset.id;
                document.getElementById('detalleId').value = idSolicitud;
                document.getElementById('detalleMotivo').value = btn.dataset.motivo;
                document.getElementById('detalleFecha').value = btn.dataset.fecha;
                document.getElementById('detalleEstado').value = btn.dataset.estado;
                document.getElementById('detalleAplicacion').value = btn.dataset.aplicacion;
                document.getElementById('detalleTipo').value = btn.dataset.tipo;
                document.getElementById('detalleTrabajador').value = btn.dataset.trabajador;

                const estado = btn.dataset.estado.toLowerCase();
                const header = document.getElementById('detalleModalHeader');
                header.className = 'modal-header text-white';

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

                const contenedor = document.getElementById('contenedorAtenciones');
                contenedor.innerHTML = '<p class="text-muted">Cargando atenciones...</p>';

                try {
                    const res = await fetch(contextPath + "/atenciones?accion=listarAtencionesPorSolicitud&idSolicitud=" + idSolicitud);
                    const data = await res.json();
                    console.log("Atenciones recibidas:", data);

                    contenedor.innerHTML = '';

                    if (!data || data.length === 0) {
                        contenedor.innerHTML = '<p class="text-muted">No hay atenciones registradas.</p>';

                    } else {
                        data.forEach(atencion => {
                            const card = crearAtencionCard(atencion);
                            contenedor.appendChild(card);
                        });
                    }

                } catch (err) {
                    console.error('Error al obtener atenciones:', err);
                    contenedor.innerHTML = '<p class="text-danger">Error al cargar las atenciones.</p>';
                }

                const modal = new bootstrap.Modal(document.getElementById('modalDetalleSolicitud'));
                modal.show();
            });
        });
    });
</script>

