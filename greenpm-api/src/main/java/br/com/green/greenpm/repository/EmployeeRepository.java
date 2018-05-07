package br.com.green.greenpm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.green.greenpm.dto.EmployeeDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {

    public Optional<EmployeeDTO> findEmployeeByEmail(String email);
}
