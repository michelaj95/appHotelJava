package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_empleado;
    @Basic
    String dni;
    String nombre;
    String apellido;
    @Temporal(TemporalType.DATE)
    Date fecha_nac;
    String direccion;
    String cargo;
    @OneToOne
    Usuario usuario;

    public Empleado() {
    }

    public Empleado(int id_empleado, String dni, String nombre, String apellido, Date fecha_nac, String direccion, String cargo, Usuario usuario) {
        this.id_empleado = id_empleado;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.direccion = direccion;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
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

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
