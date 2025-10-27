package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.entity.Category;
import com.samdcc.chaosmedia.entity.CategorySortParameter;
import com.samdcc.chaosmedia.entity.CategorySortParameterInstantiation;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.enums.SortType;
import com.samdcc.chaosmedia.enums.SortOrder;
import com.samdcc.chaosmedia.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CategoryInstantiationService {

    private final CategoryRepository categoryRepository;

    public CategoryInstantiationService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryInstantiationDTO> getAllDTOsSorted(Integer categoryId, Integer categorySortId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        CategorySortParameter categorySortParameter = category.getCategorySortParameters().get(categorySortId);
        if (categorySortParameter == null) {
            throw new RuntimeException("categorySortParameter not found.");
        }
        SortType sortType = categorySortParameter.getSortType();
        SortOrder sortOrder = categorySortParameter.getSortOrder();
        List<CategorySortParameterInstantiation> instantiations = categorySortParameter
                .getCategorySortParameterInstantiations();
        List<CategoryInstantiationDTO> categoryInstantiations;

        switch (sortType) {
            case STRING:
                if (sortOrder == SortOrder.ASC) {
                    categoryInstantiations = instantiations.stream()
                            .sorted(Comparator.comparing(CategorySortParameterInstantiation::getSortValue))
                            .map(inst -> new CategoryInstantiationDTO(
                                    inst.getCategoryInstantiation().getId(),
                                    inst.getCategoryInstantiation().getName(),
                                    inst.getCategoryInstantiation().getImagePath()))
                            .toList();
                } else {
                    categoryInstantiations = instantiations.stream()
                            .sorted(Comparator.comparing(CategorySortParameterInstantiation::getSortValue).reversed())
                            .map(inst -> new CategoryInstantiationDTO(
                                    inst.getCategoryInstantiation().getId(),
                                    inst.getCategoryInstantiation().getName(),
                                    inst.getCategoryInstantiation().getImagePath()))
                            .toList();
                }
                break;
            case INT:
                if (sortOrder == SortOrder.ASC) {
                    categoryInstantiations = instantiations.stream()
                            .sorted(Comparator.comparingInt(
                                    (CategorySortParameterInstantiation inst) -> Integer.parseInt(inst.getSortValue())))
                            .map(inst -> new CategoryInstantiationDTO(
                                    inst.getCategoryInstantiation().getId(),
                                    inst.getCategoryInstantiation().getName(),
                                    inst.getCategoryInstantiation().getImagePath()))
                            .toList();
                } else {
                    categoryInstantiations = instantiations.stream()
                            .sorted(Comparator.comparingInt(
                                    (CategorySortParameterInstantiation inst) -> Integer.parseInt(inst.getSortValue()))
                                    .reversed())
                            .map(inst -> new CategoryInstantiationDTO(
                                    inst.getCategoryInstantiation().getId(),
                                    inst.getCategoryInstantiation().getName(),
                                    inst.getCategoryInstantiation().getImagePath()))
                            .toList();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown sort type");
        }

        return categoryInstantiations;
    }

}
