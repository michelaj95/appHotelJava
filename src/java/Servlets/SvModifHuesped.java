package Servlets;

import Logica.Controladora;
import Logica.Huesped;
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

@WebServlet(name = "SvModifHuesped", urlPatterns = {"/SvModifHuesped"})
public class SvModifHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idHuesped = Integer.parseInt(request.getParameter("id"));
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombreHuesped");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String profesion = request.getParameter("profesion");
        String fechaNacCadena = request.getParameter("fecha_nac");
        Date fechaNac = null;
        try {
            fechaNac = new  SimpleDateFormat ( "yyyy-MM-dd" ).parse(fechaNacCadena);
        } catch (ParseException ex) {
            Logger.getLogger(SvModifHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Controladora control = new Controladora();
        Huesped hues = control.buscarHuesped(idHuesped);
        hues.setApellido(apellido);
        hues.setDireccion(direccion);
        hues.setDni(dni);
        hues.setFecha_nac(fechaNac);
        hues.setNombre(nombre);
        hues.setProfesion(profesion);
        
        control.modificarHuesped(hues);
        
        request.getSession().setAttribute("listaHuespedes", control.traerHuespedes());
        response.sendRedirect("pages/huespedes.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int idHuesped = Integer.parseInt(request.getParameter("id"));
        
        Controladora control = new Controladora();
        Huesped hues = control.buscarHuesped(idHuesped);
        HttpSession misession = request.getSession();
        misession.setAttribute("huesped", hues);
        response.sendRedirect("pages/modificarhuesped.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
