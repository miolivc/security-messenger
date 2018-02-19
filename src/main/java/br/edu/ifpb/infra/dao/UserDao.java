
package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void save(User user) {
        manager.persist(user);
    }
    
    public List<User> getAll() {
        return manager.createQuery("FROM Account user", User.class)
                      .getResultList();
    }
    
    public User getOne(String username) {
        return manager.find(User.class, username);
    }
    
    public void delete(User user) {
        manager.remove(user);
    }
}
