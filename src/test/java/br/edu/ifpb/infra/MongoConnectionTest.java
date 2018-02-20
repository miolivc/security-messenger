
package br.edu.ifpb.infra;

import com.mongodb.client.MongoDatabase;
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
        database.createCollection("test");
        Assert.assertNotNull(database.getCollection("test"));
    }

}
