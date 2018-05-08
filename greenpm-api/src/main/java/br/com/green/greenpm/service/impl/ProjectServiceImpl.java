package br.com.green.greenpm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.green.greenpm.dto.EmployeeDTO;
import br.com.green.greenpm.dto.ManagerDTO;
import br.com.green.greenpm.dto.ProjectDTO;
import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.exception.AuthException;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;
import br.com.green.greenpm.formatter.DataFormatter;
import br.com.green.greenpm.model.ProjectModel;
import br.com.green.greenpm.repository.EmployeeRepository;
import br.com.green.greenpm.repository.ManagerRepository;
import br.com.green.greenpm.repository.ProjectRepository;
import br.com.green.greenpm.repository.UserRepository;
import br.com.green.greenpm.service.ProjectService;
import br.com.green.greenpm.utils.Constants;
import br.com.green.greenpm.vo.EmployeeVO;
import br.com.green.greenpm.vo.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
    
    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private ProjectRepository projectRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ManagerRepository managerRepo;
    
    @Transactional(readOnly = true)
    @Override
    public ProjectModel getProjectByCod(String projectCod) throws EntityNotFoundException {
        Optional<ProjectDTO> project = projectRepo.findProjectByProjectCod(projectCod);
        ProjectModel projectMod = new ProjectModel();
        if (project.isPresent()) {
            ProjectDTO proj = project.get();

            projectMod.setProjectCod(proj.getProjectCod());
            projectMod.setProjectName(proj.getProjectName());
            projectMod.setPmName(proj.getManager().getName());
            projectMod.setPlanDatInit(proj.getPlanDatInit());
            projectMod.setPlanDatEnd(proj.getPlanDatEnd());
            projectMod.setUser(proj.getUser().getUsername());
            projectMod.setEmployee(DataFormatter.buildEmployeeModelFromDTOList(proj.getEmployee()));
            
        } else {
            LOGGER.error("Project with the corresponding cod was not found => " + projectCod);
            throw new EntityNotFoundException(ProjectDTO.class, "projectCod", projectCod);
        }
        
        return projectMod;
    }

    @Transactional
    @Override
    public String saveProject(ProjectVO project) throws EntityExistsException, AuthException {
        Optional<UserDTO> optUser = userRepo.findSysUserByUsername(project.getUsername());
        if (!optUser.isPresent()) {
            LOGGER.error("Failed to authenticate user => {}", project.getUsername());
            throw new AuthException(UserDTO.class, "username", project.getUsername());
        }  
        
        UserDTO user = optUser.get();
        if (StringUtils.isBlank(project.getPassword()) 
                || !user.getPassword().equals(project.getPassword())) {
            LOGGER.error("Invalid password for user => {}", project.getUsername());
            LOGGER.error("Passed password: {}, DB password : {}", project.getPassword(), user.getPassword());
            throw new AuthException(UserDTO.class, "username", project.getUsername());
        } 
        
        String projectCod = DataFormatter.generateCodeUsingRawText(project.getProjectName());
        Optional<ProjectDTO> proj = projectRepo.findProjectByProjectCod(projectCod);
      
        if (proj.isPresent()) {
            LOGGER.error("Project already exists. Checkout using the following code => " + projectCod);
            throw new EntityExistsException(ProjectDTO.class, "projectCod", projectCod);
        }

        ProjectDTO projectDto = (ProjectDTO) DataFormatter
                .buildEntityFromProject(project, ProjectDTO.class);
        projectDto.setUser(user);

        ManagerDTO manager = (ManagerDTO) DataFormatter
                .buildEntityFromProject(project, ManagerDTO.class);

        Optional<ManagerDTO> man = managerRepo.findManagerByEmail(project.getPmEmail());
        if (!man.isPresent()) {
            managerRepo.save(manager);
            projectDto.setManager(manager);
        } else {
            projectDto.setManager(man.get());
        }

        List<EmployeeDTO> employees = new ArrayList<>();
        for (EmployeeVO emp : project.getEmployee()) {
            Optional<EmployeeDTO> empl = employeeRepo.findEmployeeByEmail(emp.getEmail());
            if (!empl.isPresent()) {
                EmployeeDTO empDto = DataFormatter.buildEntityFromEmployee(emp);
                empDto.setProject(projectDto);
                employees.add(empDto);
            }
        }

        projectDto.setEmployee(employees);
        projectRepo.save(projectDto);

        return projectCod;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectModel> listAllProjects() {
        Page<ProjectDTO> projects = projectRepo.findAll(new PageRequest(0, Constants.MAX_LIMIT));
        
        return 
                DataFormatter.buildProjectModelFromDTOList(projects.getContent());
    }

    @Transactional
    @Override
    public String closeProject(String projectCod) throws EntityNotFoundException {
        Optional<ProjectDTO> project = projectRepo.findProjectByProjectCod(projectCod);
        if (project.isPresent()) {
            LOGGER.info("Updating project = >", project.get().getProjectCod());
            projectRepo.updateProjectStatus(projectCod);
        } else {
            LOGGER.error("Project with the corresponding cod was not found => " + projectCod);
            throw new EntityNotFoundException(ProjectDTO.class, "projectCod", projectCod);
        }
        
        return projectCod;
    }
}