package com.example.registri.tables.register;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long>{
    List<Register> findByProfessorId(Long id);
}