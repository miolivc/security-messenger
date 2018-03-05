package br.edu.ifpb.infra.query;


import java.security.PublicKey;

public class PublicUserIdentifier {

    private String name;
    private PublicKey key;

    public PublicUserIdentifier(String name, PublicKey key) {
        this.name = name;
        this.key = key;
    }

    public PublicUserIdentifier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicKey getKey() {
        return key;
    }

    public void setKey(PublicKey key) {
        this.key = key;
    }
}
