package br.edu.ifpb.service;

import br.edu.ifpb.domain.Message;
import br.edu.ifpb.domain.MessageDTO;
import br.edu.ifpb.infra.dao.MessageDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

@Stateless
public class MessageService {

    @Inject
    private MessageDao messages;

    public MessageService() {
    }

    /**
     * Return a list message from receiver public key
     * @param receiver
     * @return List<MessageDTO>
     */
    public List<MessageDTO> getAllMessages(PublicKey receiver) {
        return Collections.unmodifiableList(messages.getByReceiver(receiver));
    }

    public void saveMessage(MessageDTO message) {
        messages.save(message);
    }
}
