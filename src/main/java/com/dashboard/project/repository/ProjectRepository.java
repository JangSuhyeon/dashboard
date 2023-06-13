package com.dashboard.project.repository;

import com.dashboard.project.domain.Project;
import com.dashboard.project.domain.dto.ProjectRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Page<Project> findAll(Pageable pageable);

    Project save(Project project);
}
