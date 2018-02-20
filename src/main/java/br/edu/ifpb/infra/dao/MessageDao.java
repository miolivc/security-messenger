package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.MessageDTO;
import br.edu.ifpb.domain.TextMessage;
import org.eclipse.persistence.internal.jpa.metamodel.EntityTypeImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.PublicKey;
import java.util.List;

@Stateless
public class MessageDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(MessageDTO msg) {
        manager.persist(msg);
    }

    public List<MessageDTO> getBySender(PublicKey sender) {
        return manager.createQuery("SELECT msg FROM MessageDTO msg " +
                "WHERE msg.sender =:sender")
                .setParameter("sender", sender)
                .getResultList();
    }

    public List<MessageDTO> getByReceiver(PublicKey receiver) {
        return manager.createQuery("SELECT msg FROM MessageDTO msg " +
                "WHERE msg.receiver =:receiver")
                .setParameter("receiver", receiver)
                .getResultList();
    }

    public List<MessageDTO> get(PublicKey sender, PublicKey receiver) {
        return manager.createQuery("SELECT msg FROM MessageDTO msg" +
                " WHERE msg.sender =:sender AND msg.receiver =:receiver")
                .setParameter("sender", sender)
                .setParameter("receiver", receiver)
                .getResultList();
    }

}
