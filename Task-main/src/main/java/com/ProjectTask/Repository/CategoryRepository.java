package com.ProjectTask.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjectTask.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
