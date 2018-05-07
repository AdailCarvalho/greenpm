package br.com.green.greenpm.batch.transform;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.analyzer.BasicAnalyzer;
import br.com.green.greenpm.batch.formatter.DataFormatter;
import br.com.green.greenpm.batch.item.ProjectItemInput;
import br.com.green.greenpm.batch.item.ProjectItemOutput;
import br.com.green.greenpm.batch.utils.DateUtils;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public class ProjectItemProcessor implements ItemProcessor<ProjectItemInput, ProjectItemOutput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectItemProcessor.class);
    
    private List<String> createdItemList = new ArrayList<>();
    
    @Override
    public ProjectItemOutput process(ProjectItemInput input) throws Exception {
        boolean isValidEmployeeEmail = BasicAnalyzer.validateEmail(input.getEmployeeEmail());
        boolean isValidPeriod = BasicAnalyzer.validateDatePeriod(
                input.getPlannedStart(), 
                input.getPlannedEnd());
        if (!isValidEmployeeEmail) {
            LOGGER.warn("Email validation failure. Ignoring =>" + input.toString());
            return new ProjectItemOutput();
        }
        
        if (!isValidPeriod) {
            input.setPlannedStart(DateUtils.DAT_VAL_DEF);
        }
        
        String codProject = DataFormatter.generateCodeUsingRawText(input.getProjectName());
        if (createdItemList.contains(codProject)) {
            LOGGER.warn("Project already created. Project code => {}", codProject);
            return new ProjectItemOutput();
        }
        
        ProjectItemOutput output = new ProjectItemOutput();
        output.setProjectCod(codProject);
        output.setProjectName(input.getProjectName());
        output.setDatInitPlan(DateUtils.formatDate(input.getPlannedStart()));
        output.setDatEndPlan(DateUtils.formatDate(input.getPlannedEnd()));

        LOGGER.info("Converting {} into {}", input, output);
        
        createdItemList.add(codProject);
        
        return output;
    }

    public List<String> getCreatedItemList() {
        return createdItemList;
    }
}
