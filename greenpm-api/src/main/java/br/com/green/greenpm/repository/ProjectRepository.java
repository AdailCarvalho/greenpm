package br.com.green.greenpm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.green.greenpm.dto.ProjectDTO;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectDTO, Long>{

    public Optional<ProjectDTO>  findProjectByProjectCod(String projectCod);
    
    @Modifying
    @Query("UPDATE ProjectDTO p SET p.flgClosedProject = 'Y' where cod_project = ?1")
    public void updateProjectStatus(String projectCod);
}