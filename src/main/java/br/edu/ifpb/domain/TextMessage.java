/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 *
 * @author miolivc
 */
@Entity
public class TextMessage extends Message {
    
    @Lob
    private String text;

    public TextMessage(User send, User receiver, String text) {
        super(send, receiver);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
