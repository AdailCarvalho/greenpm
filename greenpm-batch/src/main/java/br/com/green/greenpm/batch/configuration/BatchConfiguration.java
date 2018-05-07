package br.com.green.greenpm.batch.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import br.com.green.greenpm.batch.item.ManagerItemInput;
import br.com.green.greenpm.batch.item.ManagerItemOutput;
import br.com.green.greenpm.batch.item.ProjectItemInput;
import br.com.green.greenpm.batch.item.RawItemOutput;
import br.com.green.greenpm.batch.item.UserItemInput;
import br.com.green.greenpm.batch.mapper.ManagerItemMapper;
import br.com.green.greenpm.batch.mapper.UserItemMapper;
import br.com.green.greenpm.batch.notification.JobCompletionNotificationListener;
import br.com.green.greenpm.batch.query.Query;
import br.com.green.greenpm.batch.transform.ManagerItemProcessor;
import br.com.green.greenpm.batch.transform.ProjectItemProcessor;
import br.com.green.greenpm.batch.transform.RawItemProcessor;
import br.com.green.greenpm.batch.transform.UserItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);
    
    @Autowired
    public JobBuilderFactory builderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }
    
    @Bean
    public FlatFileItemReader<ProjectItemInput> projectRawItemReader() {
        LOGGER.info("Creating Flat File Item Reader...");
        return new FlatFileItemReaderBuilder<ProjectItemInput>()
                .name(ProjectItemInput.class.getName())
                .resource(new FileSystemResource(FileSystemConfiguration.getBatchFilePath()))
                .delimited()
                .names(FileSystemConfiguration.getFileHeader())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ProjectItemInput>() {{
                    setTargetType(ProjectItemInput.class);
                }})
                .build();
    }
    
    @Bean
    public JdbcCursorItemReader<ManagerItemInput> managerItemReader() {
        LOGGER.info("Creating Manager JDBC Item Reader...");
        
        JdbcCursorItemReader<ManagerItemInput> jdbcReader = new JdbcCursorItemReader<>();
        jdbcReader.setDataSource(this.dataSource);
        jdbcReader.setSql(Query.SELECT_MANAGER);
        jdbcReader.setRowMapper(new ManagerItemMapper());
        return jdbcReader;
    }
    
    @Bean
    public JdbcCursorItemReader<UserItemInput> userItemReader() {
        LOGGER.info("Creating User JDBC Item Reader...");
        
        JdbcCursorItemReader<UserItemInput> jdbcReader = new JdbcCursorItemReader<>();
        jdbcReader.setDataSource(this.dataSource);
        jdbcReader.setSql(Query.SELECT_USER);
        jdbcReader.setRowMapper(new UserItemMapper());
        return jdbcReader;
    }
    
    @Bean
    public ProjectItemProcessor projectItemProcessor() {
        return new ProjectItemProcessor();
    }
    
    @Bean
    public ManagerItemProcessor managerItemProcessor() {
        return new ManagerItemProcessor();
    }
    
    @Bean
    public RawItemProcessor rawItemProcessor() {
        return new RawItemProcessor();
    }
    
    @Bean
    public UserItemProcessor userItemProcessor() {
        return new UserItemProcessor();
    }
    
    @Bean
    public JdbcBatchItemWriter<RawItemOutput> rawItemWriter(DataSource datasource) throws Exception {
        return new JdbcBatchItemWriterBuilder<RawItemOutput>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql(Query.INSERT_RAW)
            .dataSource(datasource)
            .build();
    }
    
    @Bean
    public JdbcBatchItemWriter<ManagerItemOutput> managerItemWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<ManagerItemOutput>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(Query.INSERT_MANAGER)
                .dataSource(datasource)
                .build();
    }
    
    @Bean
    public JdbcBatchItemWriter<UserItemInput> userItemWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<UserItemInput>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(Query.INSERT_USER)
                .dataSource(datasource)
                .build();
    }
    
    @Bean
    public Job importProjectJob(JobCompletionNotificationListener listener, 
            Step processRawDataStp1, Step processManagerDataStp2, Step processUserDataStp3) {
        return builderFactory.get("importProjectJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(processRawDataStp1)
            .next(processManagerDataStp2)
            .next(processUserDataStp3)
            .build();
    }

    @Bean
    public Step processRawDataStp1(JdbcBatchItemWriter<RawItemOutput> writer) {
        return stepBuilderFactory.get("processRawDataStp1")
            .<ProjectItemInput,RawItemOutput> chunk(100)
            .reader(projectRawItemReader())
            .processor(rawItemProcessor())
            .writer(writer)
            .build();
    }
    
    @Bean
    public Step processManagerDataStp2(JdbcBatchItemWriter<ManagerItemOutput> writer) {
        return stepBuilderFactory.get("processManagerDataStp2")
                .<ManagerItemInput, ManagerItemOutput> chunk(100)
                .reader(managerItemReader())
                .processor(managerItemProcessor())
                .writer(writer)
                .build();
    }
    
    @Bean
    public Step processUserDataStp3(JdbcBatchItemWriter<UserItemInput> writer) {
        return stepBuilderFactory.get("processUserDataStp3")
                .<UserItemInput, UserItemInput> chunk(100)
                .reader(userItemReader())
                .processor(userItemProcessor())
                .writer(writer)
                .build();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}