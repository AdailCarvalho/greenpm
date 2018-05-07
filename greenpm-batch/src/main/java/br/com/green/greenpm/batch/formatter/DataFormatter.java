package br.com.green.greenpm.batch.formatter;

import org.apache.commons.lang.StringUtils;

public class DataFormatter {
    
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private DataFormatter() {
        
    }

    public static String generateCodeUsingRawText(String text) {
        if (StringUtils.isNotBlank(text)) {
            String[] tokens = text.toUpperCase().split("\\s");
            String safeToken = (tokens.length == 1 ? String.valueOf(tokens[0].charAt(0)) : tokens[1]); 
            String subst = tokens[0].substring(0, 2)
                                    .concat(safeToken
                                    .substring(0, 1));
            
            StringBuilder bld = new StringBuilder();
            for (int i = 0; i < subst.length(); i++){
                bld.append(ALPHABET.indexOf(subst.charAt(i)) + 1);
            }
            
            return subst
                    .concat("-")
                    .concat(bld.toString());
        }
        return "N/I";
    }
    
    public static String generateCodeUsingEmail(String text) {
        if (StringUtils.isNotBlank(text)) {
            return text.split("@")[0];
            
        }
        return "N/I";
    }
}