package dailyreport.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import dailyreport.business.ReportSet;

public class ReportSetDB {
    public static ReportSet getReportSetById(String id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            ReportSet rs = em.find(ReportSet.class, id);
            return rs;
        }
        finally {
            em.close();
        }
    }
    
    public static void insert(ReportSet rs) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        trans.begin();
        try {
            em.persist(rs);
            trans.commit();
        }
        catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        }
        finally {
            em.close();
        }
    }
    
    public static void update(ReportSet rs) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        trans.begin();
        try {
            em.merge(rs);
            trans.commit();
        }
        catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        }
        finally {
            em.close();
        }
    }
}
