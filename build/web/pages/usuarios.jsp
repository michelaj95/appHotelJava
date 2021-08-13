<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Usuarios</title>
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
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b>Usuarios</b></h2>
                </div>
            </div>
        </div>

        <div class="container formulario" id="formularioEmpleado">
            <div class="row">
                <div class="col-md-12 text-center">
                    <h2>Datos del empleado</h2>
                    <br>
                </div>
            </div>
            <form action="../SvAltaEmpleado" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="inputDniEmpleado">Numero de documento:</label>
                        <input type="text" class="form-control" id="inputDniEmpleado" name="dni" placeholder="DNI">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputNombreEmpleado">Nombre:</label>
                        <input type="text" class="form-control" id="inputNombreEmpleado" name="nombreEmpleado" placeholder="Nombre">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputApellidoEmpleado">Apellido:</label>
                        <input type="text" class="form-control" name="apellido" id="inputApellidoEmpleado" placeholder="Apellido">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="inputDireccionEmpleado">Dirección</label>
                        <input type="text" class="form-control" id="inputDireccionEmpleado" name="direccion" placeholder="Dirección">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputCargo">Cargo:</label>
                        <input type="text" class="form-control" id="inputCargo" name="cargo" placeholder="Cargo">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputNacEmpleado">Fecha de nacimiento:</label>
                        <input type="date" class="form-control" name="fechaNac" id="inputNacEmpleado">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="usuario">Usuario:</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Introduzca su usuario">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="pwd">Contraseña:</label>
                        <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Introduzca la contraseña">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary btn-lg" id="btnReserva">Cargar empleado</button>
                </div>
            </form>
        </div>
        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <% }%>
    </body>
</html>