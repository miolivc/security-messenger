package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.infra.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.ejb.Stateless;
import org.bson.Document;

@Stateless
public class UserIdentifierCrypt {

    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public UserIdentifierCrypt() {
        this.database = MongoConnection.getDatabase();
        this.collection = database.getCollection("ident");
    }

    public void saveIdent(UserIdentifier ident) {
        
        byte[] privateKey = ident.getSecretKey().getEncoded();
        byte[] publicKey = ident.getKey().getEncoded();
        
        Document identDoc = new Document("user", ident.getUser());
        identDoc.append("privateKey", privateKey);
        identDoc.append("publicKey",  publicKey);

        collection.insertOne(identDoc);
    }

    public UserIdentifier getCredential(User user) {
        Document userDoc = new Document("username", user.getUsername());
        userDoc.append("name", user.getName());
        userDoc.append("password", user.getPassword());

        Document docFilter = new Document("user", userDoc);
        MongoCursor cursor = collection.find().filter(docFilter).iterator();

        UserIdentifier ident = new UserIdentifier();

        while (cursor.hasNext()) {
            Document document = (Document) cursor.next();
            ident.setUser(user);
            ident.setKey((PublicKey) document.get("publicKey"));
            ident.setSecretKey((PrivateKey) document.get("privateKey"));
            break;
        }
        return ident;
    }

}
