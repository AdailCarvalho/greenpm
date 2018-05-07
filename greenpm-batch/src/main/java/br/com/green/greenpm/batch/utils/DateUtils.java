package br.com.green.greenpm.batch.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-25
 *
 */
public class DateUtils {
    
    public static String DAT_PAT_EN = "MM/dd/yyyy";
    
    public static String DAT_PAT_DEF = "yyyy-MM-dd";
    
    public static String DAT_VAL_DEF = "01/01/2001";
    
    private static DateTimeFormatter dateFormatFrom;
    
    private static DateTimeFormatter dateFormatTo;

    private DateUtils() {
    }
    
    public static Date formatDate(String date) {
        return formatDate(date, DAT_PAT_EN, DAT_PAT_DEF);
    }
    
    public static Date formatDate(String date, String dtPatternFrom, String dtPatternTo) {
        if (!StringUtils.isNotEmpty(dtPatternFrom)) {
            dtPatternFrom = DAT_PAT_EN;
        } else if (!StringUtils.isNotEmpty(dtPatternTo)) {
            dtPatternTo = DAT_PAT_EN;
        }
        
        if (dateFormatTo == null) {
            dateFormatTo = DateTimeFormatter.ofPattern(dtPatternTo);
        }
        
        if (dateFormatFrom == null) {
            dateFormatFrom = DateTimeFormatter.ofPattern(dtPatternFrom);
        }
        
        LocalDate originalDate = LocalDate.parse(date, dateFormatFrom); 
        return Date.valueOf(originalDate.format(dateFormatTo));
    }
}