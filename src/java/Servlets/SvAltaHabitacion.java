
package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAltaHabitacion", urlPatterns = {"/SvAltaHabitacion"})
public class SvAltaHabitacion extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        Double precio = Double.parseDouble (request.getParameter("precio"));
        int piso = Integer.parseInt(request.getParameter("piso"));
     
        Controladora control = new Controladora();
        control.crearHabitacion(nombre, tipo, precio, piso);
        request.getSession().setAttribute("listaHabitaciones", control.traerHabitaciones());
        response.sendRedirect("pages/habitaciones.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
