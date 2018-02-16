
package br.edu.ifpb.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.ejb.Stateless;

public class CredentialManager {
    
    private final int LENGHT_KEY = 2048;
    private KeyPair keyPair;
    private KeyPairGenerator generator;
    
    public CredentialManager() {
        try {
            this.generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("CredentialManager" + ex.getMessage());
        }
        generator.initialize(LENGHT_KEY);
        this.keyPair = generator.genKeyPair();
    }
    
    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }
    
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }
    
}
