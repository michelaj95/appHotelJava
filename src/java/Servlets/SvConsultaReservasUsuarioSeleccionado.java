package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import Logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvConsultaReservasUsuarioSeleccionado", urlPatterns = {"/SvConsultaReservasUsuarioSeleccionado"})
public class SvConsultaReservasUsuarioSeleccionado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("UsuarioSeleccionado"));
        Controladora control = new Controladora();
        List<Reserva> listaReservasUsuario = control.traerReservasPorUsuario(idUsuario);
        Usuario usu = control.buscarUsuario(idUsuario);
        String nombreUsuario = usu.getUsuario();
        HttpSession misession = request.getSession();
        misession.setAttribute("listaReservasUsuarioSeleccionado", listaReservasUsuario);
        misession.setAttribute("usuarioSeleccionado", nombreUsuario);
        response.sendRedirect("pages/reservasPorUsuarioSeleccionado.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
