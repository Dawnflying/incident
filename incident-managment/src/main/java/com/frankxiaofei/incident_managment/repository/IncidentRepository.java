package com.frankxiaofei.incident_managment.repository;

import com.frankxiaofei.incident_managment.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    // 根据title查询
    @Query("select i from Incident i where i.title = :title")
    Incident findByTitle(@Param("title") String title);
}
