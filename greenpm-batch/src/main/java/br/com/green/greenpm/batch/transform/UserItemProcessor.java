package br.com.green.greenpm.batch.transform;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.formatter.DataFormatter;
import br.com.green.greenpm.batch.item.ProjectItemInput;
import br.com.green.greenpm.batch.item.UserItemOutput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class UserItemProcessor implements ItemProcessor<ProjectItemInput, UserItemOutput> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserItemProcessor.class);

    private List<String> createdItemList =  new ArrayList<>();
    
    @Override
    public UserItemOutput process(ProjectItemInput input) throws Exception {
        String username = DataFormatter.generateCodeUsingEmail(input.getEmployeeEmail());
        if (createdItemList.contains(username)) {
            LOGGER.warn("User already registered. Username => {}", username);
            return new UserItemOutput();
        }
        
        UserItemOutput output = new UserItemOutput();
        output.setUsername(username);
        output.setPassword(username);
        output.setDscUsername(input.getEmployeeName());
        
        LOGGER.info("Converting {} into {}", input, output);

        createdItemList.add(username);
        
        return output;
    }

    public List<String> getCreatedItemList() {
        return createdItemList;
    }
}