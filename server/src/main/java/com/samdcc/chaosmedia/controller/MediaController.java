package com.samdcc.chaosmedia.controller;

import com.samdcc.chaosmedia.entity.MediaPreview;
import com.samdcc.chaosmedia.service.MediaPreviewService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MediaController {

    private final MediaPreviewService mediaPreviewService;

    public MediaController(MediaPreviewService mediaPreviewService) {
        this.mediaPreviewService = mediaPreviewService;
    }

    /**
     * GET /api/home
     * Returns all MediaPreviews for media with id == mediaId.
     * Sorted by the MediaSortParameter with id == mediaSortId.
     */
    @GetMapping("/{mediaId}/all/{mediaSortId}")
    public List<MediaPreview> getHome(
            @PathVariable("mediaId") Integer mediaId,
            @PathVariable("mediaSortId") Integer mediaSortId) {
        return mediaPreviewService.getAllMediaPreviewsSorted(mediaId, mediaSortId);
    }
}
