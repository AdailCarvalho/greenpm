package br.com.green.greenpm.batch.transform;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.analyzer.BasicAnalyzer;
import br.com.green.greenpm.batch.formatter.DataFormatter;
import br.com.green.greenpm.batch.item.EmployeeItemOutput;
import br.com.green.greenpm.batch.item.ProjectItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class EmployeeItemProcessor implements ItemProcessor<ProjectItemInput, EmployeeItemOutput>{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemProcessor.class);
    
    private List<String> createdItemList = new ArrayList<>();
    
    @Override
    public EmployeeItemOutput process(ProjectItemInput input) throws Exception {
        boolean isValidEmployee = BasicAnalyzer.validateEmail(input.getEmployeeEmail());
        if (!isValidEmployee) {
            LOGGER.warn("Email validation failure. Ignoring => {}" + input.toString());
            return new EmployeeItemOutput();
        }
        
        String codEmployee = DataFormatter.generateCodeUsingEmail(input.getEmployeeEmail());
        if (createdItemList.contains(codEmployee)) {
            LOGGER.warn("Employee already created. Employee code => {}", codEmployee);
            return new EmployeeItemOutput();
        }

        EmployeeItemOutput output = new EmployeeItemOutput();
        output.setEmployeeCod(DataFormatter.generateCodeUsingEmail(input.getEmployeeEmail()));
        output.setEmployeeEmail(input.getEmployeeEmail());
        output.setEmployeeName(input.getEmployeeName());
        output.setEmployeeSkills(input.getEmployeeSkills());
        output.setEmployeeTeam(input.getEmployeeTeam());
          
        LOGGER.info("Converting {} into {}", input, output);

        createdItemList.add(codEmployee);
        
        return output;
    }

    public List<String> getCreatedItemList() {
        return createdItemList;
    }
}
