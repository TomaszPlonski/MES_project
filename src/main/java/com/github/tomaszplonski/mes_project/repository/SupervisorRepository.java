package com.github.tomaszplonski.mes_project.repository;

import com.github.tomaszplonski.mes_project.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {


}
