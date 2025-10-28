package com.samdcc.chaosmedia.controller;

import com.samdcc.chaosmedia.api.MediaAPI;
import com.samdcc.chaosmedia.api.CategoryAPI;
import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationsDTO;
import com.samdcc.chaosmedia.dto.MediaInstantiationPreviewsDTO;
import com.samdcc.chaosmedia.service.MediaService;
import com.samdcc.chaosmedia.service.CategoryService;
import com.samdcc.chaosmedia.service.MediaInstantiationService;
import com.samdcc.chaosmedia.service.CategoryInstantiationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MediaController {

    private final MediaService mediaService;
    private final CategoryService categoryService;
    private final MediaInstantiationService MIService;
    private final CategoryInstantiationService CIService;

    public MediaController(MediaService mediaService, CategoryService categoryService,
            MediaInstantiationService MIService, CategoryInstantiationService CIService) {
        this.mediaService = mediaService;
        this.categoryService = categoryService;
        this.MIService = MIService;
        this.CIService = CIService;
    }

    /**
     * GET /api/media/{mediaId}/all
     * GET /api/media/{mediaId}/all?sort={mediaSortId}
     * 
     * Returns all SortParameters, Categories, and MediaInstantiationPreviews (MIPs)
     * for the given media. MIPs are sorted by the sort with id == {mediaSortId}.
     * If ?sort is not specified, the media's default sort is used.
     */
    @GetMapping("/media/{mediaId}/all")
    public ResponseEntity<MediaAPI> getAll(
            @PathVariable Integer mediaId,
            @RequestParam(value = "sort", required = false) Integer mediaSortId) {
        List<SortParameterDTO> sortParameters = mediaService.getAllSortParameterDTOs(mediaId);
        List<CategoryDTO> categories = mediaService.getAllCategoryDTOs(mediaId);
        MediaInstantiationPreviewsDTO MIPs = (mediaSortId != null)
                ? MIService.getAllPreviewDTOsSorted(mediaId, mediaSortId)
                : MIService.getAllPreviewDTOsSorted(mediaId);

        return ResponseEntity.ok(new MediaAPI(sortParameters, categories, MIPs));
    }

    /**
     * GET /api//media/{mediaId}/category/{categoryId}
     * GET /api//media/{mediaId}/category/{categoryId}?sort={categorySortId}
     * 
     * Returns all CategoryInstantiations (CIs) and sort parameters for the given
     * category.
     * Also returns all categories for the given media.
     * CIs are sorted by the sort with id == {categorySortId}.
     * If ?sort is not specified, the category's default sort is used.
     */
    @GetMapping("/media/{mediaId}/category/{categoryId}")
    public ResponseEntity<CategoryAPI> getCategorySorted(
            @PathVariable("mediaId") Integer mediaId,
            @PathVariable("categoryId") Integer categoryId,
            @RequestParam(value = "sort", required = false) Integer categorySortId) {
        List<SortParameterDTO> sortParameters = categoryService.getAllSortParameterDTOs(categoryId);
        List<CategoryDTO> categories = mediaService.getAllCategoryDTOs(mediaId);
        CategoryInstantiationsDTO CIs = (categorySortId != null)
                ? CIService.getAllDTOsSorted(categoryId, categorySortId)
                : CIService.getAllDTOsSorted(categoryId);

        return ResponseEntity.ok(new CategoryAPI(sortParameters, categories, CIs));
    }

}
