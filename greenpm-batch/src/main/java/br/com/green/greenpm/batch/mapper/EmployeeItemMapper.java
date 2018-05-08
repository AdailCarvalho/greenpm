package br.com.green.greenpm.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.green.greenpm.batch.item.EmployeeItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-07
 *
 */
public class EmployeeItemMapper implements RowMapper<EmployeeItemInput> {
    
    private static final String COL_IDPROJECT = "id_project";
    private static final String COL_EMPLOYEECOD = "employee_cod";
    private static final String COL_EMPLOYEEMAIL = "employee_email";
    private static final String COL_EMPLOYEENAME = "employee_name";
    private static final String COL_EMPLOYEESKILL = "employee_skill";
    private static final String COL_EMPLOYEETEAM = "employee_team";
    
    @Override
    public EmployeeItemInput mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeItemInput input = new EmployeeItemInput();

        input.setIdProject(rs.getLong(COL_IDPROJECT));
        input.setEmployeeCod(rs.getString(COL_EMPLOYEECOD));
        input.setEmployeeName(rs.getString(COL_EMPLOYEENAME));
        input.setEmployeeEmail(rs.getString(COL_EMPLOYEEMAIL));
        input.setEmployeeSkills(rs.getString(COL_EMPLOYEESKILL));
        input.setEmployeeTeam(rs.getString(COL_EMPLOYEETEAM));
        return input;
    }
}
