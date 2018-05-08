package br.com.green.greenpm.batch.item;

import java.sql.Date;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-06
 *
 */
public class ProjectRawItemOutput {

    private String codProject;
    
    private String codEmployee;
    
    private String codManager;
    
    private String projectName;
    
    private Date plannedStart;
    
    private Date plannedEnd;
    
    private String pm;
    
    private String pmEmail;
    
    private String pmSkills;
    
    private String employeeName;
    
    private String employeeEmail;
    
    private String employeeTeam;
    
    private String employeeSkills;
   
    public ProjectRawItemOutput() {
        
    }

    public String getCodProject() {
        return codProject;
    }

    public void setCodProject(String codProject) {
        this.codProject = codProject;
    }

    public String getCodManager() {
        return codManager;
    }

    public void setCodManager(String codManager) {
        this.codManager = codManager;
    }

    public String getCodEmployee() {
        return codEmployee;
    }

    public void setCodEmployee(String codEmployee) {
        this.codEmployee = codEmployee;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getPlannedStart() {
        return plannedStart;
    }

    public void setPlannedStart(Date plannedStart) {
        this.plannedStart = plannedStart;
    }

    public Date getPlannedEnd() {
        return plannedEnd;
    }

    public void setPlannedEnd(Date plannedEnd) {
        this.plannedEnd = plannedEnd;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPmEmail() {
        return pmEmail;
    }

    public void setPmEmail(String pmEmail) {
        this.pmEmail = pmEmail;
    }

    public String getPmSkills() {
        return pmSkills;
    }

    public void setPmSkills(String pmSkills) {
        this.pmSkills = pmSkills;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


    public String getEmployeeEmail() {
        return employeeEmail;
    }


    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }


    public String getEmployeeTeam() {
        return employeeTeam;
    }


    public void setEmployeeTeam(String employeeTeam) {
        this.employeeTeam = employeeTeam;
    }


    public String getEmployeeSkills() {
        return employeeSkills;
    }


    public void setEmployeeSkills(String employeeSkills) {
        this.employeeSkills = employeeSkills;
    }
}