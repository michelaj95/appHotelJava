package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_reserva;
    @Basic
    @Temporal(TemporalType.DATE)
    Date checkIn;
    @Temporal(TemporalType.DATE)
    Date checkOut;
    @Temporal(TemporalType.DATE)
    Date fechaReserva;
    int cantidadHuespedes;
    @ManyToOne
    Habitacion habitacion;
    @ManyToOne
    Usuario usuario;
    @ManyToOne
    Huesped huesped;
 

    public Reserva() {
    }

    public Reserva(int id_reserva, Date checkIn, Date checkOut, Date fechaReserva, int cantidadHuespedes, Habitacion habitacion, Usuario usu, Huesped hues) {
        this.id_reserva = id_reserva;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.fechaReserva = fechaReserva;
        this.cantidadHuespedes = cantidadHuespedes;
        this.habitacion = habitacion;
        this.usuario = usu;
        this.huesped = hues;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getCantidadHuespedes() {
        return cantidadHuespedes;
    }

    public void setCantidadHuespedes(int cantidadHuespedes) {
        this.cantidadHuespedes = cantidadHuespedes;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
}
