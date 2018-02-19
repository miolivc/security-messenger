
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class UserIdentifier implements Serializable {
    
    @OneToOne(cascade = CascadeType.MERGE)
    private User user;
    
    @Id
    private PublicKey key;
    
    @Transient
    private PrivateKey secretKey;

    public UserIdentifier(User user, PublicKey key, PrivateKey secretKey) {
        this.user = user;
        this.key = key;
        this.secretKey = secretKey;
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
