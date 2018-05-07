package br.com.green.greenpm.batch.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.green.greenpm.batch.configuration.FileSystemConfiguration;
import br.com.green.greenpm.batch.item.ProjectItemInput;
import br.com.green.greenpm.batch.query.Query;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-03
 *
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
        String fileOrigin = FileSystemConfiguration.getBatchFilePath();
        LOGGER.info("File ready: {}", fileOrigin);
        
        LOGGER.info("Truncating table batch.STAGE_PROJECT");
       jdbcTemplate.execute(Query.TRUNCATE_RAW);
    }
    
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT DISTINCT project_name, plan_init, plan_end, manager_name FROM batch.STAGE_PROJECT",
                (rs, row) -> new ProjectItemInput(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4))
                ).forEach(input -> LOGGER.info("Found <" + input + "> in the database."));
        }
    }
}