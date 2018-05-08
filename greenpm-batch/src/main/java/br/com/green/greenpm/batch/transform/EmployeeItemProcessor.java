package br.com.green.greenpm.batch.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.item.EmployeeItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class EmployeeItemProcessor implements ItemProcessor<EmployeeItemInput, EmployeeItemInput>{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemProcessor.class);
    
    @Override
    public EmployeeItemInput process(EmployeeItemInput input) throws Exception {

        EmployeeItemInput output = new EmployeeItemInput();
        output.setEmployeeCod(input.getEmployeeCod());
        output.setEmployeeEmail(input.getEmployeeEmail());
        output.setEmployeeName(input.getEmployeeName());
        output.setEmployeeSkills(input.getEmployeeSkills());
        output.setEmployeeTeam(input.getEmployeeTeam());
        output.setIdProject(input.getIdProject());
          
        LOGGER.info("Converting {} into {}", input, output);

        return output;
    }
}
