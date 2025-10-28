package com.samdcc.chaosmedia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samdcc.chaosmedia.api.MediaInstantiationAPI;
import com.samdcc.chaosmedia.dto.MediaInstantiationDTO;
import com.samdcc.chaosmedia.service.MediaInstantiationService;

@RestController
@RequestMapping("api/media-instantiation")
public class MediaInstantiationController {

    private final MediaInstantiationService mediaInstantiationService;

    public MediaInstantiationController(MediaInstantiationService mediaInstantiationService) {
        this.mediaInstantiationService = mediaInstantiationService;
    }

    /**
     * GET /api/media-instantiation/{mediaInstantiationId}
     * 
     * Returns all data about the given MediaInstantiation
     * 
     */
    @GetMapping("/{mediaInstantiationId}")
    public ResponseEntity<MediaInstantiationAPI> getAll(
            @PathVariable Integer mediaInstantiationId) {
        MediaInstantiationDTO MI = mediaInstantiationService.getMediaInstantiationDTO(mediaInstantiationId);
        return ResponseEntity.ok(new MediaInstantiationAPI(MI));
    }
}
