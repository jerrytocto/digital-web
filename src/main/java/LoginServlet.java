import dto.ColaboradorDTO;
import dto.TrabajadorDTO;
import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import service.ColaboradorService;
import service.TrabajadorService;
import service.UsuarioService;
import serviceImpl.ColaboradorServiceImpl;
import serviceImpl.TrabajadorServiceImpl;
import serviceImpl.UsuarioServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UsuarioService usuarioService;
    private TrabajadorService trabajadorService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioServiceImpl();
        this.trabajadorService = new TrabajadorServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Autenticación básica (puedes reemplazar por una consulta a BD)
        UsuarioDTO usuarioDto = usuarioService.buscarUsuarioPorUsernamePassword(username, password);
        
        // Mostrar en consola quién se está logueando
        System.out.println("Usuario logueado: " + usuarioDto.getNombres() + " " + usuarioDto.getApellidoPaterno()+ " " + usuarioDto.getIdUsuario() );

        //No se encontró al usuario en la base de datos
        if (usuarioDto == null) {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }

        //Se encontró al usuario en la base de datos
        if (usuarioDto.getUsename().equals(username) && usuarioDto.getPassword().equals(password)) {
            System.out.println("Credenciales correctas");
            HttpSession session = request.getSession();
            String nameUser = usuarioDto.getNombres() + " - " + usuarioDto.getApellidoPaterno();
            session.setAttribute("usuario", nameUser);
            session.setAttribute("usuarioId", usuarioDto.getIdUsuario());
            session.setAttribute("usuarioDto", usuarioDto); // Guarda el DTO completo en sesión

            //Validar si el usuario es un trabajador o un colaborador
            ColaboradorDTO colaborador = usuarioService.isColaborador(usuarioDto.getIdUsuario());

            //El usuario encontrdo es un colaborador
            if (colaborador != null) {
                System.out.println("id: " + colaborador.getIdUsuario());
                session.setAttribute("rol", colaborador.getTipoColaborador()); // ADMIN, ANALISTA, etc.
                session.setAttribute("tipoUsuario", "COLABORADOR");
                System.out.println("Es un colaborador");

            } else {
                //El usuario encontrado es un trabajador  
                System.out.println("id: " + usuarioDto.getIdUsuario());
                System.out.println("Es un trabajador");
                TrabajadorDTO trabajador = trabajadorService.obtenerEmpresaTrabajador(usuarioDto.getIdUsuario());
                if(trabajador == null){
                    System.out.println("Existe algún error en la consulta a la base de datos");
                }

                Integer idEmpresa = trabajador.getCliente().getIdCliente();
                System.out.println("Es un trabajador de la empresa con id : " + idEmpresa);
                session.setAttribute("empresaId", idEmpresa);
                session.setAttribute("tipoUsuario", "TRABAJADOR");
            }

            response.sendRedirect("dashboard");

            //response.sendRedirect("index.jsp"); // Redirigir al dashboard
        } else {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
    }
}
