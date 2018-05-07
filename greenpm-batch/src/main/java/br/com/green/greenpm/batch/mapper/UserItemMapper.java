package br.com.green.greenpm.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.green.greenpm.batch.item.UserItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-07
 *
 */
public class UserItemMapper implements RowMapper<UserItemInput> {
    
    private static final String COL_USERNAME = "cod_username";
    private static final String COL_DSCUSERNAME = "dsc_username";

    @Override
    public UserItemInput mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserItemInput input = new UserItemInput();
        input.setUsername(rs.getString(COL_USERNAME));
        input.setDscUsername(rs.getString(COL_DSCUSERNAME));
        input.setPassword(rs.getString(COL_USERNAME));
        
        return input;
    }
}
