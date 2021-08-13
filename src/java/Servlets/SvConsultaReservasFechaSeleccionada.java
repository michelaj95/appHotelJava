package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvConsultaReservasFechaSeleccionada", urlPatterns = {"/SvConsultaReservasFechaSeleccionada"})
public class SvConsultaReservasFechaSeleccionada extends HttpServlet {


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
        String fechaSeleccionadaCadena = request.getParameter("fechaSeleccionada");
        Controladora control = new Controladora();
        List <Reserva> listaReservasTotal = control.traerReservas();
        List <Reserva> listaReservasFecha = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for(Reserva res : listaReservasTotal){
            Date fechaRes = res.getFechaReserva();
            String  fechaResCadena = sdf.format(fechaRes);
            if(fechaSeleccionadaCadena.equals(fechaResCadena)){
                listaReservasFecha.add(res);
            }
        }
        HttpSession misession = request.getSession();
        misession.setAttribute("fechaSeleccionadaCadena", fechaSeleccionadaCadena);
        misession.setAttribute("listaReservasFecha", listaReservasFecha);
        response.sendRedirect("pages/reservasPorFechaSeleccionada.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
