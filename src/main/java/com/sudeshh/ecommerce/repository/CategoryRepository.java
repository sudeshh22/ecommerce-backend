package com.sudeshh.ecommerce.repository;

import com.sudeshh.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}