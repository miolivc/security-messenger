package br.edu.ifpb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.security.PublicKey;

@Entity
@SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq")

public class MessageDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
    private int id;

    private PublicKey sender;

    private PublicKey receiver;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Lob
    private byte[] content;

    public MessageDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PublicKey getSender() {
        return sender;
    }

    public void setSender(PublicKey sender) {
        this.sender = sender;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
