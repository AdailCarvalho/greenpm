package br.com.green.greenpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.green.greenpm.exception.AuthException;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;
import br.com.green.greenpm.model.ProjectModel;
import br.com.green.greenpm.service.ProjectService;
import br.com.green.greenpm.vo.ProjectVO;

/**
 * 
 * @author Adail Carvalho
 *
 * @since 2018-04-24
 */

@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping(value = "/{cod}")
    public ResponseEntity<ProjectModel> getProject(@PathVariable(value = "cod") String projectCod) throws EntityNotFoundException {
        ProjectModel project = projectService.getProjectByCod(projectCod);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<ProjectModel>> listAllProjects() {
        List<ProjectModel> projects = projectService.listAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<String> saveProject(@RequestBody ProjectVO project) 
            throws EntityExistsException, AuthException {
        String projectCd = projectService.saveProject(project);
        return new ResponseEntity<>(projectCd, HttpStatus.OK);
    }
    
    @PutMapping(value = "/{cod}")
    public ResponseEntity<String> closeProject(@PathVariable(value = "cod") String projectCod) throws EntityNotFoundException {
        String code = this.projectService.closeProject(projectCod);
        return new ResponseEntity<>("Updated projec with project code => " + code, HttpStatus.OK);
    }
}
