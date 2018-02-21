 
package br.edu.ifpb.infra;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    
    public static MongoDatabase getDatabase() {
        return new MongoClient("172.17.0.3", 27017)
                .getDatabase("security-messenger");
    }
    
}
