package com.example.asm.Repository;

import com.example.asm.Model.DongSP;
import com.example.asm.Util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DongSPRepository {
    public List<DongSP> getAll() {
        List<DongSP> listDongSP = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from DongSP ");
            listDongSP = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDongSP;
    }

    public DongSP getOne(UUID id) {
        DongSP dongSP = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from DongSP WHERE id=:id ");
            query.setParameter("id", id);
            dongSP = (DongSP) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSP;
    }

    public Boolean add(DongSP dongSP) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(dongSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(DongSP dongSP) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(dongSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(DongSP dongSP) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(dongSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
