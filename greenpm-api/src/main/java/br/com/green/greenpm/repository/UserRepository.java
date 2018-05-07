package br.com.green.greenpm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.green.greenpm.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    public Optional<UserDTO> findSysUserByUsername(String username);
}
