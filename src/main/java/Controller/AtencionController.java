package Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.AsignacionDTO;
import dto.AtencionDTO;
import dto.ColaboradorDTO;
import dto.SolicitudDTO;
import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import service.AtencionService;
import serviceImpl.AtencionServiceImpl;

@WebServlet("/atenciones")
public class AtencionController extends HttpServlet {

    private AtencionService atencionService;

    @Override
    public void init() throws ServletException {
        this.atencionService = new AtencionServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listarAtencionesPorSolicitud":
                    listarAtencionesPorSolicitud(req, resp);
                    break;

                /*case "obtener":
                    obtenerCliente(req, resp);
                    break;
                case "trabajadores":
                    listarTrabajadores(req, resp);
                    break;
                case "aplicaciones":
                    listarAplicaciones(req, resp);
                    break;
                 */
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            e.printStackTrace();

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "insertar":
                    agregarAtencion(req, resp);
                    break;
                /*case "actualizar":
                    actualizarCliente(req, resp);
                    break;
                 */
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    private void agregarAtencion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Leer el JSON del body
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            sb.append(linea);
        }

        String json = sb.toString();
        System.out.println("JSON de atención recibido: " + json);

        Gson gson = new Gson();
        JsonObject data = gson.fromJson(json, JsonObject.class);

        try {
            int idSolicitud = data.get("idSolicitud").getAsInt();
            int idColaborador = data.get("idColaborador").getAsInt();
            String horaInicioStr = data.get("horaInicio").getAsString(); // Ej: "09:00"
            String horaFinStr = data.get("horaFin").getAsString();       // Ej: "10:30"
            String actividades = data.get("actividades").getAsString();

            // Obtener la fecha actual
            LocalDate hoy = LocalDate.now();

            // Combinar fecha actual con hora seleccionada
            LocalDateTime fechaHoraInicio = LocalDateTime.of(hoy, LocalTime.parse(horaInicioStr));
            LocalDateTime fechaHoraFin = LocalDateTime.of(hoy, LocalTime.parse(horaFinStr));

            // Convertir a Timestamp
            Timestamp tsInicio = Timestamp.valueOf(fechaHoraInicio);
            Timestamp tsFin = Timestamp.valueOf(fechaHoraFin);

            // Construir el DTO o entidad
            AtencionDTO atencion = new AtencionDTO();

            ColaboradorDTO colaborador = new ColaboradorDTO();
            colaborador.setIdUsuario(idColaborador);
            AsignacionDTO asignacion = new AsignacionDTO(colaborador, new SolicitudDTO(idSolicitud));

            atencion.setAsignacion(asignacion);
            atencion.setFechaHoraInicio(tsInicio);
            atencion.setFechaHoraFin(tsFin);
            atencion.setDescripcion(actividades);

            // Guardar en BD
            boolean exito = atencionService.insertarAtencion(atencion);

            // Respuesta
            resp.setContentType("application/json");
            if (exito) {
                resp.getWriter().write("{\"mensaje\":\"Atención registrada exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"mensaje\":\"No se pudo registrar la atención\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"mensaje\":\"Error al procesar la atención\"}");
        }
    }

    private void listarAtencionesPorSolicitud(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idSolicitud = Integer.parseInt(req.getParameter("idSolicitud"));

        List<AtencionDTO> lista = atencionService.listAtencionesPorSolicitud(idSolicitud);
        System.out.println("Lista de atenciones" + lista);

        // Convertir a JSON
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(lista));
    }

}
