package com.samdcc.chaosmedia.controller;

import com.samdcc.chaosmedia.api.MediaAPI;
import com.samdcc.chaosmedia.api.CategoryAPI;
import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.entity.MediaInstantiationPreview;
import com.samdcc.chaosmedia.service.MediaService;
import com.samdcc.chaosmedia.service.CategoryService;
import com.samdcc.chaosmedia.service.MediaInstantiationService;
import com.samdcc.chaosmedia.service.CategoryInstantiationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * GET /api/{mediaId}/all/{mediaSortId}
     * 
     * Returns all MediaSortParameters with specified {mediaId}
     * 
     * Returns all Categories with specified {mediaId}
     * 
     * Returns all MediaInstantiationPreviews with specified {mediaId},
     * sorted by the MediaSortParameter with specified {mediaSortId}.
     */
    @GetMapping("/{mediaId}/all/{mediaSortId}")
    public ResponseEntity<MediaAPI> getAll(
            @PathVariable("mediaId") Integer mediaId,
            @PathVariable("mediaSortId") Integer mediaSortId) {
        List<SortParameterDTO> sortParameters = mediaService.getAllSortParameterDTOs(mediaId);
        List<CategoryDTO> categories = mediaService.getAllCategoryDTOs(mediaId);
        List<MediaInstantiationPreview> MIPs = MIService.getAllMediaPreviewsSorted(mediaId, mediaSortId);
        return ResponseEntity.ok(new MediaAPI(sortParameters, categories, MIPs));
    }

    /**
     * GET /api/{mediaId}/{categoryId}/{categorySortId}
     * 
     * Returns all CategorySortParameters with specified {categoryId}
     * 
     * Returns all Categories with specified {mediaId}
     * 
     * Returns all CategoryInstantiationDTOs with specified {categoryId},
     * sorted by the CategorySortParameter with specified {categorySortId}.
     */
    @GetMapping("/{mediaId}/{categoryId}/{categorySortId}")
    public ResponseEntity<CategoryAPI> getCategory(
            @PathVariable("mediaId") Integer mediaId,
            @PathVariable("categoryId") Integer categoryId,
            @PathVariable("categorySortId") Integer categorySortId) {
        List<SortParameterDTO> sortParameters = categoryService.getAllSortParameterDTOs(categoryId);
        List<CategoryDTO> categories = mediaService.getAllCategoryDTOs(mediaId);
        List<CategoryInstantiationDTO> CIs = CIService.getAllDTOsSorted(categoryId, categorySortId);
        return ResponseEntity.ok(new CategoryAPI(sortParameters, categories, CIs));
    }

}
