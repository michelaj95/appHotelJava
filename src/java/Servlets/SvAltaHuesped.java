package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import Logica.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvAltaHuesped", urlPatterns = {"/SvAltaHuesped"})
public class SvAltaHuesped extends HttpServlet {

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
        String dni = request.getParameter("dni");
        int cantidadHuespedes = Integer.parseInt(request.getParameter("cantidad_huespedes"));
        String nombre = request.getParameter("nombreHuesped");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String profesion = request.getParameter("profesion");
        String fechaNacCadena = request.getParameter("fecha_nac");
        Date checkin = (Date) request.getSession().getAttribute("checkin");
        Date checkout = (Date) request.getSession().getAttribute("checkout");
        int idHabitacion = (int) request.getSession().getAttribute("idHabitacion");
        Date fechaNac = null;
        try {
            fechaNac = new  SimpleDateFormat ( "yyyy-MM-dd" ).parse(fechaNacCadena);
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HttpSession misession = request.getSession();
        int idUsuario = (int) misession.getAttribute("idUsuario");
        Controladora control = new Controladora();
        control.crearHuesped(dni, nombre, apellido, direccion, profesion, fechaNac);
        int idHuesped = control.traerIdNuevoHuesped();
        control.crearReserva(checkin, checkout, cantidadHuespedes, idHabitacion, idUsuario, idHuesped);
        response.sendRedirect("pages/menuprincipal.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
