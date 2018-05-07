package br.com.green.greenpm.batch.analyzer;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.green.greenpm.batch.utils.DateUtils;
import br.com.green.greenpm.batch.utils.RegexUtils;


public class BasicAnalyzer {
    
    private BasicAnalyzer() {
        
    }
    
    public static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile(RegexUtils.EMAIL.getRegex());
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean validateDatePeriod(String dateInit, String dateEnd) {
        Date datInit = DateUtils.formatDate(dateInit, "MM/dd/yyyy", "yyyy-MM-dd");
        Date datEnd = DateUtils.formatDate(dateEnd, "MM/dd/yyyy", "yyyy-MM-dd");
        return datInit.getTime() < datEnd.getTime();
     }
}