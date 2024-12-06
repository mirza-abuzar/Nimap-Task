package com.ProjectTask.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjectTask.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
