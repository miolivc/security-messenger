 
package br.edu.ifpb.infra;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    
    public static MongoDatabase getDatabase() {
        return new MongoClient("host-mongo", 27017)
                .getDatabase("security-messenger");
    }
    
}
