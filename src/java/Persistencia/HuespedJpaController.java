
package Persistencia;

import Logica.Huesped;
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


public class HuespedJpaController implements Serializable {

    public HuespedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
      public HuespedJpaController() {
       emf = Persistence.createEntityManagerFactory("HotelMarbellaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Huesped huesped) {
        if (huesped.getListaReservas() == null) {
            huesped.setListaReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaReservas = new ArrayList<Reserva>();
            for (Reserva listaReservasReservaToAttach : huesped.getListaReservas()) {
                listaReservasReservaToAttach = em.getReference(listaReservasReservaToAttach.getClass(), listaReservasReservaToAttach.getId_reserva());
                attachedListaReservas.add(listaReservasReservaToAttach);
            }
            huesped.setListaReservas(attachedListaReservas);
            em.persist(huesped);
            for (Reserva listaReservasReserva : huesped.getListaReservas()) {
                Huesped oldHuespedOfListaReservasReserva = listaReservasReserva.getHuesped();
                listaReservasReserva.setHuesped(huesped);
                listaReservasReserva = em.merge(listaReservasReserva);
                if (oldHuespedOfListaReservasReserva != null) {
                    oldHuespedOfListaReservasReserva.getListaReservas().remove(listaReservasReserva);
                    oldHuespedOfListaReservasReserva = em.merge(oldHuespedOfListaReservasReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huesped huesped) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped persistentHuesped = em.find(Huesped.class, huesped.getId_huesped());
            List<Reserva> listaReservasOld = persistentHuesped.getListaReservas();
            List<Reserva> listaReservasNew = huesped.getListaReservas();
            List<Reserva> attachedListaReservasNew = new ArrayList<Reserva>();
            for (Reserva listaReservasNewReservaToAttach : listaReservasNew) {
                listaReservasNewReservaToAttach = em.getReference(listaReservasNewReservaToAttach.getClass(), listaReservasNewReservaToAttach.getId_reserva());
                attachedListaReservasNew.add(listaReservasNewReservaToAttach);
            }
            listaReservasNew = attachedListaReservasNew;
            huesped.setListaReservas(listaReservasNew);
            huesped = em.merge(huesped);
            for (Reserva listaReservasOldReserva : listaReservasOld) {
                if (!listaReservasNew.contains(listaReservasOldReserva)) {
                    listaReservasOldReserva.setHuesped(null);
                    listaReservasOldReserva = em.merge(listaReservasOldReserva);
                }
            }
            for (Reserva listaReservasNewReserva : listaReservasNew) {
                if (!listaReservasOld.contains(listaReservasNewReserva)) {
                    Huesped oldHuespedOfListaReservasNewReserva = listaReservasNewReserva.getHuesped();
                    listaReservasNewReserva.setHuesped(huesped);
                    listaReservasNewReserva = em.merge(listaReservasNewReserva);
                    if (oldHuespedOfListaReservasNewReserva != null && !oldHuespedOfListaReservasNewReserva.equals(huesped)) {
                        oldHuespedOfListaReservasNewReserva.getListaReservas().remove(listaReservasNewReserva);
                        oldHuespedOfListaReservasNewReserva = em.merge(oldHuespedOfListaReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = huesped.getId_huesped();
                if (findHuesped(id) == null) {
                    throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.");
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
            Huesped huesped;
            try {
                huesped = em.getReference(Huesped.class, id);
                huesped.getId_huesped();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaReservas = huesped.getListaReservas();
            for (Reserva listaReservasReserva : listaReservas) {
                listaReservasReserva.setHuesped(null);
                listaReservasReserva = em.merge(listaReservasReserva);
            }
            em.remove(huesped);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huesped> findHuespedEntities() {
        return findHuespedEntities(true, -1, -1);
    }

    public List<Huesped> findHuespedEntities(int maxResults, int firstResult) {
        return findHuespedEntities(false, maxResults, firstResult);
    }

    private List<Huesped> findHuespedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huesped.class));
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

    public Huesped findHuesped(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huesped.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuespedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huesped> rt = cq.from(Huesped.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
