package com.samdcc.chaosmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samdcc.chaosmedia.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
