
import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import service.UsuarioService;
import serviceImpl.UsuarioServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Autenticación básica (puedes reemplazar por una consulta a BD)
        UsuarioDTO usuarioDto = usuarioService.buscarUsuarioPorUsernamePassword(username, password);

        //No se encontró al usuario en la base de datos
        if (usuarioDto == null) {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }

        if (usuarioDto.getUsename().equals(username) && usuarioDto.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            String nameUser = usuarioDto.getNombres() + " - "+ usuarioDto.getApellidoPaterno();
            session.setAttribute("usuario", nameUser); 
            response.sendRedirect("dashboard");

            //response.sendRedirect("index.jsp"); // Redirigir al dashboard
        } else {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
    }
}
