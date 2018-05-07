package br.com.green.greenpm.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.green.greenpm.vo.EmployeeVO;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-25
 *
 */
public class ProjectModel {
    
    private String projectCod;
    
    private String projectName;
    
    private String pmName;
    
    private Date planDatInit;
    
    private Date planDatEnd;
    
    private String user;
    
    private List<EmployeeVO> employee;
    
    public ProjectModel() {
        employee = new ArrayList<>();
    }
    
    public ProjectModel(String projectCod, String projectName, String pmName, Date planDatInit, Date planDatEnd,
            String user, List<EmployeeVO> employee) {
        super();
        this.projectCod = projectCod;
        this.projectName = projectName;
        this.pmName = pmName;
        this.planDatInit = planDatInit;
        this.planDatEnd = planDatEnd;
        this.user = user;
        this.employee = employee;
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

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
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

    public List<EmployeeVO> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeVO> employee) {
        this.employee = employee;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}