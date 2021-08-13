
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
      public UsuarioJpaController() {
       emf = Persistence.createEntityManagerFactory("HotelMarbellaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getListaReservas() == null) {
            usuario.setListaReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaReservas = new ArrayList<Reserva>();
            for (Reserva listaReservasReservaToAttach : usuario.getListaReservas()) {
                listaReservasReservaToAttach = em.getReference(listaReservasReservaToAttach.getClass(), listaReservasReservaToAttach.getId_reserva());
                attachedListaReservas.add(listaReservasReservaToAttach);
            }
            usuario.setListaReservas(attachedListaReservas);
            em.persist(usuario);
            for (Reserva listaReservasReserva : usuario.getListaReservas()) {
                Usuario oldUsuarioOfListaReservasReserva = listaReservasReserva.getUsuario();
                listaReservasReserva.setUsuario(usuario);
                listaReservasReserva = em.merge(listaReservasReserva);
                if (oldUsuarioOfListaReservasReserva != null) {
                    oldUsuarioOfListaReservasReserva.getListaReservas().remove(listaReservasReserva);
                    oldUsuarioOfListaReservasReserva = em.merge(oldUsuarioOfListaReservasReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId_usuario());
            List<Reserva> listaReservasOld = persistentUsuario.getListaReservas();
            List<Reserva> listaReservasNew = usuario.getListaReservas();
            List<Reserva> attachedListaReservasNew = new ArrayList<Reserva>();
            for (Reserva listaReservasNewReservaToAttach : listaReservasNew) {
                listaReservasNewReservaToAttach = em.getReference(listaReservasNewReservaToAttach.getClass(), listaReservasNewReservaToAttach.getId_reserva());
                attachedListaReservasNew.add(listaReservasNewReservaToAttach);
            }
            listaReservasNew = attachedListaReservasNew;
            usuario.setListaReservas(listaReservasNew);
            usuario = em.merge(usuario);
            for (Reserva listaReservasOldReserva : listaReservasOld) {
                if (!listaReservasNew.contains(listaReservasOldReserva)) {
                    listaReservasOldReserva.setUsuario(null);
                    listaReservasOldReserva = em.merge(listaReservasOldReserva);
                }
            }
            for (Reserva listaReservasNewReserva : listaReservasNew) {
                if (!listaReservasOld.contains(listaReservasNewReserva)) {
                    Usuario oldUsuarioOfListaReservasNewReserva = listaReservasNewReserva.getUsuario();
                    listaReservasNewReserva.setUsuario(usuario);
                    listaReservasNewReserva = em.merge(listaReservasNewReserva);
                    if (oldUsuarioOfListaReservasNewReserva != null && !oldUsuarioOfListaReservasNewReserva.equals(usuario)) {
                        oldUsuarioOfListaReservasNewReserva.getListaReservas().remove(listaReservasNewReserva);
                        oldUsuarioOfListaReservasNewReserva = em.merge(oldUsuarioOfListaReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId_usuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId_usuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaReservas = usuario.getListaReservas();
            for (Reserva listaReservasReserva : listaReservas) {
                listaReservasReserva.setUsuario(null);
                listaReservasReserva = em.merge(listaReservasReserva);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
