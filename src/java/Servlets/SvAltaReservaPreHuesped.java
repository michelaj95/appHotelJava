
package Servlets;

import Logica.Controladora;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvAltaReservaPreHuesped", urlPatterns = {"/SvAltaReservaPreHuesped"})
public class SvAltaReservaPreHuesped extends HttpServlet {

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
        int idHuesped = Integer. parseInt(request.getParameter("huespedElegido"));
        Date checkin = (Date) request.getSession().getAttribute("checkin");
        Date checkout = (Date) request.getSession().getAttribute("checkout");
        int idHabitacion = (int) request.getSession().getAttribute("idHabitacion");
        int cantidadHuespedes = Integer.parseInt(request.getParameter("cantidad_huespedes"));
        HttpSession misession = request.getSession();
        int idUsuario = (int) misession.getAttribute("idUsuario");
        Controladora control = new Controladora();
        control.crearReserva(checkin, checkout, cantidadHuespedes, idHabitacion, idUsuario, idHuesped);
        response.sendRedirect("pages/menuprincipal.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
