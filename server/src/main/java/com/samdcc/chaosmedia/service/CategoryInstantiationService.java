package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.entity.Category;
import com.samdcc.chaosmedia.entity.CategoryInstantiation;
import com.samdcc.chaosmedia.entity.CategorySortParameter;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationsDTO;
import com.samdcc.chaosmedia.repository.CategoryRepository;
import com.samdcc.chaosmedia.util.SortUtils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryInstantiationService {

    private final CategoryRepository categoryRepository;

    public CategoryInstantiationService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryInstantiationsDTO getAllDTOsSorted(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        return sortDTOs(category.getDefaultSort());
    }

    public CategoryInstantiationsDTO getAllDTOsSorted(Integer categoryId, Integer categorySortId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        CategorySortParameter categorySortParameter = category.getCategorySortParameters().get(categorySortId);
        if (categorySortParameter == null) {
            throw new RuntimeException("categorySortParameter not found.");
        }
        return sortDTOs(categorySortParameter);
    }

    private CategoryInstantiationsDTO sortDTOs(CategorySortParameter categorySortParameter) {
        List<CategoryInstantiation> CIs = SortUtils.sortByParameter(categorySortParameter);
        List<CategoryInstantiationDTO> DTOs = CIs.stream().map(inst -> new CategoryInstantiationDTO(
                inst.getId(),
                inst.getName(),
                inst.getImagePath()))
                .toList();
        return new CategoryInstantiationsDTO(categorySortParameter.getId(), DTOs);
    }

}
