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

import br.com.green.greenpm.batch.item.EmployeeItemInput;
import br.com.green.greenpm.batch.item.ManagerItemInput;
import br.com.green.greenpm.batch.item.ManagerItemOutput;
import br.com.green.greenpm.batch.item.ProjectItemInput;
import br.com.green.greenpm.batch.item.ProjectRawItemInput;
import br.com.green.greenpm.batch.item.ProjectRawItemOutput;
import br.com.green.greenpm.batch.item.UserItemInput;
import br.com.green.greenpm.batch.mapper.EmployeeItemMapper;
import br.com.green.greenpm.batch.mapper.ManagerItemMapper;
import br.com.green.greenpm.batch.mapper.ProjectItemMapper;
import br.com.green.greenpm.batch.mapper.UserItemMapper;
import br.com.green.greenpm.batch.notification.JobCompletionNotificationListener;
import br.com.green.greenpm.batch.query.Query;
import br.com.green.greenpm.batch.transform.EmployeeItemProcessor;
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
    public FlatFileItemReader<ProjectRawItemInput> projectRawItemReader() {
        LOGGER.info("Creating Flat File Item Reader...");
        return new FlatFileItemReaderBuilder<ProjectRawItemInput>()
                .name(ProjectRawItemInput.class.getName())
                .resource(new FileSystemResource(FileSystemConfiguration.getBatchFilePath()))
                .delimited()
                .names(FileSystemConfiguration.getFileHeader())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ProjectRawItemInput>() {{
                    setTargetType(ProjectRawItemInput.class);
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
    public JdbcCursorItemReader<ProjectItemInput> projectItemReader() {
        LOGGER.info("Creating Project JDBC Item Reader...");
        
        JdbcCursorItemReader<ProjectItemInput> jdbcReader = new JdbcCursorItemReader<>();
        jdbcReader.setDataSource(this.dataSource);
        jdbcReader.setSql(Query.SELECT_PROJECT);
        jdbcReader.setRowMapper(new ProjectItemMapper());
        
        return jdbcReader;
    }
    
    @Bean
    public JdbcCursorItemReader<EmployeeItemInput> employeeItemReader() {
        LOGGER.info("Creating Employee JDBC ");
        
        JdbcCursorItemReader<EmployeeItemInput> jdbcReader = new JdbcCursorItemReader<>();
        jdbcReader.setDataSource(this.dataSource);
        jdbcReader.setSql(Query.SELECT_EMPLOYEE);
        jdbcReader.setRowMapper(new EmployeeItemMapper());
        
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
    public EmployeeItemProcessor employeeItemProcessor() {
        return new EmployeeItemProcessor();
    }
    
    @Bean
    public JdbcBatchItemWriter<ProjectRawItemOutput> rawItemWriter(DataSource datasource) throws Exception {
        return new JdbcBatchItemWriterBuilder<ProjectRawItemOutput>()
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
    public JdbcBatchItemWriter<ProjectItemInput> projectItemWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<ProjectItemInput>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(Query.INSERT_PROJECT)
                .dataSource(datasource)
                .build();
    }
    
    @Bean
    public JdbcBatchItemWriter<EmployeeItemInput> employeeItemWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<EmployeeItemInput>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(Query.INSERT_EMPLOYEE)
                .dataSource(datasource)
                .build();
    }
    
    @Bean
    public Job importProjectJob(JobCompletionNotificationListener listener, 
            Step processRawDataStp1, Step processManagerDataStp2, Step processUserDataStp3,
            Step processProjectDataStp4, Step processEmployeeDataStp5) {
        return builderFactory.get("importProjectJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(processRawDataStp1)
            .next(processManagerDataStp2)
            .next(processUserDataStp3)
            .next(processProjectDataStp4)
            .next(processEmployeeDataStp5)
            .build();
    }

    @Bean
    public Step processRawDataStp1(JdbcBatchItemWriter<ProjectRawItemOutput> writer) {
        return stepBuilderFactory.get("processRawDataStp1")
            .<ProjectRawItemInput,ProjectRawItemOutput> chunk(100)
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
    
    @Bean
    public Step processProjectDataStp4(JdbcBatchItemWriter<ProjectItemInput> writer) {
        return stepBuilderFactory.get("processProjectDataStp4")
                .<ProjectItemInput, ProjectItemInput> chunk(100)
                .reader(projectItemReader())
                .processor(projectItemProcessor())
                .writer(writer)
                .build();
    }
    
    
    @Bean
    public Step processEmployeeDataStp5(JdbcBatchItemWriter<EmployeeItemInput> writer) {
        return stepBuilderFactory.get("processEmployeeDataStp5")
                .<EmployeeItemInput, EmployeeItemInput> chunk(100)
                .reader(employeeItemReader())
                .processor(employeeItemProcessor())
                .writer(writer)
                .build();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}