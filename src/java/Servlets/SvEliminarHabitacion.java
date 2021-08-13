
package Servlets;

import Logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvEliminarHabitacion", urlPatterns = {"/SvEliminarHabitacion"})
public class SvEliminarHabitacion extends HttpServlet {

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
        int idHabitacion = Integer.parseInt(request.getParameter("id"));
        
        Controladora control = new Controladora();
        control.eliminarHabitacion(idHabitacion);
        request.getSession().setAttribute("listaHabitaciones", control.traerHabitaciones());
        response.sendRedirect("pages/habitaciones.jsp");
    }
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
