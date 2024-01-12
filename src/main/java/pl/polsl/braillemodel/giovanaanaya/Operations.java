/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pl.polsl.braillemodel.giovanaanaya;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
 * Operations class uses the entity manager and includes 
 * methods for persisting and retrieving objects using JPA.
 *
 * @author Giovana Anaya
 * @version 1.0
 */
public class Operations {

    /**
     * Persists the specified object using JPA.
     *
     * @param object The object to persist.
     */
    public void persistObject(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BraillePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(object);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            em.close();
        }
    }
    /**
     * Retrieves a list of HistoryEntity objects using JPA.
     *
     * @return A list of HistoryEntity objects.
     */
    public List<HistoryEntity> findObjects() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BraillePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        List<HistoryEntity> conversionList = null;

        try {
            transaction.begin();
            Query query = em.createQuery("SELECT p FROM HistoryEntity p");
            conversionList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }

        return conversionList;
    }

}
