package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvReservaFechas", urlPatterns = {"/SvReservaFechas"})
public class SvReservaFechas extends HttpServlet {

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
        int idHabitacion = Integer. parseInt(request.getParameter("nombre"));
        String checkinCadena = request.getParameter("fecha_checkin");
        String checkoutCadena = request.getParameter("fecha_checkout");
        Date checkin = null;
        Date checkout = null;
        try {
            checkin = new  SimpleDateFormat ( "yyyy-MM-dd" ).parse(checkinCadena);
            checkout = new  SimpleDateFormat ( "yyyy-MM-dd" ).parse(checkoutCadena);
        } catch (ParseException ex) {
            Logger.getLogger(SvReservaFechas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Controladora control = new Controladora();
        boolean autorizada = control.verificarReserva(idHabitacion, checkin, checkout);
        String tipoHabitacion = control.tipoHabitacion(idHabitacion);
        
        if(autorizada == true){
            double precioEstadia = control.calcularPrecioEstadia(checkin, checkout, idHabitacion);
            request.getSession().setAttribute("tipoHabitacion", tipoHabitacion);
            request.getSession().setAttribute("idHabitacion", idHabitacion);
            request.getSession().setAttribute("checkin", checkin);
            request.getSession().setAttribute("checkout", checkout);
             request.getSession().setAttribute("precioEstadia", precioEstadia);
            List <Huesped> listaHuespedes = control.traerHuespedes();
            HttpSession misession = request.getSession();
            misession.setAttribute("listaHuespedes", listaHuespedes);
            response.sendRedirect("pages/elegirHuesped.jsp");
        }
        else {
            response.sendRedirect("pages/reservaFechas.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
