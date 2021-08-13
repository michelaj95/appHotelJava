package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_usuario;
    @Basic
    String usuario;
    String contraseña;
    @OneToMany
    @CascadeOnDelete
    List<Reserva> listaReservas;

    public Usuario(int id_usuario, String usuario, String contraseña, List<Reserva> listaReservas) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.listaReservas = listaReservas;
    }

    public Usuario() {
    }
    
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
    
    
}
