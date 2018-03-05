
package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.infra.query.PublicUserIdentifier;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
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
    
    public List<PublicUserIdentifier> getAll() {

        List<PublicUserIdentifier> answer = new ArrayList<>();
        List resultList = manager.createQuery("SELECT u.user.name, u.key FROM UserIdentifier u").getResultList();

        resultList.forEach((object) -> {
            Object[] dados = (Object[]) object;
            String name = (String) dados[0];
            PublicKey key = (PublicKey) dados[1];
            answer.add(new PublicUserIdentifier(name, key));
        });

        return answer;
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
