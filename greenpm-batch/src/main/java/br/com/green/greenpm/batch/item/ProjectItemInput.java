package br.com.green.greenpm.batch.item;

import java.sql.Date;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-02-03
 *
 */
public class ProjectItemInput {
    
    private Long projectId;
    
    private Long managerId;
    
    private Long userId;
    
    private String projectCod;
    
    private String projectName;
    
    private Date datInitPlan;
    
    private Date datEndPlan;
    
    private String persistedBy;
    
    private String flgClosedProject;
    
    public ProjectItemInput() {
        persistedBy = "BATCH";
        flgClosedProject = "N";
    }
    
    public ProjectItemInput(Long projectId) {
        this.projectId = projectId;
    }

    public ProjectItemInput(Long projectId, Long managerId, String projectCod) {
        this.projectId = projectId;
        this.managerId = managerId;
        this.projectCod = projectCod;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getDatInitPlan() {
        return datInitPlan;
    }

    public void setDatInitPlan(Date datInitPlan) {
        this.datInitPlan = datInitPlan;
    }

    public Date getDatEndPlan() {
        return datEndPlan;
    }

    public void setDatEndPlan(Date datEndPlan) {
        this.datEndPlan = datEndPlan;
    }

    public String getPersistedBy() {
        return persistedBy;
    }

    public void setPersistedBy(String persistedBy) {
        this.persistedBy = persistedBy;
    }

    public String getFlgClosedProject() {
        return flgClosedProject;
    }

    public void setFlgClosedProject(String flgClosedProject) {
        this.flgClosedProject = flgClosedProject;
    }
}