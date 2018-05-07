package br.com.green.greenpm.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.green.greenpm.utils.Constants;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-25
 *
 */
@Entity
@Table(name = "EMPLOYEE", schema = "staff")
@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", 
initialValue = 1, allocationSize = 1)
public class EmployeeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @Column(name = "id_employee")
    private Long idEmployee;
    
    @Column(name = "cod_employee", unique = true)
    private String codEmployee;
    
    @Column(name = "dsc_name")
    private String name;
    
    @Column(name = "dsc_email")
    private String email;
    
    @Column(name = "dsc_skill")
    private String skill;
    
    @Column(name = "dsc_team")
    private String team;
    
    @Column(name = "dsc_persisted_by")
    private String persistedBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_project")
    private ProjectDTO project;
    
    public EmployeeDTO() {
        persistedBy = Constants.PERSISTENCE_API;
    }

    public EmployeeDTO(Long idEmployee, String name, String email) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.email = email;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCodEmployee() {
        return codEmployee;
    }

    public void setCodEmployee(String codEmployee) {
        this.codEmployee = codEmployee;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }
    
    public String getPersistedBy() {
        return persistedBy;
    }

    @Override
    public String toString() {
        return "EmployeeDTO [idEmployee=" + idEmployee + ", codEmployee=" + codEmployee + ", name=" + name + ", email="
                + email + ", skill=" + skill + ", team=" + team + ", project=" + project + "]";
    }
}