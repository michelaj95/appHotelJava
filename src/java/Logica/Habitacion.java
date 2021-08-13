package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Habitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_habitacion;
    @Basic
    int piso;
    String nombre;
    String tipo;
    double precio;
    @OneToMany
    @CascadeOnDelete
    List<Reserva> listaReservasHabitacion;

    public Habitacion() {
    }
    
    public Habitacion(int id_habitacion){
        this.id_habitacion = id_habitacion;
    }

    public Habitacion(int id_habitacion, int piso, String nombre, String tipo, double precio, List<Reserva> listaReservasHabitacion) {
        this.id_habitacion = id_habitacion;
        this.piso = piso;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.listaReservasHabitacion = listaReservasHabitacion;
    }

    public Habitacion(int piso, String nombre, String tipo, double precio) {
        this.piso = piso;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }
    
    

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Reserva> getListaReservasHabitacion() {
        return listaReservasHabitacion;
    }

    public void setListaReservasHabitacion(List<Reserva> listaReservasHabitacion) {
        this.listaReservasHabitacion = listaReservasHabitacion;
    }
    
    
}


