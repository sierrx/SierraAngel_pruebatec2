package com.turnos.persistencia;

import com.turnos.model.Ciudadano;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.turnos.model.Turno;
import com.turnos.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class CiudadanoJpaController implements Serializable {

    public CiudadanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
     public CiudadanoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("TurnoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudadano ciudadano) {
        if (ciudadano.getListaTurno() == null) {
            ciudadano.setListaTurno(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedListaTurno = new ArrayList<Turno>();
            for (Turno listaTurnoTurnoToAttach : ciudadano.getListaTurno()) {
                listaTurnoTurnoToAttach = em.getReference(listaTurnoTurnoToAttach.getClass(), listaTurnoTurnoToAttach.getId());
                attachedListaTurno.add(listaTurnoTurnoToAttach);
            }
            ciudadano.setListaTurno(attachedListaTurno);
            em.persist(ciudadano);
            for (Turno listaTurnoTurno : ciudadano.getListaTurno()) {
                Ciudadano oldCiudadanoOfListaTurnoTurno = listaTurnoTurno.getCiudadano();
                listaTurnoTurno.setCiudadano(ciudadano);
                listaTurnoTurno = em.merge(listaTurnoTurno);
                if (oldCiudadanoOfListaTurnoTurno != null) {
                    oldCiudadanoOfListaTurnoTurno.getListaTurno().remove(listaTurnoTurno);
                    oldCiudadanoOfListaTurnoTurno = em.merge(oldCiudadanoOfListaTurnoTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudadano ciudadano) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudadano persistentCiudadano = em.find(Ciudadano.class, ciudadano.getId());
            List<Turno> listaTurnoOld = persistentCiudadano.getListaTurno();
            List<Turno> listaTurnoNew = ciudadano.getListaTurno();
            List<Turno> attachedListaTurnoNew = new ArrayList<Turno>();
            for (Turno listaTurnoNewTurnoToAttach : listaTurnoNew) {
                listaTurnoNewTurnoToAttach = em.getReference(listaTurnoNewTurnoToAttach.getClass(), listaTurnoNewTurnoToAttach.getId());
                attachedListaTurnoNew.add(listaTurnoNewTurnoToAttach);
            }
            listaTurnoNew = attachedListaTurnoNew;
            ciudadano.setListaTurno(listaTurnoNew);
            ciudadano = em.merge(ciudadano);
            for (Turno listaTurnoOldTurno : listaTurnoOld) {
                if (!listaTurnoNew.contains(listaTurnoOldTurno)) {
                    listaTurnoOldTurno.setCiudadano(null);
                    listaTurnoOldTurno = em.merge(listaTurnoOldTurno);
                }
            }
            for (Turno listaTurnoNewTurno : listaTurnoNew) {
                if (!listaTurnoOld.contains(listaTurnoNewTurno)) {
                    Ciudadano oldCiudadanoOfListaTurnoNewTurno = listaTurnoNewTurno.getCiudadano();
                    listaTurnoNewTurno.setCiudadano(ciudadano);
                    listaTurnoNewTurno = em.merge(listaTurnoNewTurno);
                    if (oldCiudadanoOfListaTurnoNewTurno != null && !oldCiudadanoOfListaTurnoNewTurno.equals(ciudadano)) {
                        oldCiudadanoOfListaTurnoNewTurno.getListaTurno().remove(listaTurnoNewTurno);
                        oldCiudadanoOfListaTurnoNewTurno = em.merge(oldCiudadanoOfListaTurnoNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ciudadano.getId();
                if (findCiudadano(id) == null) {
                    throw new NonexistentEntityException("The ciudadano with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudadano ciudadano;
            try {
                ciudadano = em.getReference(Ciudadano.class, id);
                ciudadano.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudadano with id " + id + " no longer exists.", enfe);
            }
            List<Turno> listaTurno = ciudadano.getListaTurno();
            for (Turno listaTurnoTurno : listaTurno) {
                listaTurnoTurno.setCiudadano(null);
                listaTurnoTurno = em.merge(listaTurnoTurno);
            }
            em.remove(ciudadano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudadano> findCiudadanoEntities() {
        return findCiudadanoEntities(true, -1, -1);
    }

    public List<Ciudadano> findCiudadanoEntities(int maxResults, int firstResult) {
        return findCiudadanoEntities(false, maxResults, firstResult);
    }

    private List<Ciudadano> findCiudadanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudadano.class));
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

    public Ciudadano findCiudadano(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudadano.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadanoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudadano> rt = cq.from(Ciudadano.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}