package br.com.green.greenpm.service.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;
import br.com.green.greenpm.formatter.DataFormatter;
import br.com.green.greenpm.model.UserModel;
import br.com.green.greenpm.repository.UserRepository;
import br.com.green.greenpm.service.UserService;
import br.com.green.greenpm.utils.Constants;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserRepository userRepo;

    @Transactional(readOnly = true)
    @Override
    public UserModel getUserByUsername(String username) throws EntityNotFoundException {
        Optional<UserDTO> optUser = userRepo.findSysUserByUsername(username);
        if (!optUser.isPresent()) {
            LOGGER.error("User with the given name was not found =>" + username);
            throw new EntityNotFoundException(UserDTO.class, "username", username);
        }
        
        UserModel user = new UserModel(optUser.get().getIdUser(), 
                optUser.get().getUsername(), 
                optUser.get().getDscUsername(),
                optUser.get().getFlgIsAdmin().equals("Y") ? true : false);
        
        return user; 
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserModel> listAllUsers() {
        Page<UserDTO> users = userRepo.findAll(new PageRequest(0, Constants.MAX_LIMIT));
        return 
                DataFormatter.buildUserModelFromDTOList(users.getContent());
    }

    @Transactional
    @Override
    public void saveUser(UserDTO user) throws EntityExistsException {
        Optional<UserDTO> optUser = userRepo.findSysUserByUsername(user.getUsername());
        if (optUser.isPresent()) {
            LOGGER.error("User with same username already exists.");
            throw new EntityExistsException(UserDTO.class, "username", user.getUsername());
        }
        
        this.userRepo.save(user);
    }
}