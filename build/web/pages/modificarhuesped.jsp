<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Huesped"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Modificar huésped</title>
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
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b>Modificar huésped</b></h2> 
                </div>
            </div>
        </div>

        <div class="container formulario" id="formularioModificarHuesped">
            <div class="row">
                <div class="col-md-12 text-center">
                     <h2>Datos del huésped</h2>
                   <br>
                </div>
            </div>
     
            <form action="../SvModifHuesped" method="GET">
                <%
                    Huesped hues = (Huesped) misession.getAttribute("huesped");
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date fechaNac = hues.getFecha_nac();
                     String fechaCadena = sdf.format(fechaNac);
                %>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="inputDniHuesped">Numero de documento:</label>
                        <input type="text" class="form-control" id="inputDniHuesped" name="dni" placeholder="DNI" value="<%=hues.getDni()%>">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputNombreHuesped">Nombre:</label>
                        <input type="text" class="form-control" id="inputNombreHuesped" name="nombreHuesped" placeholder="Nombre completo" value="<%=hues.getNombre()%>">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputApellidoHuesped">Apellido:</label>
                        <input type="text" class="form-control" name="apellido" id="inputApellidoHuesped" placeholder="Apellido" value="<%=hues.getApellido()%>">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="inputDireccionHuesped">Dirección</label>
                        <input type="text" class="form-control" id="inputDireccionHuesped" name="direccion" placeholder="Dirección" value="<%=hues.getDireccion()%>">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputProfesionHuesped">Profesión:</label>
                        <input type="text" class="form-control" id="inputProfesionHuesped" name="profesion" placeholder="Profesión" value="<%=hues.getProfesion()%>">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputNacHuesped">Fecha de nacimiento:</label>
                        <input type="date" class="form-control" name="fecha_nac" id="inputNacHuesped" value="<%=fechaCadena%>">
                        <input type="hidden" name="id" value="<%=hues.getId_huesped()%>">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary" id="btnModificarHuesped">Modificar</button>
                </div>
            </form>
        </div>
        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <%}%>
    </body>

</html>