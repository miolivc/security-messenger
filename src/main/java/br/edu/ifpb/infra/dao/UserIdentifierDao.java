
package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserIdentifierDao {
    
    @PersistenceContext
    private EntityManager manager;

    public UserIdentifierDao() {
    }
    
    public void save(UserIdentifier user) {
        manager.persist(user);
    }
    
    public List<UserIdentifier> getAll() {
        return manager.createQuery("FROM UserIdentifier user",
                                UserIdentifier.class)
                      .getResultList();
    }
    
    public UserIdentifier getOne(String username) {
        return manager.createQuery("SELECT ident FROM UserIdentifier ident "
                + "WHERE ident.user.username = :username", UserIdentifier.class)
                .setParameter("username", username)
                .getSingleResult();
    }
    
    public UserIdentifier getOne(User user) {
        return manager.createQuery("SELECT ident FROM UserIdentifier ident "
                + "WHERE ident.user = :user", UserIdentifier.class)
                .setParameter("user", user)
                .getSingleResult();
    }
    
    public void delete(UserIdentifier ident) {
        manager.remove(ident);
    }
}
