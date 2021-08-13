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
                    <h2 class="float-right saludoUsuario">Sesión iniciada: <%=usuario%></h2>
                    <br>
                    <h2 class="float-right seccion">Usted se encuentra en la sección de <b>Habitaciones</b></h2> 
                </div>
            </div>
        </div>

        <div class="container formulario" id="formularioHuesped">
             <div class="row">
                <div class="col-md-12 text-center">
                     <h2>Datos de la habitación</h2>
                     <br>
                </div>
            </div>
            <form action="../SvAltaHabitacion" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNombreHabitacion">Nombre de Habitación</label>
                        <input type="text" class="form-control" id="inputNombreHabitacion" name="nombre" placeholder="Nombre">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputTipoHabitacion">Tipo:</label>
                        <select class="form-control" id="inputTipoHabitacion" name="tipo">
                            <option selected>Elija...</option>
                            <option>Single</option>
                            <option>Doble</option>
                            <option>Triple</option>
                            <option>Múltiple</option>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputPrecioHabitacion">Precio:</label>
                        <input type="number" class="form-control" min="0" max="10000" step="1" name="precio" id="precio" required="required">
                    </div>
                   <div class="form-group col-md-6">
                        <label for="inputPisoHabitacion">Piso:</label>
                        <select class="form-control" id="inputTipoHabitacion" name="piso">
                            <option selected>Elija...</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                        </select>
                    </div>
                  
                </div>
                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary btn-lg" id="btnHabitacion">Añadir Habitacion</button>
                </div>
            </form>
        </div>
        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <%}%>
    </body>

</html>