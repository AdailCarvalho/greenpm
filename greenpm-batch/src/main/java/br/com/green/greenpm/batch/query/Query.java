package br.com.green.greenpm.batch.query;

public class Query {

    //Insert
    public static final String INSERT_PROJECT = 
            "INSERT INTO pm.PROJECT (id_project,fk_id_manager, fk_id_user, cod_project, dsc_project, dat_init_plan, dat_end_plan)"
                    + " VALUES ((SELECT NEXTVAL('project_seq')),:managerId, :userId, :projectCod, :projectName, :datInitPlan, :datEndPlan)";
    
    public static final String INSERT_USER = 
            "INSERT INTO auth.SYS_USER (id_user,cod_username, cod_password, dsc_user, flg_is_admin)"
            + "VALUES ((SELECT NEXTVAL('user_seq')),:username, :password, :dscUsername, :flgIsAdmin)";
    
    public static final String INSERT_MANAGER = 
            "INSERT INTO staff.MANAGER (id_manager,cod_manager, dsc_name, dsc_email, dsc_skill)"
            + "VALUES ((SELECT NEXTVAL('manager_seq')),:pmCod ,:pmName ,:pmEmail ,:pmSkills)";
    
    public static final String INSERT_EMPLOYEE = 
            "INSERT INTO staff.EMPLOYEE (id_employee,cod_employee, dsc_team, dsc_name, dsc_email, dsc_skill, fk_id_project)"
            + "VALUES ((SELECT NEXTVAL('employee_seq')),:employeeCod, :employeeTeam, :employeeName, :employeeEmail, :employeeSkills, :projectId)";
    
    public static final String INSERT_RAW = 
            "INSERT INTO batch.STAGE_PROJECT (project_cod, project_name, plan_init, plan_end, manager_cod, manager_name, manager_email,"
            + "manager_skill, employee_cod, employee_name, employee_email, employee_team, employee_skill) "
            + "VALUES (:codProject, :projectName, :plannedStart, :plannedEnd, :codManager, :pm, :pmEmail, :pmSkills,"
            + ":codEmployee, :employeeName, :employeeEmail, :employeeTeam, :employeeSkills)";
    
    //Select
    public static final String SELECT_MANAGER = "SELECT DISTINCT manager_cod, manager_name, manager_email, manager_skill FROM batch.STAGE_PROJECT"
            + " where manager_cod not in (SELECT DISTINCT cod_manager FROM staff.MANAGER )";
            
    
    //Delete
    public static final String TRUNCATE_RAW = "TRUNCATE TABLE batch.STAGE_PROJECT";
}