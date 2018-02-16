
package br.edu.ifpb.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.persistence.Column;

public class MediaMessage extends Message {
 
    private byte[] content;
    
    @Column(length = 10)
    private String extension;
    
    public MediaMessage(File file) {
        this.extension = file.getName().split("\\.")[1];
        try {
            this.content = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            System.err.println("MediaMessage" + ex.getMessage());
        }
    }

    public MediaMessage() {
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
}