package br.edu.ifpb.infra.attrconverter;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyConverter {

    public static PrivateKey convert(byte[] byteValue) {
        PrivateKey privateKey = null;
        try {
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(byteValue));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] convert(PrivateKey privateKey) {
        return privateKey.getEncoded();
    }

    public static PublicKey convertPub(byte[] byteKey) {
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(byteKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return publicKey;
    }
}
