package br.com.green.greenpm.batch.mapper;

import br.com.green.greenpm.batch.item.ProjectItemInput;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-07
 *
 */
public class ProjectItemMapper implements RowMapper<ProjectItemInput> {

    private static final String COL_FKMANAGER = "fk_id_manager";
    private static final String COL_FKUSER = "fk_id_user";
    private static final String COL_CODPROJECT = "project_cod";
    private static final String COL_NAMEPROJECT = "project_name";
    private static final String COL_DATINIT = "plan_init";
    private static final String COL_DATEND = "plan_end";
    
    @Override
    public ProjectItemInput mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectItemInput input = new ProjectItemInput();
        
        input.setProjectCod(rs.getString(COL_CODPROJECT));
        input.setProjectName(rs.getString(COL_NAMEPROJECT));
        input.setDatInitPlan(rs.getDate(COL_DATINIT));
        input.setDatEndPlan(rs.getDate(COL_DATEND));
        input.setUserId(rs.getLong(COL_FKUSER));
        input.setManagerId(rs.getLong(COL_FKMANAGER));
        
        return input;
    }

}
