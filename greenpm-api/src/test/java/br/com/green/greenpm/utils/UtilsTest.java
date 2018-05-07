package br.com.green.greenpm.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

    String dtPattern;
    
    @Before
    public void init() {
        dtPattern = "MM/dd/yyyy";
    }
    
    @Test
    public void testStrToDateFormat() {
        String datInit = "04/25/2018";
        String ptTo = "yyyy-MM-dd";
        
        Date fmtDate = DateUtils.formatDate(datInit, dtPattern, ptTo);
        assertEquals("2018-04-25", fmtDate.toString());
    }
    
    @Test
    public void testIfNullApplyDefDtFormat() {
        Date fmtDate1 = DateUtils.formatDate("03/25/2018", dtPattern, null);
        Date fmtDate2 = DateUtils.formatDate("07/04/1989", null, null);
        
        assertNotNull(fmtDate1);
        assertNotNull(fmtDate2);
    }
}
