package com.samdcc.chaosmedia.controller;

import com.samdcc.chaosmedia.service.MediaService;
import com.samdcc.chaosmedia.dto.MediaHomeViewDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final MediaService mediaService;

    public HomeController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * GET /api/home
     * Returns all types of media for the home page.
     */
    @GetMapping("/home")
    public List<MediaHomeViewDTO> getHome() {
        return mediaService.getAllMediaHomeViews();
    }

}
