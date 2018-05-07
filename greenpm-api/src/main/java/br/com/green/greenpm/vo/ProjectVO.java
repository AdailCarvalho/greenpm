package br.com.green.greenpm.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-25
 *
 */
public class ProjectVO {

    private String projectCod;
    
    private String projectName;
    
    private String planDatInit;
    
    private String planDatEnd;
    
    private String pmName;
    
    private String pmEmail;
    
    private String pmSkill;
    
    private String username;
    
    private String password;
    
    private List<EmployeeVO> employee;
    
    public ProjectVO() {
        employee = new ArrayList<>();
    }

    public ProjectVO(String projectName, String planDatInit, String planDatEnd) {
        this.projectName = projectName;
        this.planDatInit = planDatInit;
        this.planDatEnd = planDatEnd;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPlanDatInit() {
        return planDatInit;
    }

    public void setPlanDatInit(String planDatInit) {
        this.planDatInit = planDatInit;
    }

    public String getPlanDatEnd() {
        return planDatEnd;
    }

    public void setPlanDatEnd(String planDatEnd) {
        this.planDatEnd = planDatEnd;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmEmail() {
        return pmEmail;
    }

    public void setPmEmail(String pmEmail) {
        this.pmEmail = pmEmail;
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

    public String getPmSkill() {
        return pmSkill;
    }

    public void setPmSkill(String pmSkill) {
        this.pmSkill = pmSkill;
    }

    public List<EmployeeVO> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeVO> employee) {
        this.employee = employee;
    }

    public String getProjectCod() {
        return projectCod;
    }

    public void setProjectCod(String projectCod) {
        this.projectCod = projectCod;
    }
}
