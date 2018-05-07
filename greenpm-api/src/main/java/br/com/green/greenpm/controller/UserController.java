package br.com.green.greenpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;
import br.com.green.greenpm.model.UserModel;
import br.com.green.greenpm.service.UserService;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/{username}")
    public ResponseEntity<UserModel> getUser(@PathVariable(value = "username") String username) throws EntityNotFoundException {
        UserModel user = userService.getUserByUsername(username);
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserModel>> listAllUsers() {
        List<UserModel> usersList = userService.listAllUsers();
        return new ResponseEntity<List<UserModel>>(usersList, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO user) throws EntityExistsException {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}