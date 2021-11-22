package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Task.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
}

