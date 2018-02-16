
package br.edu.ifpb.domain;

public class TextMessage extends Message {
    
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
