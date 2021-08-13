<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Next Level HTML CSS Template</title>
        <!--
      Next Level CSS Template
      https://templatemo.com/tm-532-next-level
        -->
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
                    </div>
                </div>
                <div class="col-lg-8 col-2 tm-nav-col">
                    <h2 class="saludoUsuario">¡Hola <%=usuario%>! Bienvenido/a nuevamente</h2>
                </div>
            </div>
        </div>

        <div class="row tm-welcome-row">
            <div class="col-12 tm-page-cols-container">
                <div class="tm-page-col-right">
                    <div class="row justify-content-center align-items-center tm-welcome-parallax" data-parallax="scroll"
                         data-image-src="../img/pruebafondo7.jpg">
                        <div class="col-2 text-center tm-gallery-item category-2">
                            <a href="reservaFechas.jsp">
                                <img class="icono" src="../img/reserva.png" alt="">
                                <p class="botonMenu">Nueva reserva</p>
                            </a>
                        </div>
                        <div class="col-2 text-center">
                            <form action="../SvConsultaHuespedes" method="GET">
                                <a href="../SvConsultaHuespedes">
                                    <img class="icono" src="../img/huespedes.png" alt="">
                                    <p class="botonMenu">Huéspedes</p>
                                </a>
                            </form>
                        </div>
                        <div class="col-2 text-center">
                            <form action="../SvEliminarReservasVacias" method="GET">
                                <a href="../SvEliminarReservasVacias">
                                    <img class="icono" src="../img/consultas.png" alt="">
                                    <p class="botonMenu">Consultas</p>
                                </a>
                            </form>
                        </div>
                        <div class="col-2 text-center">
                            <a href="usuarios.jsp">
                                <img class="icono" src="../img/usuarios.png" alt="">
                                <p class="botonMenu">Usuarios</p>
                            </a>  
                        </div>
                        <div class="col-2 text-center">
                            <form action="../SvConsultaHabitaciones" method="GET">
                                <a href="../SvConsultaHabitaciones">
                                    <img class="icono" src="../img/ganancias.png" alt="">
                                    <p class="botonMenu">Habitaciones</p>
                                </a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="../js/jquery.min.js"></script>
        <script src="../js/parallax.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <% }%>
    </body>

</html>