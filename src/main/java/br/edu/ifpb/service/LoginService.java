package br.edu.ifpb.service;

import br.edu.ifpb.domain.User;
import br.edu.ifpb.domain.UserIdentifier;
import br.edu.ifpb.infra.dao.UserDao;
import br.edu.ifpb.exception.NotIdentifiedException;
import br.edu.ifpb.exception.UserException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginService {

    @Inject
    private UserDao users;
    @EJB
    private CredentialService credentialService;

    public LoginService() {
    }

    public void signin(String username, String password, String name) throws UserException {
        User user = new User(username, name, password);

        if (users.getAll().contains(user)) {
            throw new UserException("User already created!");
        }
        if (password.length() > 16) {
            throw new UserException("Password Parameter is invalid!");
        }

        users.save(user);
        credentialService.createCredential(user);
    }

    public void signin(User user) throws UserException {

        if (users.getAll().contains(user)) {
            throw new UserException("User already created!");
        }
        if (user.getPassword().length() > 16) {
            throw new UserException("Password Parameter is invalid!");
        }

        users.save(user);
        credentialService.createCredential(user);
    }

    public UserIdentifier login(String username, String password) {
        User user = users.getOne(username);
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            try {
                return credentialService.getPrivateCredential(user);
            } catch (NotIdentifiedException ex) {
                System.err.println("Login class: " + ex.getMessage());
            }
        }
        return null;
    }

}
