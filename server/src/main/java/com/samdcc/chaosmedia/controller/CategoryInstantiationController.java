package com.samdcc.chaosmedia.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samdcc.chaosmedia.api.CategoryInstantiationMediaAPI;
import com.samdcc.chaosmedia.api.CategoryInstantiationSubcategoryAPI;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationsDTO;
import com.samdcc.chaosmedia.dto.MediaInstantiationPreviewsDTO;
import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.service.CategoryInstantiationService;
import com.samdcc.chaosmedia.service.CategoryService;
import com.samdcc.chaosmedia.service.MediaInstantiationService;
import com.samdcc.chaosmedia.service.MediaService;

@RestController
@RequestMapping("api/category-instantiation")
public class CategoryInstantiationController {

    private final MediaService mediaService;
    private final MediaInstantiationService MIService;
    private final CategoryService categoryService;
    private final CategoryInstantiationService CIService;

    public CategoryInstantiationController(MediaService mediaService, CategoryService categoryService,
            MediaInstantiationService MIService,
            CategoryInstantiationService CIService) {
        this.mediaService = mediaService;
        this.categoryService = categoryService;
        this.MIService = MIService;
        this.CIService = CIService;
    }

    /**
     * GET /api/category-instantiation/{CIId}/media/{mediaId}
     * GET /api/category-instantiation/{CIId}/media/{mediaId}?sort={mediaSortId}
     * 
     * Returns all SortParameters given media.
     * Returns all Subcategories for the given CategoryInstantiation (CI)
     * Returns all data about the given CI
     * Returns all MediaInstantiationPreviews (MIPs) correlated with the CI
     * MIPs are sorted by the sort with id == {mediaSortId}.
     * If ?sort is not specified, the media's default sort is used.
     * 
     */
    @GetMapping("/{CIId}/media/{mediaId}")
    public ResponseEntity<CategoryInstantiationMediaAPI> getMedia(
            @PathVariable Integer CIId,
            @PathVariable Integer mediaId,
            @RequestParam(value = "sort", required = false) Integer mediaSortId) {

        List<SortParameterDTO> sortParameters = mediaService.getAllSortParameterDTOs(mediaId);
        List<CategoryDTO> subcategories = CIService.getSubcategoryDTOs(CIId);
        CategoryInstantiationDTO CI = CIService.getDTO(CIId);
        MediaInstantiationPreviewsDTO MIPs = (mediaSortId != null)
                ? MIService.getCIsPreviewDTOsSorted(mediaId, CIId, mediaSortId)
                : MIService.getCIsPreviewDTOsSorted(mediaId, CIId);

        return ResponseEntity.ok(new CategoryInstantiationMediaAPI(sortParameters, subcategories, CI, MIPs));
    }

    /**
     * GET
     * /api/category-instantiation/{CIId}/subcategory/{categoryId}
     * GET
     * /api/category-instantiation/{CIId}/subcategory/{categoryId}?sort={categorySortId}
     * 
     * Returns all SortParameters for the given subcategory.
     * Returns all Subcategories for the given CategoryInstantiation (CI)
     * Returns all data about the given CI
     * Returns all CIs correlated with the CI and is in the given subcategory
     * CIs are sorted by the sort with id == {categorySortId}.
     * If ?sort is not specified, the category's default sort is used.
     * 
     */
    @GetMapping("/{CIId}/subcategory/{categoryId}")
    public ResponseEntity<CategoryInstantiationSubcategoryAPI> getSubcategory(
            @PathVariable Integer CIId,
            @PathVariable Integer categoryId,
            @RequestParam(value = "sort", required = false) Integer categorySortId) {

        List<SortParameterDTO> sortParameters = categoryService.getAllSortParameterDTOs(categoryId);
        List<CategoryDTO> subcategories = CIService.getSubcategoryDTOs(CIId);
        CategoryInstantiationDTO CI = CIService.getDTO(CIId);
        CategoryInstantiationsDTO CIs = (categorySortId != null)
                ? CIService.getCIsPreviewDTOsSorted(categoryId, CIId, categorySortId)
                : CIService.getCIsPreviewDTOsSorted(categoryId, CIId);

        return ResponseEntity.ok(new CategoryInstantiationSubcategoryAPI(sortParameters, subcategories, CI, CIs));
    }
}
