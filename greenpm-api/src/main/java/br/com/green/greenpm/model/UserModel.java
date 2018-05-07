package br.com.green.greenpm.model;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public class UserModel {
    
    private Long id;
    
    private String username;
    
    private String descUsername;
    
    private Boolean isAdmin;
    
    public UserModel() {
        
    }
    
    public UserModel(Long id, String username, String descUsername, Boolean isAdmin) {
        super();
        this.id = id;
        this.username = username;
        this.descUsername = descUsername;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescUsername() {
        return descUsername;
    }

    public void setDescUsername(String descUsername) {
        this.descUsername = descUsername;
    }
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "UserModel [username=" + username + ", descUsername=" + descUsername + ", id=" + id + "]";
    }
}