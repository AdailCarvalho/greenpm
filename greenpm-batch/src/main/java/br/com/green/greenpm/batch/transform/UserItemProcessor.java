package br.com.green.greenpm.batch.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.item.UserItemInput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class UserItemProcessor implements ItemProcessor<UserItemInput, UserItemInput> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public UserItemInput process(UserItemInput input) throws Exception {
        UserItemInput output = new UserItemInput();
        output.setUsername(input.getUsername());
        output.setPassword(input.getPassword());
        output.setDscUsername(input.getUsername());
        
        LOGGER.info("Converting {} into {}", input, output);
        
        return output;
    }
}