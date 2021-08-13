<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logica.Habitacion"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Reservas</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" />
        <link rel="stylesheet" href="../css/all.min.css" />
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" href="../css/templatemo-style.css" />
    </head>

    <body>
        <%
            HttpSession misession = request.getSession();
            String usuario = (String) misession.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("../index.jsp");
            } else {
        %>
        <div class="container-fluid">
            <div class="row tm-brand-row">
                <div class="col-lg-4 col-10">
                    <div class="tm-brand-container">
                        <div class="tm-brand-texts" id="contenedorTitulo">
                            <h1 class="text-uppercase tm-brand-name" id="titulo">HOTEL MARBELLA</h1>
                        </div>
                        <div>
                            <a href="menuprincipal.jsp"><h2 class="float-left enlaceAtras">Volver al menú principal</h2></a> 
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-2 align-self-center">
                    <h2 class="float-right saludoUsuario">Sesión iniciada: <%=usuario%> </h2>
                    <br>
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b> Reservas </b></h2> 
                </div>
            </div>
        </div>

        <div class="container formulario">
            <div class="row">
                <div class="col-md-12 text-center">
                    <h2>Habitación y fecha de estadía</h2>
                </div>
            </div>
            <form action="../SvReservaFechas" method="POST">
                <div class="form-row">
                    <%
                        Controladora control = new Controladora();
                        List<Habitacion> listaHabitaciones = control.traerHabitaciones();
                    %>
                    <div class="form-group col-md-12">
                        <label for="inputNombreHabitacion">Nombre de habitación:</label>
                        <select id="inputHabitacion" class="form-control" name="nombre">
                            <option selected>Elija...</option>
                            <%
                             for (Habitacion hab : listaHabitaciones) {
                                 String nombreHabitacion = hab.getNombre() + " " + hab.getTipo();
                                 int idHabitacion = hab.getId_habitacion(); 
                            %>
                            <option value="<%=idHabitacion%>"><%=nombreHabitacion%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputCheckIn">Fecha de check-in:</label>
                        <input type="date" class="form-control" name="fecha_checkin" id="inputCheckIn">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputCheckOut">Fecha de check-out:</label>
                        <input type="date" class="form-control" name="fecha_checkout" id="inputCheckOut">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary"id="btnConsultar">Consultar disponibilidad</button>
                </div>
            </form>
        </div> 
        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <%}%>
    </body>
</html>
