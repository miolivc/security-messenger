package br.edu.ifpb.infra.dao;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.infra.MongoConnection;
import br.edu.ifpb.infra.attrconverter.KeyConverter;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javax.ejb.Stateless;
import org.bson.Document;
import org.bson.types.Binary;

@Stateless
public class UserIdentifierCrypt {

    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public UserIdentifierCrypt() {
        this.database = MongoConnection.getDatabase();
        this.collection = database.getCollection("ident");
    }

    public void saveIdent(UserIdentifier ident) {

        User user = ident.getUser();

        Document userDoc = new Document("username", user.getUsername());
        userDoc.append("name", user.getName());
        userDoc.append("password", user.getPassword());

        Document identDoc = new Document("user", userDoc);
        identDoc.append("privateKey", KeyConverter.convert(ident.getSecretKey()));
        identDoc.append("publicKey", ident.getKey().getEncoded());

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
            ident.setKey(KeyConverter.convertPub((byte[]) document.get("publicKey", Binary.class).getData()));
            ident.setSecretKey(KeyConverter.convert((byte[]) document.get("privateKey",Binary.class).getData()));
            break;
        }
        return ident;
    }

}
