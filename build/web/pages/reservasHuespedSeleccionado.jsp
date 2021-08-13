<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Reservas por huésped</title>
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
                    <h2 class="float-right saludoUsuario">Sesión iniciada: <%=usuario%></h2>
                    <br>
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b>Consultas</b></h2>
                </div>
            </div>
        </div>
        <%
            String huespedSeleccionado = (String) misession.getAttribute("huespedSeleccionado");
            String fechaPrimera = (String) misession.getAttribute("fechaPrimera");
            String fechaSegunda = (String) misession.getAttribute("fechaSegunda");
        %>

        <div class="col-md-12 col-sm-12 ">
            <div class="x_panel">
                <div class="x_title">
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <br>
                            <h2>Reservas hechas por <%=huespedSeleccionado%> desde el <%=fechaPrimera%> hasta el <%=fechaSegunda%></h2>
                            <br>
                        </div>
                    </div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card-box table-responsive">
                                <table id="datatable-checkbox" class="table table-striped table-bordered bulk_action" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>ID de Reserva</th>
                                            <th>Fecha de check-in</th>
                                            <th>Fecha de check-out</th>
                                            <th>Habitación</th>
                                            <th>Cantidad de huéspedes</th>
                                            <th>Fecha de reserva</th>
                                            <th>Usuario</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            List<Reserva> listaReservasHuesped = (List) request.getSession().getAttribute("listaReservasHuesped");
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                            for (Reserva res : listaReservasHuesped) {
                                        %>
                                        <tr>
                                            <%
                                                int idReserva = res.getId_reserva();
                                                Date fechaCheckin = res.getCheckIn();
                                                Date fechaCheckout = res.getCheckOut();
                                                Date fechaReserva = res.getFechaReserva();
                                                String fechaCheckinCadena = sdf.format(fechaCheckin);
                                                String fechaCheckoutCadena = sdf.format(fechaCheckout);
                                                String fechaReservaCadena = sdf.format(fechaReserva);
                                                int cantidadHuespedes = res.getCantidadHuespedes();
                                                String habitacionNombre = res.getHabitacion().getNombre() + " " + res.getHabitacion().getTipo();
                                                String usuarioNombre = res.getUsuario().getUsuario();
                                                String huesped = res.getHuesped().getDni() + " - " + res.getHuesped().getApellido() + ", " + res.getHuesped().getNombre();
                                            %>
                                            <td> <%=idReserva%> </td>
                                            <td> <%=fechaCheckinCadena%> </td>
                                            <td> <%=fechaCheckoutCadena%> </td>
                                            <td> <%=habitacionNombre%> </td>
                                            <td> <%=cantidadHuespedes%> </td>
                                            <td> <%=fechaReservaCadena%> </td>
                                            <td> <%=usuarioNombre%> </td>
                                            <td><form name="frmEliminarreserva" action="../SvEliminarReserva" method="POST" style="display:inline">
                                                    <input type="hidden" name="id" value="<%=idReserva%>">
                                                    <button type="submit" class="btn btn-outline-danger btn-sm" data-title="Delete" style="display:inline">Eliminar</button> 
                                                </form> </td>    
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../build/js/custom.min.js"></script>
  <%}%>
    </body>

</html>
