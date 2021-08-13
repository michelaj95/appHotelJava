<%@page import="Logica.Habitacion"%>
<%@page import="Logica.Huesped"%>
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
                    <h2 class="float-right saludoUsuario">Sesión iniciada: <%=usuario%></h2>
                    <br>
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b> Reservas </b></h2> 
                </div>
            </div>
        </div>

        <div class="container formulario" id="formularioHuesped">
            <div class="row">
                <div class="col-md-12 text-center">
                   <h2>Datos del huésped</h2>
                   <br>
                </div>
            </div>
            <form action="../SvAltaHuesped" method="POST">
                <div class="form-row">
                    <%
                        String tipoHabitacion = (String) misession.getAttribute("tipoHabitacion");
                    %>
                    <div class="form-group col-md-3">
                        <label for="inputDniHuesped">Numero de documento:</label>
                        <input type="text" class="form-control" id="inputDniHuesped" name="dni" placeholder="DNI">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputNombreHuesped">Nombre:</label>
                        <input type="text" class="form-control" id="inputNombreHuesped" name="nombreHuesped" placeholder="Nombre completo">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputApellidoHuesped">Apellido:</label>
                        <input type="text" class="form-control" name="apellido" id="inputApellidoHuesped" placeholder="Apellido">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputCantHuespedes">Cantidad de huéspedes:</label>
                        <select id="inputCantHuespedes" class="form-control" name="cantidad_huespedes">
                            <option selected>Elija...</option>
                            <%if (tipoHabitacion.equals("Single")) {
                            %> 
                            <option>1</option>
                            <%
                            } else if (tipoHabitacion.equals("Doble")) {
                            %>
                            <option>1</option>
                            <option>2</option>
                            <%
                            } else if (tipoHabitacion.equals("Triple")) {
                            %>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <%
                            } else {
                            %>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="inputDireccionHuesped">Dirección</label>
                        <input type="text" class="form-control" id="inputDireccionHuesped" name="direccion" placeholder="Dirección">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputProfesionHuesped">Profesión:</label>
                        <input type="text" class="form-control" id="inputProfesionHuesped" name="profesion" placeholder="Profesión">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputNacHuesped">Fecha de nacimiento:</label>
                        <input type="date" class="form-control" name="fecha_nac" id="inputNacHuesped">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary btn-lg" id="btnReserva">Realizar reserva</button>
                </div>
            </form>
        </div>
        <script src="../js/misfunciones.js"></script>
        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <%}%>
    </body>

</html>