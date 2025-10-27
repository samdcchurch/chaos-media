package com.samdcc.chaosmedia.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.entity.Category;
import com.samdcc.chaosmedia.entity.CategorySortParameter;
import com.samdcc.chaosmedia.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<SortParameterDTO> getAllSortParameterDTOs(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        return category.getCategorySortParameters().values().stream()
                .sorted(Comparator.comparing(CategorySortParameter::getId))
                .map(parameter -> new SortParameterDTO(parameter.getId(), parameter.getName()))
                .toList();
    }

}
