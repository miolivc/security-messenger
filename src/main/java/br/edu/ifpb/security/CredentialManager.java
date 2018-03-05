
package br.edu.ifpb.security;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CredentialManager {
    
    private static final int LENGHT_KEY = 2048;
    private static KeyPair keyPair;
    private static KeyPairGenerator generator;
    
    public CredentialManager() {
    }

    public static void prepare() {
        try {
            CredentialManager.generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("CredentialManager" + ex.getMessage());
        }
        generator.initialize(LENGHT_KEY);
        CredentialManager.keyPair = generator.genKeyPair();
    }

    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }
    
    public static PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }
    
}
