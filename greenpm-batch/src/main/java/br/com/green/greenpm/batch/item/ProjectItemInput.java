package br.com.green.greenpm.batch.item;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public class ProjectItemInput {

    private String projectName;
    
    private String plannedStart;
    
    private String plannedEnd;
    
    private String pm;
    
    private String pmEmail;
    
    private String pmSkills;
    
    private String employeeName;
    
    private String employeeEmail;
    
    private String employeeTeam;
    
    private String employeeSkills;
    
    public ProjectItemInput() {
        
    }
    
    public ProjectItemInput(String projectName, String pm) {
        this.projectName = projectName;
        this.pm = pm;
    }

    public ProjectItemInput(String projectName, String plannedStart, String plannedEnd, String pm) {
        this.projectName = projectName;
        this.plannedStart = plannedStart;
        this.plannedEnd = plannedEnd;
        this.pm = pm;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPlannedStart() {
        return plannedStart;
    }

    public void setPlannedStart(String plannedStart) {
        this.plannedStart = plannedStart;
    }

    public String getPlannedEnd() {
        return plannedEnd;
    }

    public void setPlannedEnd(String plannedEnd) {
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

    @Override
    public String toString() {
        return "ProjectItemInput [projectName=" + projectName + ", plannedStart=" + plannedStart + ", plannedEnd="
                + plannedEnd + ", pm=" + pm + "]";
    }
}