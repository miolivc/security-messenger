/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import br.edu.ifpb.security.CredentialManager;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author miolivc
 */
@Entity
public class UserIdentifier implements Serializable {
    
    @Column(unique = true)
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;
    
    @Id
    private PublicKey key;
    
    @Transient
    private PrivateKey secretKey;

    public UserIdentifier(User user) {
        this.user = user;
        CredentialManager manager = new CredentialManager();
        this.key = manager.getPublicKey();
        this.secretKey = manager.getPrivateKey();
    }

    public UserIdentifier() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PublicKey getKey() {
        return key;
    }

    public void setKey(PublicKey key) {
        this.key = key;
    }

    public PrivateKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(PrivateKey secretKey) {
        this.secretKey = secretKey;
    }
    
}
