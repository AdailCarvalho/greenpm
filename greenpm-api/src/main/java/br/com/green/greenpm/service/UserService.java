package br.com.green.greenpm.service;

import java.util.List;

import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;
import br.com.green.greenpm.model.UserModel;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public interface UserService {
    
    /**
     * 
     * @param username The system user's username.
     * @return A representation of the user.
     * 
     * @throws EntityNotFoundException if no user with the passed username was found.
     */
    public UserModel getUserByUsername(String username) throws EntityNotFoundException;
    
    /**
     * 
     * Lists all registered users on the system database.
     * 
     * @return a list containing all registered users.
     */
    public List<UserModel> listAllUsers();
    
    /**
     * Registers a new system user.
     * 
     * @param user An object with users information
     * 
     * @throws EntityExistsException if a user with the same username already exists on database.
     */
    public void saveUser(UserDTO user) throws EntityExistsException;
}