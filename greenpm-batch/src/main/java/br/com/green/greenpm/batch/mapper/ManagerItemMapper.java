package br.com.green.greenpm.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.green.greenpm.batch.item.ManagerItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class ManagerItemMapper implements RowMapper<ManagerItemInput> {
    
    private static final String COL_PMCOD = "manager_cod";
    private static final String COL_PMNAME = "manager_name";
    private static final String COL_PMEMAIL = "manager_email";
    private static final String COL_PMSKILL = "manager_skill";

    @Override
    public ManagerItemInput mapRow(ResultSet rs, int rowNum) throws SQLException {
        ManagerItemInput input = new ManagerItemInput();
        
        input.setPmCod(rs.getString(COL_PMCOD));
        input.setPm(rs.getString(COL_PMNAME));
        input.setPmSkills(rs.getString(COL_PMSKILL));
        input.setPmEmail(rs.getString(COL_PMEMAIL));
        
        return input;
    }
}