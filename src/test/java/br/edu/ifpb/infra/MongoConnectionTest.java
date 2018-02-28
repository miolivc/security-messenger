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

    
}
