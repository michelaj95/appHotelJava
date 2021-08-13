
package Persistencia;

import Logica.Habitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
      public HabitacionJpaController() {
       emf = Persistence.createEntityManagerFactory("HotelMarbellaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        if (habitacion.getListaReservasHabitacion() == null) {
            habitacion.setListaReservasHabitacion(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaReservasHabitacion = new ArrayList<Reserva>();
            for (Reserva listaReservasHabitacionReservaToAttach : habitacion.getListaReservasHabitacion()) {
                listaReservasHabitacionReservaToAttach = em.getReference(listaReservasHabitacionReservaToAttach.getClass(), listaReservasHabitacionReservaToAttach.getId_reserva());
                attachedListaReservasHabitacion.add(listaReservasHabitacionReservaToAttach);
            }
            habitacion.setListaReservasHabitacion(attachedListaReservasHabitacion);
            em.persist(habitacion);
            for (Reserva listaReservasHabitacionReserva : habitacion.getListaReservasHabitacion()) {
                Habitacion oldHabitacionOfListaReservasHabitacionReserva = listaReservasHabitacionReserva.getHabitacion();
                listaReservasHabitacionReserva.setHabitacion(habitacion);
                listaReservasHabitacionReserva = em.merge(listaReservasHabitacionReserva);
                if (oldHabitacionOfListaReservasHabitacionReserva != null) {
                    oldHabitacionOfListaReservasHabitacionReserva.getListaReservasHabitacion().remove(listaReservasHabitacionReserva);
                    oldHabitacionOfListaReservasHabitacionReserva = em.merge(oldHabitacionOfListaReservasHabitacionReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getId_habitacion());
            List<Reserva> listaReservasHabitacionOld = persistentHabitacion.getListaReservasHabitacion();
            List<Reserva> listaReservasHabitacionNew = habitacion.getListaReservasHabitacion();
            List<Reserva> attachedListaReservasHabitacionNew = new ArrayList<Reserva>();
            for (Reserva listaReservasHabitacionNewReservaToAttach : listaReservasHabitacionNew) {
                listaReservasHabitacionNewReservaToAttach = em.getReference(listaReservasHabitacionNewReservaToAttach.getClass(), listaReservasHabitacionNewReservaToAttach.getId_reserva());
                attachedListaReservasHabitacionNew.add(listaReservasHabitacionNewReservaToAttach);
            }
            listaReservasHabitacionNew = attachedListaReservasHabitacionNew;
            habitacion.setListaReservasHabitacion(listaReservasHabitacionNew);
            habitacion = em.merge(habitacion);
            for (Reserva listaReservasHabitacionOldReserva : listaReservasHabitacionOld) {
                if (!listaReservasHabitacionNew.contains(listaReservasHabitacionOldReserva)) {
                    listaReservasHabitacionOldReserva.setHabitacion(null);
                    listaReservasHabitacionOldReserva = em.merge(listaReservasHabitacionOldReserva);
                }
            }
            for (Reserva listaReservasHabitacionNewReserva : listaReservasHabitacionNew) {
                if (!listaReservasHabitacionOld.contains(listaReservasHabitacionNewReserva)) {
                    Habitacion oldHabitacionOfListaReservasHabitacionNewReserva = listaReservasHabitacionNewReserva.getHabitacion();
                    listaReservasHabitacionNewReserva.setHabitacion(habitacion);
                    listaReservasHabitacionNewReserva = em.merge(listaReservasHabitacionNewReserva);
                    if (oldHabitacionOfListaReservasHabitacionNewReserva != null && !oldHabitacionOfListaReservasHabitacionNewReserva.equals(habitacion)) {
                        oldHabitacionOfListaReservasHabitacionNewReserva.getListaReservasHabitacion().remove(listaReservasHabitacionNewReserva);
                        oldHabitacionOfListaReservasHabitacionNewReserva = em.merge(oldHabitacionOfListaReservasHabitacionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = habitacion.getId_habitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
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
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId_habitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaReservasHabitacion = habitacion.getListaReservasHabitacion();
            for (Reserva listaReservasHabitacionReserva : listaReservasHabitacion) {
                listaReservasHabitacionReserva.setHabitacion(null);
                listaReservasHabitacionReserva = em.merge(listaReservasHabitacionReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
