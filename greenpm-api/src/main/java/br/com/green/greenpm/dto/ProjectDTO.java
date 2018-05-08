package br.com.green.greenpm.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.green.greenpm.utils.Constants;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-25
 */
@Entity
@Table(name = "PROJECT", schema = "pm")
@SequenceGenerator(name = "project_seq", sequenceName = "project_seq", 
    initialValue = 1, allocationSize = 1)
public class ProjectDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @Column(name = "id_project")
    private Long idProject;
    
    @JsonFormat(pattern = Constants.DAT_EN_FMT)
    @Column(name = "dat_init_plan")
    private Date planDatInit;
    
    @JsonFormat(pattern = Constants.DAT_EN_FMT)
    @Column(name = "dat_end_plan")
    private Date planDatEnd;
    
    @Column(name = "cod_project")
    private String projectCod;
    
    @Column(name = "dsc_project")
    private String projectName;
    
    @Column(name = "dsc_persisted_by")
    private String persistedBy;
    
    @Column(name = "flg_closed_project")
    private String flgClosedProject;
    
    @ManyToOne
    @JoinColumn(name = "fk_id_user")
    private UserDTO user;
    
    @ManyToOne
    @JoinColumn(name = "fk_id_manager")
    private ManagerDTO manager;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project")
    private List<EmployeeDTO> employee;
    
    public ProjectDTO() {
        persistedBy = Constants.PERSISTENCE_API;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public Date getPlanDatInit() {
        return planDatInit;
    }

    public void setPlanDatInit(Date planDatInit) {
        this.planDatInit = planDatInit;
    }

    public Date getPlanDatEnd() {
        return planDatEnd;
    }

    public void setPlanDatEnd(Date planDatEnd) {
        this.planDatEnd = planDatEnd;
    }

    public String getProjectCod() {
        return projectCod;
    }

    public void setProjectCod(String projectCod) {
        this.projectCod = projectCod;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    public List<EmployeeDTO> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeDTO> employee) {
        this.employee = employee;
    }

    public String getFlgClosedProject() {
        return flgClosedProject;
    }

    public void setFlgClosedProject(String flgClosedProject) {
        this.flgClosedProject = flgClosedProject;
    }

    public void setPersistedBy(String persistedBy) {
        this.persistedBy = persistedBy;
    }

    public String getPersistedBy() {
        return persistedBy;
    }
}