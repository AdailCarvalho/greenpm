package br.com.green.greenpm.formatter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.green.greenpm.dto.EmployeeDTO;
import br.com.green.greenpm.dto.ManagerDTO;
import br.com.green.greenpm.dto.ProjectDTO;
import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.service.ProjectService;
import br.com.green.greenpm.vo.EmployeeVO;
import br.com.green.greenpm.vo.ProjectVO;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-26
 *
 */
public class DataFormatterTest {

    @Autowired
    ProjectService projectService;
    
    ProjectVO projectVO;
    
    List<EmployeeVO> employeeList;
    
    @Before
    public void init( ) {
        projectVO = new ProjectVO();
        projectVO.setUsername("admin");
        projectVO.setPassword("admin123");
        projectVO.setPlanDatInit("05/02/2018");
        projectVO.setPlanDatEnd("05/16/2018");
        projectVO.setProjectName("Test gm");
        projectVO.setPmEmail("adail@gmail.com");
        projectVO.setPmSkill("Data Mining, ETL, these stuff");
        
        employeeList = new ArrayList<>();
        employeeList.add(new EmployeeVO("Baraka", "NetherRealm", "Cut Head off"));
        
        projectVO.setEmployee(employeeList);
    }
    
    @Test
    public void testProjectCodeGeneration() {
        String codProjectA = DataFormatter.generateCodeUsingRawText("Routing Reprocessing");
        String codProjectB = DataFormatter.generateCodeUsingRawText("Reprocessing");
        String codProjectC = DataFormatter.generateCodeUsingRawText(null);
        
        assertEquals("ROR-181518", codProjectA);
        assertEquals("RER-18518", codProjectB);
        assertNotNull(codProjectC);
    }
    
    @Test
    public void testDTOBuildExcEmpDTO() {
        ManagerDTO manager = (ManagerDTO) DataFormatter.buildEntityFromProject(projectVO, ManagerDTO.class);
        ProjectDTO project = (ProjectDTO) DataFormatter.buildEntityFromProject(projectVO, ProjectDTO.class);
        UserDTO user = (UserDTO) DataFormatter.buildEntityFromProject(projectVO, UserDTO.class);
        
        assertEquals("adail@gmail.com", manager.getEmail());
        assertEquals("adail", manager.getCodManager());
        assertEquals("TEG-2057", project.getProjectCod());
        assertEquals("Test gm", project.getProjectName());
        assertEquals("admin", user.getUsername());
        assertEquals("admin123", user.getPassword());
    }
    
    @Test
    public void testEmployeeDTOBuild() {
        EmployeeDTO employee = DataFormatter.buildEntityFromEmployee(projectVO.getEmployee().get(0));
        assertEquals("Baraka", employee.getName());
        assertEquals("NetherRealm", employee.getTeam());
    }
}