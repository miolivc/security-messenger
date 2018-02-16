
package br.ifpb.edu.service;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.infra.dao.UserDao;
import br.ifpb.edu.exception.UserException;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginService {
    
    @Inject
    private UserDao users;
    @Inject
    private CredentialService credentialService;
    
    private void sigin(String username, String password, String name)
            throws UserException {
        if (users.getOne(username) != null) {
            throw new UserException("User already created!");
        }
        User user = new User(username, name, password);
        users.save(user);
        credentialService.createCredential(user);
    }
    
    private boolean login(String username, String password) 
            throws UserException {
        User user = users.getOne(username);
        return user.getUsername().equals(username) && 
                user.getPassword().equals(password);
    }
    
}
