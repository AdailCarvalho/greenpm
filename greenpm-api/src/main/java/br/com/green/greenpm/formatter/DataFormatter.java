package br.com.green.greenpm.formatter;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import br.com.green.greenpm.dto.EmployeeDTO;
import br.com.green.greenpm.dto.ManagerDTO;
import br.com.green.greenpm.dto.ProjectDTO;
import br.com.green.greenpm.dto.UserDTO;
import br.com.green.greenpm.model.ProjectModel;
import br.com.green.greenpm.model.UserModel;
import br.com.green.greenpm.utils.Constants;
import br.com.green.greenpm.utils.DateUtils;
import br.com.green.greenpm.vo.EmployeeVO;
import br.com.green.greenpm.vo.ProjectVO;

/**
 * 
 * @author Adail Carvalho
 * 
 * @sicne 2018-04-26
 *
 */
public class DataFormatter {
    
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private DataFormatter() {
        
    }
    
    public static List<EmployeeVO> buildEmployeeModelFromDTOList(List<EmployeeDTO> employees) {
        return employees
                .stream()
                .map(empl -> new EmployeeVO(empl.getName(), empl.getTeam(), empl.getSkill()))
                .collect(Collectors.toList());
    }
    
    public static List<ProjectModel> buildProjectModelFromDTOList(List<ProjectDTO> projects) {
        return projects
                .stream()
                .map(proj -> new ProjectModel(proj.getProjectCod(), 
                        proj.getManager().getName(),
                        proj.getManager().getEmail(),
                        proj.getPlanDatInit(),
                        proj.getPlanDatEnd(),
                        proj.getUser().getUsername(),
                        buildEmployeeModelFromDTOList(proj.getEmployee())))
                .collect(Collectors.toList());
    }
    
    public static List<UserModel> buildUserModelFromDTOList(List<UserDTO> users) {
        return users
                 .stream()
                 .map(usr -> new UserModel(usr.getIdUser(), usr.getUsername(), usr.getDscUsername(), usr.getFlgIsAdmin().equals("Y") ? true : false))
                 .collect(Collectors.toList());
    }
    
    public static EmployeeDTO buildEntityFromEmployee(EmployeeVO employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        String employeeCod = DataFormatter.generateCodeUsingEmail(employee.getEmail());
        
        employeeDto.setCodEmployee(employeeCod);
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setName(employee.getName());
        employeeDto.setSkill(employee.getSkill());
        employeeDto.setTeam(employee.getTeam());
        
        return employeeDto;
    }
    
    public static <T> Object buildEntityFromProject(ProjectVO project, Class<T> clazz) {
        Object dto = null;
        if (clazz.getName().equals(ManagerDTO.class.getName())) {
            ManagerDTO managerDto = new ManagerDTO();
            
            String managerCod = DataFormatter.generateCodeUsingEmail(project.getPmEmail());
            managerDto.setCodManager(managerCod);
            managerDto.setEmail(project.getPmEmail());
            managerDto.setName(project.getPmName());
            managerDto.setSkill(project.getPmSkill());
            
            dto = managerDto;
        } else if (clazz.getName().equals(UserDTO.class.getName())) { 
            UserDTO sysUser = new UserDTO();
            
            sysUser.setUsername(project.getUsername());
            sysUser.setDscUsername(project.getUsername());
            sysUser.setPassword(project.getPassword());
            
            dto = sysUser;
        } else {
            ProjectDTO projectDto = new ProjectDTO();
            String projectCod = DataFormatter.generateCodeUsingRawText(project.getProjectName());

            projectDto.setProjectCod(projectCod);
            projectDto.setProjectName(project.getProjectName());
            
            Date datInit = DateUtils.formatDate(project.getPlanDatInit(), 
                    Constants.DAT_EN_FMT , 
                    Constants.DAT_DEF_FMT);
            
            Date datEnd = DateUtils.formatDate(project.getPlanDatEnd(), 
                    Constants.DAT_EN_FMT , 
                    Constants.DAT_DEF_FMT);
            
            Date defaultDat = DateUtils.formatDate(Constants.DAT_DEF, 
                    Constants.DAT_EN_FMT, 
                    Constants.DAT_DEF_FMT);
            
            if (datInit.getTime() > datEnd.getTime()) {
                projectDto.setPlanDatInit(defaultDat);
            } else {
                projectDto.setPlanDatInit(datInit);
            }
            
            projectDto.setPlanDatEnd(datEnd);
            
            dto = projectDto;
        }
        
        return dto;
    }
    
    public static String generateCodeUsingRawText(String text) {
        if (StringUtils.isNotBlank(text)) {
            String[] tokens = text.toUpperCase().split("\\s");
            String safeToken = (tokens.length == 1 ? String.valueOf(tokens[0].charAt(0)) : tokens[1]); 
            String subst = tokens[0].substring(0, 2)
                                    .concat(safeToken
                                    .substring(0, 1));
            
            StringBuilder bld = new StringBuilder();
            for (int i = 0; i < subst.length(); i++){
                bld.append(ALPHABET.indexOf(subst.charAt(i)) + 1);
            }
            
            return subst
                    .concat("-")
                    .concat(bld.toString());
        }
        return "N/I";
    }
    
    public static String generateCodeUsingEmail(String text) {
        if (StringUtils.isNotBlank(text)) {
            return text.split("@")[0];
            
        }
        return "N/I";
    }
}