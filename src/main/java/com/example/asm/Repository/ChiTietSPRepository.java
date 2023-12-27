package com.example.asm.Repository;

import com.example.asm.Model.ChiTietSP;
import com.example.asm.Util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChiTietSPRepository {
    public List<ChiTietSP> getAll() {
        List<ChiTietSP> listChiTietSP = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from ChiTietSP ");
            listChiTietSP = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChiTietSP;
    }

    public ChiTietSP getOne(UUID id) {
        ChiTietSP chiTietSP = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from ChiTietSP WHERE id=:id");
            query.setParameter("id", id);
            chiTietSP = (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSP;
    }

    public boolean add(ChiTietSP ctsp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ChiTietSP ctsp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(ChiTietSP ctsp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
