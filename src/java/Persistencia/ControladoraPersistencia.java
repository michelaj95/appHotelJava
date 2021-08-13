package Persistencia;

import Logica.*;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    HuespedJpaController huespedJPA = new HuespedJpaController();
    ReservaJpaController reservaJPA = new ReservaJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();
    HabitacionJpaController habitacionJPA = new HabitacionJpaController();
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    
    public void crearHuesped(Huesped hues){
        huespedJPA.create(hues);
    }
    
    public void crearReserva(Reserva reser, int idHabitacion, int idUsuario, int idHuesped){
        reser.setHabitacion(habitacionJPA.findHabitacion(idHabitacion));
        reser.setUsuario(usuarioJPA.findUsuario(idUsuario));
        reser.setHuesped(huespedJPA.findHuesped(idHuesped));
        reservaJPA.create(reser);
    }
      
    public List<Huesped> traerHuespedes(){
        return huespedJPA.findHuespedEntities();
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJPA.findUsuarioEntities();
    }
    
     public List<Habitacion> traerHabitaciones() {
        return  habitacionJPA.findHabitacionEntities();
    }

    public List<Reserva> traerReservas() {
      return reservaJPA.findReservaEntities();
    }

    public void crearEmpleado(Empleado emple, int idUsuario) {
        emple.setUsuario(usuarioJPA.findUsuario(idUsuario));
        empleadoJPA.create(emple);
    }

    public void crearUsuario(Usuario usu) {
        usuarioJPA.create(usu);
    }

    public void eliminarHuesped(int idHuesped) {
        try {
            huespedJPA.destroy(idHuesped);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Huesped buscarHuesped(int idHuesped) {
       return huespedJPA.findHuesped(idHuesped);
    }

    public void modificarHuesped(Huesped hues) {
        try {
            huespedJPA.edit(hues);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Habitacion traerHabitacionPorId(int idHabitacion) {
       return habitacionJPA.findHabitacion(idHabitacion);
    }

    public void primeraCargaHabitaciones(Habitacion hab1, Habitacion hab2, Habitacion hab3, Habitacion hab4, Habitacion hab5, Habitacion hab6, Habitacion hab7, Habitacion hab8) {
        habitacionJPA.create(hab1);
        habitacionJPA.create(hab2);
        habitacionJPA.create(hab3);
        habitacionJPA.create(hab4);
        habitacionJPA.create(hab5);
        habitacionJPA.create(hab6);
        habitacionJPA.create(hab7);
        habitacionJPA.create(hab8);
    }

    public void eliminarReserva(int idReserva) {
         try {
            reservaJPA.destroy(idReserva);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reserva buscarReserva(int idReserva) {
       return reservaJPA.findReserva(idReserva);
    }

    public void eliminarHabitacion(int idHabitacion) {
        try {
            habitacionJPA.destroy(idHabitacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Habitacion buscarHabitacion(int idHabitacion) {
        return habitacionJPA.findHabitacion(idHabitacion);
    }

    public void crearHabitacion(Habitacion hab) {
     habitacionJPA.create(hab);
    }

    public Usuario buscarUsuario(int idUsuario) {
       return usuarioJPA.findUsuario(idUsuario);
    }
    
    
}
