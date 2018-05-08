package br.com.green.greenpm.batch.query;

public class Query {

    //Insert
    public static final String INSERT_PROJECT = 
            "INSERT INTO pm.PROJECT (id_project,fk_id_manager, fk_id_user, cod_project, dsc_project, dat_init_plan, dat_end_plan, dsc_persisted_by, flg_closed_project)"
                    + " VALUES ((SELECT NEXTVAL('project_seq')),:managerId, :userId, :projectCod, :projectName, :datInitPlan, :datEndPlan, :persistedBy, :flgClosedProject)";
    
    public static final String INSERT_USER = 
            "INSERT INTO auth.SYS_USER (id_user,cod_username, cod_password, dsc_user, flg_is_admin, dsc_persisted_by)"
            + "VALUES ((SELECT NEXTVAL('user_seq')),:username, :password, :dscUsername, :flgIsAdmin, :persistedBy)";
    
    public static final String INSERT_MANAGER = 
            "INSERT INTO staff.MANAGER (id_manager,cod_manager, dsc_name, dsc_email, dsc_skill, dsc_persisted_by)"
            + "VALUES ((SELECT NEXTVAL('manager_seq')),:pmCod ,:pmName ,:pmEmail ,:pmSkills, :persistedBy)";
    
    public static final String INSERT_EMPLOYEE = 
            "INSERT INTO staff.EMPLOYEE (id_employee,cod_employee, dsc_team, dsc_name, dsc_email, dsc_skill, fk_id_project, dsc_persisted_by)"
            + "VALUES ((SELECT NEXTVAL('employee_seq')),:employeeCod, :employeeTeam, :employeeName, :employeeEmail, :employeeSkills, :idProject, :persistedBy)";
    
    public static final String INSERT_RAW = 
            "INSERT INTO batch.STAGE_PROJECT (project_cod, project_name, plan_init, plan_end, manager_cod, manager_name, manager_email,"
            + "manager_skill, employee_cod, employee_name, employee_email, employee_team, employee_skill) "
            + "VALUES (:codProject, :projectName, :plannedStart, :plannedEnd, :codManager, :pm, :pmEmail, :pmSkills,"
            + ":codEmployee, :employeeName, :employeeEmail, :employeeTeam, :employeeSkills)";
    
    //Select
    public static final String SELECT_MANAGER = "SELECT DISTINCT manager_cod, manager_name, manager_email, manager_skill FROM batch.STAGE_PROJECT"
            + " WHERE manager_cod NOT IN (SELECT DISTINCT cod_manager FROM staff.MANAGER )";
    
    public static final String SELECT_USER = "SELECT DISTINCT employee_cod AS cod_username, employee_name AS dsc_username FROM batch.STAGE_PROJECT "
            + "WHERE employee_cod NOT IN (SELECT DISTINCT cod_username FROM auth.SYS_USER)";
    
    public static final String SELECT_EMPLOYEE = "SELECT PROJ.id_project, BT.employee_cod, BT.employee_email,  BT.employee_name, "
            + "BT.employee_skill,  BT.employee_team FROM batch.stage_project BT INNER JOIN pm.project PROJ "
            + "ON PROJ.cod_project = BT.project_cod WHERE BT.employee_cod NOT IN  "
            + "(SELECT DISTINCT EMP.cod_employee FROM staff.employee EMP)  "
            + "and PROJ.id_project NOT IN(SELECT DISTINCT EMP.fk_id_project FROM staff.employee EMP)";
    
    public static final String SELECT_PROJECT = "SELECT DISTINCT 2 AS FK_ID_USER, MAN.id_manager as FK_ID_MANAGER, PROJ.project_cod, "
            + "PROJ.project_name, PROJ.plan_init, PROJ.plan_end FROM batch.stage_project "
            + "PROJ INNER JOIN staff.manager MAN ON PROJ.manager_cod = MAN.cod_manager "
            + "WHERE PROJ.project_cod NOT IN (SELECT DISTINCT cod_project FROM pm.project)";
            
    //Delete
    public static final String TRUNCATE_RAW = "TRUNCATE TABLE batch.STAGE_PROJECT";
}