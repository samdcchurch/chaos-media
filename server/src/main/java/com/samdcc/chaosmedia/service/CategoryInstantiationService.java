package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.entity.Category;
import com.samdcc.chaosmedia.entity.CategoryInstantiation;
import com.samdcc.chaosmedia.entity.CategorySortParameter;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationsDTO;
import com.samdcc.chaosmedia.repository.CategoryInstantiationRepository;
import com.samdcc.chaosmedia.repository.CategoryRepository;
import com.samdcc.chaosmedia.util.SortUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryInstantiationService {

    private final CategoryInstantiationRepository CIRepository;
    private final CategoryRepository categoryRepository;

    public CategoryInstantiationService(CategoryInstantiationRepository CIRepository,
            CategoryRepository categoryRepository) {
        this.CIRepository = CIRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategoryInstantiationDTO getDTO(Integer CIId) {
        CategoryInstantiation CI = CIRepository.findById(CIId).orElseThrow(() -> new RuntimeException("CI not found."));
        return new CategoryInstantiationDTO(CI.getId(), CI.getName(), CI.getImagePath());
    }

    public List<CategoryDTO> getSubcategoryDTOs(Integer CIId) {
        Category category = CIRepository.findById(CIId).orElseThrow(() -> new RuntimeException("CI not found."))
                .getCategory();
        List<Category> subcategories = category.getSubcategories();
        return subcategories.stream().map(subcat -> new CategoryDTO(subcat.getId(), subcat.getName())).toList();
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

    public CategoryInstantiationsDTO getCIsPreviewDTOsSorted(Integer categoryId, Integer CIId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        CategoryInstantiation CI = CIRepository.findById(CIId).orElseThrow(() -> new RuntimeException("CI not found."));
        return getCIsPreviewDTOsSorted(category, CI, category.getDefaultSort());
    }

    public CategoryInstantiationsDTO getCIsPreviewDTOsSorted(Integer categoryId, Integer CIId, Integer categorySortId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        CategoryInstantiation CI = CIRepository.findById(CIId).orElseThrow(() -> new RuntimeException("CI not found."));
        CategorySortParameter categorySortParameter = category.getCategorySortParameters().get(categorySortId);
        if (categorySortParameter == null) {
            throw new RuntimeException("mediaSortParameter not found.");
        }
        return getCIsPreviewDTOsSorted(category, CI, categorySortParameter);
    }

    public CategoryInstantiationsDTO getCIsPreviewDTOsSorted(Category category, CategoryInstantiation CI,
            CategorySortParameter categorySortParameter) {
        List<CategoryInstantiation> allCIsSorted = SortUtils.sortByParameter(categorySortParameter);
        List<CategoryInstantiation> CIsCIs = CI.getCategoryInstantiations();
        Set<Integer> ciCiIds = CIsCIs.stream().map(CategoryInstantiation::getId).collect(Collectors.toSet());
        List<CategoryInstantiationDTO> SortedDTOs = allCIsSorted.stream()
                .filter(ci -> ciCiIds.contains(ci.getId()))
                .map(ci -> new CategoryInstantiationDTO(ci.getId(), ci.getName(), ci.getImagePath()))
                .toList();

        return new CategoryInstantiationsDTO(categorySortParameter.getId(), SortedDTOs);
    }

}
