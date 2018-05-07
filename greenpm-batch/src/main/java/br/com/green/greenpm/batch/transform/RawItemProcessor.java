package br.com.green.greenpm.batch.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.formatter.DataFormatter;
import br.com.green.greenpm.batch.item.ProjectItemInput;
import br.com.green.greenpm.batch.item.RawItemOutput;
import br.com.green.greenpm.batch.utils.DateUtils;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class RawItemProcessor implements ItemProcessor<ProjectItemInput, RawItemOutput>{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RawItemProcessor.class);
    
    private Integer nrow = 0;

    @Override
    public RawItemOutput process(ProjectItemInput input) throws Exception {
        RawItemOutput rawLine = new RawItemOutput();
        
        rawLine.setCodProject(DataFormatter.generateCodeUsingRawText(input.getProjectName()));
        rawLine.setCodEmployee(DataFormatter.generateCodeUsingEmail(input.getEmployeeEmail()));
        rawLine.setCodManager(DataFormatter.generateCodeUsingEmail(input.getPmEmail()));
        rawLine.setProjectName(input.getProjectName());
        rawLine.setPlannedStart(DateUtils.formatDate(input.getPlannedStart()));
        rawLine.setPlannedEnd(DateUtils.formatDate(input.getPlannedEnd()));
        rawLine.setPm(input.getPm());
        rawLine.setPmEmail(input.getPmEmail());
        rawLine.setPmSkills(input.getPmSkills());
        rawLine.setEmployeeName(input.getEmployeeName());
        rawLine.setEmployeeSkills(input.getEmployeeSkills());
        rawLine.setEmployeeTeam(input.getEmployeeTeam());
        rawLine.setEmployeeEmail(input.getEmployeeEmail());
        
        if (nrow == 10000) {
            LOGGER.info("Num Read Lines => {} ", nrow);
        }

        nrow ++;
        
        return rawLine;
    }
}
