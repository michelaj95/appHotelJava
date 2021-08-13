package Servlets;

import Logica.Controladora;
import Logica.Usuario;
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

@WebServlet(name = "SvAltaEmpleado", urlPatterns = {"/SvAltaEmpleado"})
public class SvAltaEmpleado extends HttpServlet {

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
        String nombre = request.getParameter("nombreEmpleado");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String cargo = request.getParameter("cargo");
        String fechaNacCadena = request.getParameter("fechaNac");
        Date fechaNac = null;
        try {
            fechaNac = new  SimpleDateFormat ( "yyyy-MM-dd" ).parse(fechaNacCadena);
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contraseña");
        
        Controladora control = new Controladora();
        boolean disponible = control.verificarDisponibilidadUsuario(usuario, contra);
        
        if(disponible == true){
          control.crearUsuario(usuario, contra);
          int idUsuario = control.traerIdNuevoUsuario(usuario);
          control.crearEmpleado(dni, nombre, apellido, direccion, cargo, fechaNac, idUsuario);
            
            response.sendRedirect("pages/menuprincipal.jsp");
        }
        else {
            response.sendRedirect("pages/menuprincipal.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
