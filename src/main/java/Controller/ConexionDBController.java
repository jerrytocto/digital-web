
package Controller;

import com.last.digital.resources.config.ConnectionDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "PruebaConexionServlet", urlPatterns = {"/probarConexion"})
public class ConexionDBController extends HttpServlet{
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            Connection conn = ConnectionDB.getConexion();

            if (conn != null && !conn.isClosed()) {
                out.println("<h2 style='color:green;'>¡Conexión a la base de datos establecida correctamente!</h2>");
            } else {
                out.println("<h2 style='color:red;'>No se pudo establecer conexión.</h2>");
            }

            ConnectionDB.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2 style='color:red;'>Error al conectar: " + e.getMessage() + "</h2>");
        }
    }
}
