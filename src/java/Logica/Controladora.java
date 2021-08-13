package Logica;

import Persistencia.ControladoraPersistencia;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearHuesped(String dni, String nombre, String apellido, String direccion, String profesion, Date fecha_nac) {
        Huesped hues = new Huesped();
        hues.setApellido(apellido);
        hues.setDireccion(direccion);
        hues.setDni(dni);
        hues.setFecha_nac(fecha_nac);
        hues.setNombre(nombre);
        hues.setProfesion(profesion);

        controlPersis.crearHuesped(hues);
    }

    public void crearEmpleado(String dni, String nombre, String apellido, String direccion, String cargo, Date fecha_nac, int id_usuario) {
        Empleado emple = new Empleado();
        emple.setDni(dni);
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setCargo(cargo);
        emple.setFecha_nac(fecha_nac);

        controlPersis.crearEmpleado(emple, id_usuario);
    }

    public void crearUsuario(String usuario, String contra) {
        Usuario usu = new Usuario();
        usu.setUsuario(usuario);
        usu.setContraseña(contra);
        controlPersis.crearUsuario(usu);
    }

    public void crearReserva(Date checkIn, Date checkOut, int cantidadHuespedes, int idHabitacion, int idUsuario, int idHuesped) {
        Reserva reser = new Reserva();
        reser.setCantidadHuespedes(cantidadHuespedes);
        reser.setCheckIn(checkIn);
        reser.setCheckOut(checkOut);
        ZoneId zona = ZoneId.systemDefault();
        LocalDate ahora = LocalDate.now();
        ZonedDateTime inicioHoy = ahora.atStartOfDay(zona);
        Instant instante = inicioHoy.toInstant();
        Date fechaReserva = Date.from(instante);
        System.out.println("Fecha reserva desde controladora: " + fechaReserva);
        reser.setFechaReserva(fechaReserva);
        controlPersis.crearReserva(reser, idHabitacion, idUsuario, idHuesped);
    }

    public List<Huesped> traerHuespedes() {
        return controlPersis.traerHuespedes();
    }

    public List<Habitacion> traerHabitaciones() {
        return controlPersis.traerHabitaciones();
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Reserva> traerReservas() {
        return controlPersis.traerReservas();
    }

    public boolean verificarUsuario(String usuario, String contra) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        if (listaUsuarios != null) {
            for (Usuario usu : listaUsuarios) {
                if (usu.getUsuario().equals(usuario) && usu.getContraseña().equals(contra)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarReserva(int idHabitacion, Date checkinAComprobar, Date checkoutAComprobar) {
        List<Habitacion> listaHabitaciones = controlPersis.traerHabitaciones();
        for (Habitacion hab : listaHabitaciones) {
            if (hab.getId_habitacion() == idHabitacion) {
                List<Reserva> listaReservasHabitacion = hab.getListaReservasHabitacion();
                if (listaReservasHabitacion != null) {
                    for (Reserva reser : listaReservasHabitacion) {
                        if ((checkoutAComprobar.before(reser.getCheckIn()) || checkoutAComprobar.equals(reser.getCheckIn())) || (checkinAComprobar.after(reser.getCheckOut()) || checkinAComprobar.equals(reser.getCheckOut()))) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean verificarDisponibilidadUsuario(String usuario, String contra) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        if (listaUsuarios != null) {
            for (Usuario usu : listaUsuarios) {
                if (usu.getUsuario().equals(usuario)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int traerIdNuevoUsuario(String usuario) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        for (Usuario usu : listaUsuarios) {
            if (usu.getUsuario().equals(usuario)) {
                return usu.id_usuario;
            }
        }
        return 0;
    }

    public int traerIdNuevoHuesped() {
        List<Huesped> listaHuespedes = controlPersis.traerHuespedes();
        int idUltimoHuesped = listaHuespedes.get(0).getId_huesped();
        for (Huesped hues : listaHuespedes) {
            if (hues.getId_huesped() > idUltimoHuesped) {
                idUltimoHuesped = hues.getId_huesped();
            }
        }
        return idUltimoHuesped;
    }

    public void eliminarHuesped(int idHuesped) {
        controlPersis.eliminarHuesped(idHuesped);
    }

    public Huesped buscarHuesped(int idHuesped) {
        return controlPersis.buscarHuesped(idHuesped);
    }

    public void modificarHuesped(Huesped hues) {
        controlPersis.modificarHuesped(hues);
    }

    public List<Reserva> traerReservasPorUsuario(int idUsuario) {
        List<Reserva> listaReservas = controlPersis.traerReservas();
        List<Reserva> listaReservasFiltradas = new ArrayList<>();
        for (Reserva res : listaReservas) {
            if (res.getUsuario().getId_usuario() == idUsuario) {
                listaReservasFiltradas.add(res);
            }
        }
        return listaReservasFiltradas;
    }

    public List<Reserva> traerReservasPorFecha(int idHuesped, Date fechaPrimera, Date fechaSegunda) {
        System.out.println("Date fecha primera: " + fechaPrimera);
        System.out.println("Date fecha segunda:" + fechaSegunda);
        List<Reserva> listaReservas = controlPersis.traerReservas();
        List<Reserva> listaReservasFiltradas = new ArrayList<>();
        for (Reserva res : listaReservas) {
            if (res.getHuesped().getId_huesped() == idHuesped) {
                System.out.println("fecha de la reserva: " + res.getFechaReserva());
                if ((fechaPrimera.before(res.getFechaReserva()) || fechaPrimera.equals(res.getFechaReserva())) && (fechaSegunda.after(res.getFechaReserva()) || fechaSegunda.equals(res.getFechaReserva()))) {
                    listaReservasFiltradas.add(res);
                }
            }
        }
        return listaReservasFiltradas;
    }

    public String tipoHabitacion(int idHabitacion) {
        Habitacion habSeleccionada = controlPersis.traerHabitacionPorId(idHabitacion);
        return habSeleccionada.getTipo();
    }

    public void primerIngreso(String usuario, String contra){
         List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        if(listaUsuarios.isEmpty() && usuario.equals("admin") && contra.equals("admin")){
            Usuario usu = new Usuario();
            usu.setContraseña(contra);
            usu.setUsuario(usuario);
            controlPersis.crearUsuario(usu);
            primeraCargaHabitaciones();
        }
    }

    private void primeraCargaHabitaciones() {
       Habitacion hab1 = new Habitacion(1, "Estándar", "Single", 1000);
       Habitacion hab2 = new Habitacion(1, "Estándar", "Doble", 1500);
       Habitacion hab3 = new Habitacion(1, "Estándar", "Triple", 2000);
       Habitacion hab4 = new Habitacion(1, "Estándar", "Múltiple", 2500);
       Habitacion hab5 = new Habitacion(1, "Suite", "Single", 2000);
       Habitacion hab6 = new Habitacion(1, "Suite", "Doble", 3000);
       Habitacion hab7 = new Habitacion(1, "Suite", "Triple", 4000);
       Habitacion hab8 = new Habitacion(1, "Suite", "Múltiple", 5000);
       controlPersis.primeraCargaHabitaciones(hab1, hab2, hab3, hab4, hab5, hab6, hab7, hab8);
    }

    public void eliminarReserva(int idReserva) {
        controlPersis.eliminarReserva(idReserva);
    }

    public void eliminarReservasVacias() {
      List <Reserva> listaReservas = controlPersis.traerReservas();
      for(Reserva res : listaReservas){
          if(res.getHuesped() == null  || res.getHabitacion() == null){
              controlPersis.eliminarReserva(res.getId_reserva());
          }      
      }
    }

    public Reserva buscarReserva(int idReserva) {
          return controlPersis.buscarReserva(idReserva);
    }

    public void eliminarHabitacion(int idHabitacion) {
       controlPersis.eliminarHabitacion(idHabitacion);
    }

    public Habitacion buscarHabitacion(int idHabitacion) {
        return controlPersis.buscarHabitacion(idHabitacion);
    }

    public void crearHabitacion(String nombre, String tipo, Double precio, int piso) {
        Habitacion hab = new Habitacion(piso, nombre, tipo, precio);
       controlPersis.crearHabitacion(hab);
    }

    public double calcularPrecioEstadia(Date checkin, Date checkout, int idHabitacion) {
              Habitacion hab = controlPersis.buscarHabitacion(idHabitacion);
              double precioNoche = hab.getPrecio();
              int milisecondsByDay = 86400000;
              double dias = (double) ((checkout.getTime()-checkin.getTime()) / milisecondsByDay);
              double precioEstadia = dias * precioNoche;
              return precioEstadia;
    }

    public Usuario buscarUsuario(int idUsuario) {
        return controlPersis.buscarUsuario(idUsuario);
    }
}
