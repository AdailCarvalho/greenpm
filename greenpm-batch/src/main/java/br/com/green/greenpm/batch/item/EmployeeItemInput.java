package br.com.green.greenpm.batch.item;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class EmployeeItemInput {

    private Long id;
    
    private Long idProject;
    
    private String employeeCod;
    
    private String employeeName;
    
    private String employeeTeam;
    
    private String employeeSkills;
    
    private String employeeEmail;
    
    private String persistedBy;
    
    public EmployeeItemInput() {
        persistedBy = "BATCH";
    }
    
    public EmployeeItemInput(Long id) {
        this.id = id;
    }

    public EmployeeItemInput(String employeeCod, String employeeName, String employeeTeam, String employeeSkills,
            String employeeEmail) {
        this.employeeCod = employeeCod;
        this.employeeName = employeeName;
        this.employeeTeam = employeeTeam;
        this.employeeSkills = employeeSkills;
        this.employeeEmail = employeeEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getEmployeeCod() {
        return employeeCod;
    }

    public void setEmployeeCod(String employeeCod) {
        this.employeeCod = employeeCod;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    
    public String getPersistedBy() {
        return persistedBy;
    }

    public void setPersistedBy(String persistedBy) {
        this.persistedBy = persistedBy;
    }
}