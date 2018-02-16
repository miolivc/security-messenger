
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

public abstract class Message implements Serializable {
    
    private int id;
    private User send;
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
