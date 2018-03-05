
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ident_seq", sequenceName = "ident_seq")
public class UserIdentifier implements Serializable {

    @Id
    @GeneratedValue(generator = "ident_seq", strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    
    @Column(unique = true)
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
