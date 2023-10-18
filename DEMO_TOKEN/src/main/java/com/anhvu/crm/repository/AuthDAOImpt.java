package com.anhvu.crm.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anhvu.crm.entity.AuthToken;
import com.anhvu.crm.interfaces.AuthDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;

@Repository
public class AuthDAOImpt implements AuthDAO {

    private EntityManager entityManager;

    @Autowired
    public AuthDAOImpt(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public AuthToken findById(int id) {
        TypedQuery<AuthToken> query = entityManager.createQuery("from AuthToken where id:=theData", AuthToken.class);
        query.setParameter("theData", id);
        AuthToken authToken = query.getSingleResult();
        return authToken;
    }

    @Override
    public AuthToken findByKeyToken(String keyToken) {
        TypedQuery<AuthToken> query = entityManager.createQuery("from AuthToken where keyToken = :theData",
                AuthToken.class);
        query.setParameter("theData", keyToken);
        List<AuthToken> authTokens = query.getResultList();

        if (!authTokens.isEmpty()) {
            return authTokens.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<AuthToken> findByUserId(int id) {
        TypedQuery<AuthToken> query = entityManager.createQuery("from AuthToken where userId=:theData",
                AuthToken.class);
        query.setParameter("theData", id);
        List<AuthToken> authTokens = query.getResultList();
        return authTokens;
    }

    @Override
    public void save(AuthToken theToken) {
        entityManager.merge(theToken);
    }

    @Override
    public void delete(int id) {
        Query query = entityManager.createQuery("delete from AuthToken where id=:theData");
        query.setParameter("theData", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByUserId(int userId) {
        Query query = entityManager.createQuery("delete from AuthToken where userId=:theData");
        query.setParameter("theData", userId);
        query.executeUpdate();
    }

}
