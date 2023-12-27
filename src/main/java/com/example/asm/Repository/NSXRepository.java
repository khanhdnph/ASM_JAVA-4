package com.example.asm.Repository;

import com.example.asm.Model.NSX;
import com.example.asm.Util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NSXRepository {
    public List<NSX> getAll() {
        List<NSX> listNSX = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from NSX ");
            listNSX = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNSX;
    }

    public NSX getOne(UUID id) {
        NSX nsx = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from NSX WHERE id=:id ");
            query.setParameter("id", id);
            nsx = (NSX) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nsx;
    }

    public Boolean add(NSX nsx) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(NSX nsx) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(NSX nsx) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
