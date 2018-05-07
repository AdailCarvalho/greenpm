package br.com.green.greenpm.utils;

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
    
    private static DateTimeFormatter dateFormatFrom;
    
    private static DateTimeFormatter dateFormatTo;

    private DateUtils() {
    }
    
    public static Date formatDate(String date, String dtPatternFrom, String dtPatternTo) {
        if (!StringUtils.isNotEmpty(dtPatternFrom)) {
            dtPatternFrom = Constants.DAT_DEF_FMT;
        } else if (!StringUtils.isNotEmpty(dtPatternTo)) {
            dtPatternTo = Constants.DAT_DEF_FMT;
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