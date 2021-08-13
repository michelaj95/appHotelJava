package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contraseña");
        
        Controladora control = new Controladora();
        control.primerIngreso(usuario, contra);
        boolean autorizado = control.verificarUsuario(usuario, contra);
        int idUsuario;
        
        if(autorizado == true){
            HttpSession misession = request.getSession(true);
            misession.setAttribute("usuario", usuario);
            misession.setAttribute("contra", contra);
            idUsuario = control.traerIdNuevoUsuario(usuario);
            misession.setAttribute("idUsuario", idUsuario);
            response.sendRedirect("pages/menuprincipal.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
