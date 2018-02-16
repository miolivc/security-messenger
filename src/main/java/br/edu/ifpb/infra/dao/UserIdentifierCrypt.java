package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bson.Document;

@Stateless
public class UserIdentifierCrypt {

    @Inject
    private MongoDatabase database;
    private final MongoCollection<Document> collection;

    {
        this.collection = database.getCollection("test");
    }

    public void saveIdent(UserIdentifier ident) {
        Document userDoc = new Document("username", ident.getUser().getUsername());
        userDoc.append("name", ident.getUser().getName());
        userDoc.append("password", ident.getUser().getPassword());

        Document identDoc = new Document("publicKey", ident.getKey());
        identDoc.append("privateKey", ident.getSecretKey());
        identDoc.append("user", userDoc);

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
