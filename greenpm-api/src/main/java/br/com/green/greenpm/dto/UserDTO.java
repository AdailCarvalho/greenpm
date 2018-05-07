package br.com.green.greenpm.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.green.greenpm.utils.Constants;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-26
 *
 */
@Entity
@Table(name = "SYS_USER", schema = "auth")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", 
initialValue = 1, allocationSize = 1)
public class UserDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id_user")
    private Long idUser;
    
    @Column(name = "cod_username", unique = true)
    private String username;
    
    @Column(name = "cod_password")
    private String password;
    
    @Column(name = "dsc_user")
    private String dscUsername;
    
    @Column(name = "flg_is_admin" )
    private String flgIsAdmin;
    
    @Column(name = "dsc_persisted_by")
    private String persistedBy;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<ProjectDTO> project;
    
    public UserDTO() {
        persistedBy = Constants.PERSISTENCE_API;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDscUsername() {
        return dscUsername;
    }

    public void setDscUsername(String dscUsername) {
        this.dscUsername = dscUsername;
    }

    public List<ProjectDTO> getProject() {
        return project;
    }

    public void setProject(List<ProjectDTO> project) {
        this.project = project;
    }

    public String getPersistedBy() {
        return persistedBy;
    }
}