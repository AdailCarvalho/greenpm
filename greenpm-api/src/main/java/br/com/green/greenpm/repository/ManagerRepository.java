package br.com.green.greenpm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.green.greenpm.dto.ManagerDTO;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerDTO, Long> {

    public Optional<ManagerDTO> findManagerByEmail(String email);
}