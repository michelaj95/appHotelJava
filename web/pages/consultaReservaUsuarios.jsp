<%@page import="Logica.Controladora"%>
<%@page import="Logica.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Reservas por usuario</title>
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
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b>Consultas</b></h2> 
                </div>
            </div>
        </div>
        <div class="container formulario">
           <div class="row">
                <div class="col-md-12 text-center">
                <h2>Reservas por usuario</h2>
                   <br>
                </div>
            </div>
            <form action="../SvConsultaReservasUsuarioSeleccionado" method="POST">
                <div class="form-row">
                    <%
                        Controladora control = new Controladora();
                        List<Usuario> listaUsuarios = control.traerUsuarios();
                        String nombreUsuario;
                        int idUsuario;
                    %>
                    <div class="form-group col-md-12">
                        <label for="inputFechaReservas">Usuario a consultar:</label>
                        <select id="inputFechaReservas" class="form-control" name="UsuarioSeleccionado">
                            <option selected>Elija...</option>
                            <%
                                for (Usuario usu : listaUsuarios) {
                                    nombreUsuario = usu.getUsuario();
                                    idUsuario = usu.getId_usuario();
                            %>
                            <option value="<%=idUsuario%>"><%=nombreUsuario%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary"id="btnConsultar">Consultar reservas</button>
                </div>
            </form>


            <script src="../js/jquery.min.js"></script>
            <script src="../js/parallax.min.js"></script>
            <script src="../js/bootstrap.min.js"></script>
            <%}%>
    </body>
</html>