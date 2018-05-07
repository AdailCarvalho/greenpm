package br.com.green.greenpm.batch.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.green.greenpm.batch.analyzer.BasicAnalyzer;
import br.com.green.greenpm.batch.item.ManagerItemInput;
import br.com.green.greenpm.batch.item.ManagerItemOutput;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-05
 *
 */
public class ManagerItemProcessor implements ItemProcessor<ManagerItemInput, ManagerItemOutput> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerItemProcessor.class);
    
    @Override
    public ManagerItemOutput process(ManagerItemInput input) throws Exception {
        ManagerItemOutput output = new ManagerItemOutput();
        boolean isValidManagerEmail = BasicAnalyzer.validateEmail(input.getPmEmail());
        if (!isValidManagerEmail) {
            LOGGER.warn("Email validation failure. Setting email as N/I for => {}" + input.toString());
            output.setPmEmail(input.getPmEmail());
        } else {
            output.setPmEmail(input.getPmEmail());
        }
        
        output.setPmCod(input.getPmCod());
        output.setPmName(input.getPm());
        output.setPmSkills(input.getPmSkills());
        
        LOGGER.info("Converting {} into {}", input, output);

        return output;
    }
}
