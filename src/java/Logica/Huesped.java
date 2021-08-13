package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Huesped implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_huesped;
    @Basic
    String dni;
    String nombre;
    String apellido;
    String direccion;
    String profesion;
    @Temporal(TemporalType.DATE)
    Date fecha_nac;
    @OneToMany
    @CascadeOnDelete
    List<Reserva> listaReservas;

    public Huesped() {
    }

    public Huesped(int id_huesped, String dni, String nombre, String apellido, String direccion, String profesion, Date fecha_nac, List<Reserva> listaReservas) {
        this.id_huesped = id_huesped;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.profesion = profesion;
        this.fecha_nac = fecha_nac;
        this.listaReservas = listaReservas;
    }

    public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
    
    
}
