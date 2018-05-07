package br.com.green.greenpm.service;

import java.util.List;

import br.com.green.greenpm.exception.AuthException;
import br.com.green.greenpm.exception.EntityExistsException;
import br.com.green.greenpm.exception.EntityNotFoundException;
import br.com.green.greenpm.model.ProjectModel;
import br.com.green.greenpm.vo.ProjectVO;

/**
 * 
 * @author Adail Carvalho
 *
 * @since 2018-04-25
 */
public interface ProjectService {
    
    /**
     * Get project informations with the passed cod.
     * 
     * @return a project object, with the specified cod.
     * 
     * @throws EntityNotFoundException
     */
    public ProjectModel getProjectByCod(String projectCod) throws EntityNotFoundException;
    
    /**
     * Save project informations on db.
     * 
     * @return projectCd A generated project code identifier.
     * 
     * @throws EntityExistsException 
     */
    public String saveProject(ProjectVO project) throws EntityExistsException, AuthException ;
    
    /**
     * List all projects persisted on db.
     * @return
     */
    public List<ProjectModel> listAllProjects();
}