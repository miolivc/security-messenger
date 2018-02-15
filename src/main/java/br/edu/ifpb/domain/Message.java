
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
@SequenceGenerator(
        name = "message_seq", 
        sequenceName = "message_seq", 
        initialValue = 1000
)
public abstract class Message implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    private int id;
    @OneToOne
    private User send;
    @OneToOne
    private User receiver;
    private final ZonedDateTime createdIn;
    
    {
        this.createdIn = ZonedDateTime.now();
    }

    public Message(User send, User receiver) {
        this.send = send;
        this.receiver = receiver;
    }
    
    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSend() {
        return send;
    }

    public void setSend(User send) {
        this.send = send;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    
}
