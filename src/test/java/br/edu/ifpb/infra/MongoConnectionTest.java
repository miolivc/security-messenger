package br.edu.ifpb.infra;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import com.mongodb.client.MongoDatabase;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MongoConnectionTest {

    private MongoDatabase database;

    @Before
    public void init() {
        this.database = MongoConnection.getDatabase();
    }

    @Test
    public void connect() {
//        database.createCollection("test");
        Assert.assertNotNull(database.getCollection("test"));
    }

    @Test
    public void insertIdent() throws NoSuchAlgorithmException {
        
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        
        KeyPair genKeyPair = generator.genKeyPair();
        
        User user = new User("miolivc", "Michelle Oliveira", "12345");
        UserIdentifier ident = new UserIdentifier(user, genKeyPair.getPublic(),
                genKeyPair.getPrivate());
        
        Document identDoc = new Document("id", ident.getUser().getUsername());
        identDoc.append("user", ident.getUser());
        identDoc.append("privateKey", ident.getSecretKey().getEncoded());
        identDoc.append("publicKey",  ident.getKey().getEncoded());

        database.getCollection("test").insertOne(identDoc);
    }

}
