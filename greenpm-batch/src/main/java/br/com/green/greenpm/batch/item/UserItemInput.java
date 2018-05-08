package br.com.green.greenpm.batch.item;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-07
 *
 */
public class UserItemInput {
    
    private Long id;
    
    private String username;
    
    private String dscUsername;
    
    private String password;
    
    private String flgIsAdmin;
    
    private String persistedBy;
    
    public UserItemInput() {
        this.flgIsAdmin = "N";
        this.persistedBy = "BATCH";
    }

    public UserItemInput(String username, String dscUsername, String password, String flgIsAdmin) {
        this.username = username;
        this.dscUsername = dscUsername;
        this.password = password;
        this.flgIsAdmin = flgIsAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDscUsername() {
        return dscUsername;
    }

    public void setDscUsername(String dscUsername) {
        this.dscUsername = dscUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlgIsAdmin() {
        return flgIsAdmin;
    }

    public void setFlgIsAdmin(String flgIsAdmin) {
        this.flgIsAdmin = flgIsAdmin;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersistedBy() {
        return persistedBy;
    }

    public void setPersistedBy(String persistedBy) {
        this.persistedBy = persistedBy;
    }

    @Override
    public String toString() {
        return "UserItemInput [id=" + id + ", username=" + username + ", dscUsername=" + dscUsername + ", password="
                + password + ", flgIsAdmin=" + flgIsAdmin + ", persistedBy=" + persistedBy + "]";
    }
}
