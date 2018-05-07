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
 * @since 2018-04-25
 */
@Entity
@Table(name = "MANAGER", schema = "staff")
@SequenceGenerator(name = "manager_seq", sequenceName = "manager_seq", 
    initialValue = 1, allocationSize = 1)
public class ManagerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_seq")
    @Column(name = "id_manager")
    private Long idManager;
    
    @Column(name = "cod_manager", unique = true)
    private String codManager;
    
    @Column(name = "dsc_name")
    private String name;
    
    @Column(name = "dsc_email")
    private String email;
    
    @Column(name = "dsc_skill")
    private String skill;
    
    @Column(name = "dsc_persisted_by")
    private String persistedBy;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manager")
    private List<ProjectDTO> project;
    
    public ManagerDTO() {
        persistedBy = Constants.PERSISTENCE_API;
    }
    
    public ManagerDTO(String codManager, String name, String email) {
        this.codManager = codManager;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCodManager() {
        return codManager;
    }

    public void setCodManager(String codManager) {
        this.codManager = codManager;
    }

    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
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