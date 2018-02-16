
package br.edu.ifpb.infra;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import javax.enterprise.inject.Produces;

public class MongoConnection {
    
    @Produces
    public static MongoDatabase getDatabase() {
        return new MongoClient("localhost", 27017)
                .getDatabase("security-messenger");
    }
    
}
