<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="css/estilos.css">
    <title>Hotel Marbella</title>
</head>

<body>
    <header>

    </header>
    <div class="container-fluid contenedor">
        <div class="row" id="filaPrincipal">
            <div class="col-6" id="columnaIzquierdaGrande">
                <div class="row">
                    <div class="col-12" id="columnaInternaArriba">
                        <div class="float-right" id="contenedorImagenIzquierdaArriba">
                            <img src="img/imagenIzquierdaArriba.jpg" alt="imagen habitacion hotel"
                                id="imagenIzquierdaArriba">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12" id="columnaInternaAbajo">
                        <div class="float-right" id="contenedorImagenIzquierdaAbajo">
                            <img src="img/imagenIzquierdaAbajo.jpg" alt="imagen recepcion hotel"
                                id="imagenIzquierdaAbajo">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6" id="columnaDerechaGrande">
                <div id="contenedorImagenDerecha">
                    <img src="img/imagenDerecha.jpg" alt="imagen hall hotel" id="imagenDerecha">
                </div>
            </div>
        </div>
        <div class="row" id="bloqueNegativo">
            <div class="col-12">
                <div class="mx-auto" id="recuadroCentral">
                    <div class="encabezados">
                        <h1 class="titulo">HOTEL MARBELLA</h1>
                        <p>Bienvenido/a al sistema. Por favor ingrese sus datos:</p>
                    </div>
                    <div id="formulario">
                        <form action="SvUsuario" method="POST">
                            <div class="form-group">
                                <label for="usuario">Usuario:</label>
                                <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Introduzca su usuario">
                            </div>
                            <div class="form-group">
                                <label for="pwd">Contraseña:</label>
                                <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Introduzca la contraseña">
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox"> Recordar mis datos</label>
                            </div>
                            <div class="boton">
                            <button type="submit" class="btn btn-default" id="botonEnviar">INGRESAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous">
    </script>
</body>

</html>