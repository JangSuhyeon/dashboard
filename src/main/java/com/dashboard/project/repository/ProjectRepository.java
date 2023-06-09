package com.dashboard.project.repository;

import com.dashboard.project.domain.Project;
import org.hibernate.query.JpaTuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p,c FROM Project p LEFT JOIN Code c ON c.groupCode = 'STATUS' AND p.status = c.code")
    Page<JpaTuple> findAllAsTuple(Pageable pageable);

}
