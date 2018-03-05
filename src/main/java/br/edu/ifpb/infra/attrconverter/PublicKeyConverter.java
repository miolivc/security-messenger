/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra.attrconverter;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.KeyFactory;

/**
 * Auto convert all objects PublicKey from application for byte array 
 * because @Converter with autoApply = true
 * @author miolivc
 * @author alann
 */
@Converter(autoApply = true)
public class PublicKeyConverter implements AttributeConverter<PublicKey, byte[]>{

    @Override
    public byte[] convertToDatabaseColumn(PublicKey key) {
        return key.getEncoded();
    }

    @Override
    public PublicKey convertToEntityAttribute(byte[] byteKey) {
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(byteKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return publicKey;
    }
    
}
