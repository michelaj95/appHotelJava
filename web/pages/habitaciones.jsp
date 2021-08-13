<%@page import="Logica.Habitacion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Habitaciones</title>
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
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b>Habitaciones</b></h2> 
                </div>
            </div>
        </div>

        <div class="col-md-12 col-sm-12 ">
            <div class="x_panel">
                <div class="x_title">
                  <div class="row">
                        <div class="col-md-12 text-center">
                            <br>
                            <h2>Lista de habitaciones</h2>
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
                                            <th>Nombre</th>
                                            <th>Tipo</th>
                                            <th>Piso</th>
                                            <th>Precio</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            List<Habitacion> listaHabitaciones = (List) request.getSession().getAttribute("listaHabitaciones");

                                            for (Habitacion hab : listaHabitaciones) {
                                        %>
                                        <tr>
                                            <%
                                                String nombre = hab.getNombre();
                                                String tipo = hab.getTipo();
                                                int piso = hab.getPiso();
                                                String precio = "$" + Double.toString(hab.getPrecio());
                                                int idHabitacion = hab.getId_habitacion();
                                            %>
                                            <td> <%=nombre%> </td>
                                            <td> <%=tipo%> </td>
                                            <td> <%=piso%> </td>
                                            <td> <%=precio%> </td>
                                            <td><form name="frmEliminarHabitacion" action="../SvEliminarHabitacion" method="POST" style="display:inline">
                                                    <input type="hidden" name="id" value="<%=idHabitacion%>">
                                                    <button type="submit" class="btn btn-outline-danger btn-sm" data-title="Delete" style="display:inline">Eliminar</button> 
                                                </form> </td>    
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                                 
                                <form name="frmAltaHabitacion" action="altaHabitacion.jsp" style="display:inline">
                                    <div class="row justify-content-center">
                                    <button type="submit" class="btn btn-primary btn-lg" data-title="Add" style="display:inline">Agregar Nueva</button> 
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <%}%>
    </body>
</html>