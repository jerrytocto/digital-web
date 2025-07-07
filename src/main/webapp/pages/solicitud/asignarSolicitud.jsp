<!-- Estilos CSS para el modal (agregar al <head> de tu página) -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilosAsignarSolicitud.css">


<!-- Modal Mejorado -->
<div class="modal fade" id="modalAsignarSolicitud" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="formAsignarSolicitud">
                <div class="modal-header text-white">
                    <h5 class="modal-title">
                        <i class="fas fa-users me-2"></i>Asignar Colaboradores a Solicitud
                    </h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idSolicitudAsignar">
                    <input type="hidden" id="esPrimeraAsignacionInput">



                    <!-- Sección de Colaboradores -->
                    <div class="mb-4">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <label class="form-label fw-bold mb-0">
                                <i class="fas fa-user-friends me-2 text-primary"></i>Seleccionar Colaboradores
                            </label>
                            <span id="contadorColaboradores" class="selection-counter" style="display: none;">0</span>
                        </div>
                        <div class="form-text mb-3">Selecciona uno o más colaboradores para asignar a esta solicitud</div>

                        <!-- Loading state -->
                        <div id="loadingColaboradores" class="loading-spinner" style="display: none;">
                            <div class="spinner-border text-primary me-2" role="status"></div>
                            <span>Cargando colaboradores...</span>
                        </div>

                        <!-- Lista de colaboradores -->
                        <div id="listaColaboradores"></div>
                    </div>

                    <!-- Sección de Coordinador -->
                    <div id="seccionCoordinador" class="coordinador-section" style="display: none;">
                        <div class="d-flex align-items-center mb-3">
                            <i class="fas fa-crown me-2 text-success"></i>
                            <h6 class="mb-0 text-success fw-bold">Seleccionar Coordinador</h6>
                        </div>
                        <div class="form-text mb-3">Elige quién será el coordinador de este equipo</div>
                        <div id="listaCoordinadores"></div>
                    </div>

                    <!-- Estado vacío -->
                    <div id="estadoVacio" class="empty-state" style="display: none;">
                        <i class="fas fa-users-slash fa-3x mb-3 text-muted"></i>
                        <h6>No hay colaboradores disponibles</h6>
                        <small>No se encontraron colaboradores para asignar</small>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                        <i class="fas fa-times me-2"></i>Cancelar
                    </button>
                    <button type="submit" class="btn btn-primary" id="btnGuardarAsignacion" disabled>
                        <i class="fas fa-save me-2"></i>Guardar Asignación
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- JavaScript para el modal (agregar antes del cierre de </body>) -->
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const contextPath = "/Digital";
        const rolUsuario = "<%= session.getAttribute("rol") != null ? session.getAttribute("rol") : ""%>";

        const colaboradoresSeleccionados = new Set();
        let coordinadorSeleccionado = null;
        let colaboradoresData = [];
        let esPrimeraAsignacion = true;

        const listaColaboradores = document.getElementById("listaColaboradores");
        const listaCoordinadores = document.getElementById("listaCoordinadores");
        const seccionCoordinador = document.getElementById("seccionCoordinador");
        const contadorColaboradores = document.getElementById("contadorColaboradores");
        const btnGuardar = document.getElementById("btnGuardarAsignacion");
        const loadingColaboradores = document.getElementById("loadingColaboradores");
        const estadoVacio = document.getElementById("estadoVacio");

        function crearColaboradorCard(colaborador, esCoordinador = false) {
            const clean = (str) => str ? String(str).trim() : '';
            const nombres = clean(colaborador.nombres) || 'Sin nombre';
            const apellidoPaterno = clean(colaborador.apellidoPaterno) || '';
            const apellidoMaterno = clean(colaborador.apellidoMaterno) || '';
            const tipoColaborador = clean(colaborador.tipoColaborador) || 'colaborador';
            const tipoDocumento = clean(colaborador.tipoDocumento) || 'DOC';
            const numeroDocumento = clean(colaborador.numeroDocumento) || 'N/A';
            const nombreCompleto = [nombres, apellidoPaterno, apellidoMaterno].filter(Boolean).join(' ');

            const card = document.createElement("div");
            card.className = esCoordinador ? 'coordinador-card' : 'colaborador-card';
            card.dataset.userId = colaborador.idUsuario;

            const infoDiv = document.createElement("div");
            infoDiv.className = "colaborador-info";

            const detailsDiv = document.createElement("div");
            detailsDiv.className = "colaborador-details";

            const nombreH6 = document.createElement("h6");
            nombreH6.textContent = nombreCompleto;

            const docSmall = document.createElement("small");
            docSmall.innerHTML = `<i class="fas fa-id-card me-1"></i>${tipoDocumento}: ${numeroDocumento}`;

            detailsDiv.appendChild(nombreH6);
            detailsDiv.appendChild(docSmall);

            const rightDiv = document.createElement("div");
            rightDiv.className = "d-flex align-items-center";

            const badgeSpan = document.createElement("span");
            badgeSpan.className = `tipo-badge tipo-${tipoColaborador.toLowerCase()} me-2`;
            badgeSpan.textContent = tipoColaborador.charAt(0).toUpperCase() + tipoColaborador.slice(1);

            rightDiv.appendChild(badgeSpan);

            if (esCoordinador) {
                rightDiv.innerHTML += `<i class="fas fa-crown text-warning"></i>`;
            } else {
                const checkIcon = document.createElement("i");
                checkIcon.className = "fas fa-check-circle text-success";
                checkIcon.style.display = colaboradoresSeleccionados.has(colaborador.idUsuario) ? "block" : "none";
                rightDiv.appendChild(checkIcon);
            }

            infoDiv.appendChild(detailsDiv);
            infoDiv.appendChild(rightDiv);
            card.appendChild(infoDiv);

            card.addEventListener("click", () => {
                if (esCoordinador)
                    seleccionarCoordinador(colaborador, card);
                else
                    toggleColaboradorSelection(colaborador, card);
            });

            if (!esCoordinador && colaboradoresSeleccionados.has(colaborador.idUsuario)) {
                card.classList.add("selected");
            }

            return card;
        }

        function toggleColaboradorSelection(colaborador, card) {
            const checkIcon = card.querySelector(".fa-check-circle");
            if (colaboradoresSeleccionados.has(colaborador.idUsuario)) {
                colaboradoresSeleccionados.delete(colaborador.idUsuario);
                card.classList.remove("selected");
                checkIcon.style.display = "none";
            } else {
                colaboradoresSeleccionados.add(colaborador.idUsuario);
                card.classList.add("selected");
                checkIcon.style.display = "block";
            }
            actualizarUI();
        }

        function seleccionarCoordinador(colaborador, card) {
            document.querySelectorAll(".coordinador-card").forEach(c => c.classList.remove("selected"));
            coordinadorSeleccionado = colaborador.idUsuario;
            card.classList.add("selected");
            actualizarUI();
        }

        function actualizarUI() {
            const count = colaboradoresSeleccionados.size;
            contadorColaboradores.textContent = count;
            contadorColaboradores.style.display = count > 0 ? "flex" : "none";
            seccionCoordinador.style.display = count > 0 ? "block" : "none";
            if (count > 0)
                actualizarListaCoordinadores();
            else
                coordinadorSeleccionado = null;
            btnGuardar.disabled = !(count > 0 && coordinadorSeleccionado);
        }

        function actualizarListaCoordinadores() {
            listaCoordinadores.innerHTML = "";
            const seleccionados = colaboradoresData.filter(c => colaboradoresSeleccionados.has(c.idUsuario));
            seleccionados.forEach(colaborador => {
                const card = crearColaboradorCard(colaborador, true);
                if (coordinadorSeleccionado === colaborador.idUsuario)
                    card.classList.add("selected");
                listaCoordinadores.appendChild(card);
            });
        }

        async function cargarColaboradores() {
            try {
                loadingColaboradores.style.display = "flex";
                listaColaboradores.innerHTML = "";
                estadoVacio.style.display = "none";

                const res = await fetch(contextPath + "/colaborador?accion=listarColaboradores");
                const colaboradores = await res.json();

                colaboradoresData = colaboradores;
                if (!colaboradores.length) {
                    estadoVacio.style.display = "block";
                    return;
                }

                colaboradores.forEach(colaborador => {
                    const card = crearColaboradorCard(colaborador);
                    listaColaboradores.appendChild(card);
                });

                actualizarUI();
            } catch (err) {
                console.error("Error al cargar colaboradores:", err);
                listaColaboradores.innerHTML = `<div class="alert alert-danger"><i class="fas fa-exclamation-triangle me-2"></i>Error al cargar colaboradores</div>`;
            } finally {
                loadingColaboradores.style.display = "none";
            }
        }

        async function cargarAsignacionesPrevias(idSolicitud) {
            console.log("Id de la solicitud en la función cargarAsignacionesPrevias:", idSolicitud);
            console.log("Path: ", contextPath+ "/asignar?accion=listarColaboradoresPorSolicitud&idSolicitud=" +idSolicitud)
     
            try {
                const res = await fetch(contextPath+ "/asignar?accion=listarColaboradoresPorSolicitud&idSolicitud=" +idSolicitud);
                                const data = await res.json();
                                console.log("Usuarios asignados para la solicitud ", data);
                                data.asignados.forEach(id => colaboradoresSeleccionados.add(id));
                                coordinadorSeleccionado = data.coordinador;
                            } catch (err) {
                                console.error("Error al obtener asignaciones previas:", err);
                            }
                        }

                        function resetearModal() {
                            colaboradoresSeleccionados.clear();
                            coordinadorSeleccionado = null;
                            listaColaboradores.innerHTML = "";
                            listaCoordinadores.innerHTML = "";
                            seccionCoordinador.style.display = "none";
                            contadorColaboradores.style.display = "none";
                            btnGuardar.disabled = true;
                            estadoVacio.style.display = "none";
                            loadingColaboradores.style.display = "none";
                        }

                        document.addEventListener("click", async (e) => {
                            const btn = e.target.closest(".btn-asignar-solicitud");
                            if (!btn)
                                return;

                            if (rolUsuario !== "admin") {
                                alert("Solo los usuarios con rol ADMIN pueden asignar solicitudes.");
                                return;
                            }

                            const idSolicitud = btn.getAttribute("data-id");
                            const estadoSolicitud = btn.getAttribute("data-estado");
                            esPrimeraAsignacion = estadoSolicitud === "Sin asignar";

                            document.getElementById("idSolicitudAsignar").value = idSolicitud;
                            resetearModal();
                            const modal = new bootstrap.Modal(document.getElementById("modalAsignarSolicitud"));
                            modal.show();

                            if (!esPrimeraAsignacion)
                                await cargarAsignacionesPrevias(idSolicitud);
                            await cargarColaboradores();
                        });

                        document.getElementById("formAsignarSolicitud").addEventListener("submit", async (e) => {
                            e.preventDefault();
                            const idSolicitud = document.getElementById("idSolicitudAsignar").value;
                            const datos = {
                                idSolicitud,
                                colaboradores: Array.from(colaboradoresSeleccionados),
                                coordinador: coordinadorSeleccionado
                            };

                            try {
                                btnGuardar.disabled = true;
                                btnGuardar.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Guardando...';
                                const res = await fetch(contextPath + "/asignar?accion=insertar", {
                                    method: "POST",
                                    headers: {"Content-Type": "application/json"},
                                    body: JSON.stringify(datos)
                                });

                                if (!res.ok)
                                    throw new Error("Error al guardar asignación");

                                alert("¡Asignación guardada exitosamente!");
                                bootstrap.Modal.getInstance(document.getElementById("modalAsignarSolicitud")).hide();
                                actualizarTablaSolicitudes();
                            } catch (err) {
                                console.error("Error al guardar asignación:", err);
                                alert("Error al guardar. Intente nuevamente.");
                            } finally {
                                btnGuardar.disabled = false;
                                btnGuardar.innerHTML = '<i class="fas fa-save me-2"></i>Guardar Asignación';
                            }
                        });

                        async function actualizarTablaSolicitudes() {
                            try {
                                const res = await fetch(contextPath + "/solicitudes?accion=listarTodas", {
                                    headers: {"X-Requested-With": "XMLHttpRequest"}
                                });
                                const html = await res.text();
                                const doc = new DOMParser().parseFromString(html, "text/html");
                                const nuevoTbody = doc.querySelector("#tbodySolicitudes");
                                const actualTbody = document.getElementById("tbodySolicitudes");

                                if (nuevoTbody && actualTbody) {
                                    actualTbody.innerHTML = nuevoTbody.innerHTML;
                                }
                            } catch (err) {
                                console.error("Error al actualizar solicitudes:", err);
                            }
                        }
                    });
</script>
