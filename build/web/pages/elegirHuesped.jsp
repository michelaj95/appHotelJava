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
                            <a  href="menuprincipal.jsp"><h2 class="float-left enlaceAtras">Volver al menú principal</h2></a> 
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
        <div class="container formulario">
            <div class="row">
                <%
                    double precioEstadia = (double) misession.getAttribute("precioEstadia");
                %>
                <div class="col-md-12 text-center">
                    <h2>El precio total de la estadia es de:  <b>$<%=precioEstadia%> </b></h2>
                    <br>
                    <h3>Huéspedes en sistema</h3>
                    <br>
                </div>
            </div>
            <form action="../SvAltaReservaPreHuesped" method="POST">
                <div class="form-row">
                    <%
                        String tipoHabitacion = (String) misession.getAttribute("tipoHabitacion");
                        List<Huesped> listaHuespedes = (List) request.getSession().getAttribute("listaHuespedes");
                    %>
                    <div class="form-group col-md-12">
                        <label for="inputHuespedExistente">Elija un huésped ya existente: </label>
                        <select id="inputHuespedExistente" class="form-control" name="huespedElegido">
                            <option selected>Elija...</option>
                            <%
                                for (Huesped hues : listaHuespedes) {
                                    String opcionHuesped = hues.getDni() + " - " + hues.getApellido() + ", " + hues.getNombre();
                                    int idHuesped = hues.getId_huesped();
                            %>
                            <option value="<%=idHuesped%>"><%=opcionHuesped%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
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
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary btn-lg"id="btnElegir">Elegir y confirmar reserva</button>
                </div>
            </form>
            <div class="row">
                <div class="col-md-12 text-center">
                    <br>
                    <br>
                    <h3>O ingrese un nuevo huésped</h3>
                    <br>
                </div>
            </div>       
            <form name="frmingresarNuevoHuesped" action="reservaHuesped.jsp" style="display:inline">
                <div class="form-row justify-content-center">
                        <button type="submit" class="btn btn-primary btn-lg" data-title="Edit" style="display:inline">Ingresar nuevo huésped</button> 
                </div>
            </form>
        </div>
    </div>

    <script src="../js/jquery.min.js"></script>
    <script src="../js/parallax.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <%}%>
</body>

</html>
