package com.example.asm.Repository;

import com.example.asm.Model.CuaHang;
import com.example.asm.Util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CuaHangRepository {
    public List<CuaHang> getAll() {
        List<CuaHang> listCuaHang = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from CuaHang ");
            listCuaHang = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listCuaHang;
    }

    public CuaHang getOne(UUID id) {
        CuaHang cuaHang = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("from CuaHang WHERE id=:id");
            query.setParameter("id", id);
            cuaHang = (CuaHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return cuaHang;
    }

    public boolean add(CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public boolean update(CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public boolean delete(CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}
