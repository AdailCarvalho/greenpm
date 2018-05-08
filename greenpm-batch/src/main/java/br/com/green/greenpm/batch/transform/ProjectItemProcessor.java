package br.com.green.greenpm.batch.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.item.ProjectItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
public class ProjectItemProcessor implements ItemProcessor<ProjectItemInput, ProjectItemInput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectItemProcessor.class);
    
    @Override
    public ProjectItemInput process(ProjectItemInput input) throws Exception {
        ProjectItemInput output = new ProjectItemInput();
        output.setProjectCod(input.getProjectCod());
        output.setProjectName(input.getProjectName());
        output.setDatInitPlan(input.getDatInitPlan());
        output.setDatEndPlan(input.getDatEndPlan());
        output.setUserId(input.getUserId());
        output.setManagerId(input.getManagerId());

        LOGGER.info("Converting {} into {}", input, output);
        return output;
    }
}
