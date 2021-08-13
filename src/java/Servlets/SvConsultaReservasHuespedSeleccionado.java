package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import Logica.Reserva;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@WebServlet(name = "SvConsultaReservasHuespedSeleccionado", urlPatterns = {"/SvConsultaReservasHuespedSeleccionado"})
public class SvConsultaReservasHuespedSeleccionado extends HttpServlet {

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
       String fechaPrimeraCadena = request.getParameter("fechaPrimera");
       System.out.println("fechaprimeracadena: " + fechaPrimeraCadena);
       String fechaSegundaCadena = request.getParameter("fechaSegunda");
       int idHuesped = Integer.parseInt(request.getParameter("HuespedSeleccionado"));
       Date fechaPrimera = null;
       Date fechaSegunda = null;
        try {
            fechaPrimera = new  SimpleDateFormat ( "dd-MM-yyyy" ).parse(fechaPrimeraCadena);
            fechaSegunda = new  SimpleDateFormat ( "dd-MM-yyyy" ).parse(fechaSegundaCadena);
        } catch (ParseException ex) {
            Logger.getLogger(SvConsultaReservasHuespedSeleccionado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Controladora control = new Controladora();
        List <Reserva> listaReservasFiltradas = control.traerReservasPorFecha(idHuesped, fechaPrimera, fechaSegunda);
        Huesped hues = control.buscarHuesped(idHuesped);
        String huespedSeleccionado = hues.getNombre() + " " + hues.getApellido();
        HttpSession misession = request.getSession();
        misession.setAttribute("fechaPrimera", fechaPrimeraCadena);
        misession.setAttribute("fechaSegunda", fechaSegundaCadena);
        misession.setAttribute("huespedSeleccionado", huespedSeleccionado);
        misession.setAttribute("listaReservasHuesped", listaReservasFiltradas);
        System.out.println(listaReservasFiltradas);
        response.sendRedirect("pages/reservasHuespedSeleccionado.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
