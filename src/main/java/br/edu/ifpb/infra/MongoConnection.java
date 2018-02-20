
package br.edu.ifpb.infra;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import javax.enterprise.inject.Produces;

public class MongoConnection {
    
    @Produces
    public static MongoDatabase getDatabase() {
        return new MongoClient("172.17.0.2", 27017)
                .getDatabase("security-messenger");
    }
    
}
