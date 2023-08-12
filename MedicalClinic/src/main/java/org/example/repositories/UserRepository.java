package org.example.repositories;

import org.example.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository {
    private final Session session;

    private final Transaction transaction;

    public UserRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public User createOrUpdate(User user){
        try {
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    public List<User> getAll(){
        String getAll= "select User from User user";
        Query<User> getAllQuery = session.createQuery(getAll, User.class);
        List<User> User  = getAllQuery.getResultList();
        return User;
    }

    public User getBYId(Long id){
        User user = session.get(User.class, id);
        return user;
    }

    public void deleteById(Long id){
        User user = getBYId(id);
        session.remove(user);
    }
}

