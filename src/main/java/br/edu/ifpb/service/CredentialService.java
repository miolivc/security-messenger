package br.edu.ifpb.service;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.infra.dao.UserIdentifierCrypt;
import br.edu.ifpb.infra.dao.UserIdentifierDao;
import br.edu.ifpb.infra.query.PublicUserIdentifier;
import br.edu.ifpb.security.CredentialManager;
import br.edu.ifpb.exception.NotIdentifiedException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CredentialService {

    @Inject
    private UserIdentifierDao idents;
    @Inject
    private UserIdentifierCrypt cryptIdents;
//    @Inject
//    private CredentialManager cm;

    public CredentialService() {
//        cm.prepare();
    }

    public void createCredential(User user) {
//        UserIdentifier ident = new UserIdentifier(user, cm.getPublicKey(),
//                cm.getPrivateKey());
        CredentialManager.prepare();
        UserIdentifier ident = new UserIdentifier(user, CredentialManager.getPublicKey(),
                CredentialManager.getPrivateKey());
        idents.save(ident);
        cryptIdents.saveIdent(ident);
    }

    public UserIdentifier getPublicCredential(String username) {
        return idents.getOne(username);
    }

    public List<PublicUserIdentifier> getAllCredential() {
        return idents.getAll();
    }

    public UserIdentifier getPrivateCredential(User user) 
            throws NotIdentifiedException {
        UserIdentifier identifier = idents.getOne(user);
        if (user.equals(identifier.getUser())) {
            return cryptIdents.getCredential(user);
        }
        throw new NotIdentifiedException("Unauthorized: Wrong parameter for user");
    }

}
