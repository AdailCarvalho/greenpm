package br.com.green.greenpm.batch.configuration;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemConfiguration.class);
    
    private static final String SO_SLASH = StringUtils.defaultIfEmpty(
            java.nio.file.FileSystems.getDefault().getSeparator(), 
            "//"); 
    
    private static final String SO = System.getProperty("os.name");
    
    private static final String GREENPM_BATCH_FILEPATH = "_opt_greenbatch_"; 
    
    private static String[] fileHeader = new String[] { "# ProjectName", "PlannedStart", "PlannedEnd", "PM", "PMEmail",
            "PMSkills", "EmployeeName", "EmployeeEmail", "EmployeeTeam", "EmployeeSkills" };

    private static String batchFilePath;
    
    public FileSystemConfiguration() {
    
    }

    public static String getBatchFilePath() {
        LOGGER.info("Configuring system path ...");
        if (StringUtils.isNotBlank(batchFilePath)) {
            return batchFilePath;
        }
        
        if (SO.startsWith("Windows")) {
           String escapeSlash = "\\\\";
           batchFilePath = "C:".concat(GREENPM_BATCH_FILEPATH).replaceAll("_", escapeSlash);
           LOGGER.info("SO => {}", SO);
        } else {
            batchFilePath = GREENPM_BATCH_FILEPATH.replace("_", SO_SLASH);
            LOGGER.info("SO => {}", SO);
        }
        
        File batchFile = new File(batchFilePath + "gm-challenge.csv");
        if (!batchFile.exists()) {
            try {
                FileUtils.forceMkdir(batchFile);
            } catch (IOException io1) {
                //Log
            }
        } 
        
        LOGGER.info("Base file path = {}", batchFile);
       
       return batchFile.getAbsolutePath();
    }
    
    public static String[] getFileHeader( ) {
        return fileHeader;
    }
}