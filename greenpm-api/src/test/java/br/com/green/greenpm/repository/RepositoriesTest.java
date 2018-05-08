package br.com.green.greenpm.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.green.greenpm.App;
import br.com.green.greenpm.dto.ManagerDTO;
import br.com.green.greenpm.dto.ProjectDTO;
import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.formatter.DataFormatter;
import br.com.green.greenpm.utils.Constants;
import br.com.green.greenpm.vo.EmployeeVO;
import br.com.green.greenpm.vo.ProjectVO;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-05-02
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@PropertySource("application-test.properties")
@Transactional
public class RepositoriesTest {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ProjectRepository projectRepository;
    
    UserDTO user;
    
    ManagerDTO manager = new ManagerDTO();
    
    ProjectVO projectVO;
    
    List<EmployeeVO> employees;
    
    @Before
    public void init() {
        projectVO = new ProjectVO("Route Reprocessing", "05/21/2018", "10/16/2018");
        
        user = new UserDTO();
        user.setUsername("dummy");
        user.setPassword("dummy#2018");
        user.setDscUsername("dummy");
        user.setFlgIsAdmin("N");
        
        manager.setName("Juan Carlos");
        manager.setEmail("juan.carlos@gmail.com");
        manager.setSkill("SCRUM, ITIL,XP");
        
        employees = new ArrayList<>();
        
        EmployeeVO emp1 = new EmployeeVO("Bojack Horseman", "bojack@gmail.com", 
                "ETL,Stream Processing,Microsservices");;
        EmployeeVO emp2 = new EmployeeVO("Mr. Peanut Butter","butter@gmail.com",
                "Quality Assurance");
        
        employees.add(emp1);
        employees.add(emp2);
        
        projectVO.setPmName(manager.getName());
        projectVO.setPmEmail(manager.getEmail());
        projectVO.setPmSkill(manager.getSkill());
    }
    
    @Test
    public void testCreateAndGetUser() {
        userRepository.save(user);
        
        Optional<UserDTO> dummyUser = userRepository.findById(user.getIdUser());
        assertNotNull(dummyUser.get());
        assertEquals("dummy", dummyUser.get().getUsername());
    }
    
    @Test
    public void testCreateAndGetProject() {
        ProjectDTO projectDto = getProjectDto(projectVO); 
        projectRepository.save(projectDto);
        
        Optional<ProjectDTO> proj = projectRepository.findById(projectDto.getIdProject());
       
        assertNotNull(proj);
        assertEquals("ROR-181518", proj.get().getProjectCod());
        assertTrue(projectDto.getPlanDatInit().getTime() <  projectDto.getPlanDatEnd().getTime());
        assertTrue(proj.get().getPlanDatInit().getTime() < proj.get().getPlanDatEnd().getTime());
    }
    
    public ProjectDTO getProjectDto(ProjectVO vo) {
        return (ProjectDTO) DataFormatter.buildEntityFromProject(vo, ProjectDTO.class);
    }
}