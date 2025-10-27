package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.dto.MediaDTO;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.entity.Media;
import com.samdcc.chaosmedia.entity.MediaSortParameter;
import com.samdcc.chaosmedia.entity.Category;
import com.samdcc.chaosmedia.repository.MediaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<MediaDTO> getAllMediaDTOs() {
        List<Media> medias = mediaRepository.findAll();
        return medias.stream().map(m -> new MediaDTO(m.getId(), m.getName(), m.getImagePath()))
                .collect(Collectors.toList());
    }

    public List<SortParameterDTO> getAllSortParameterDTOs(Integer mediaId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        return media.getMediaSortParameters().values().stream()
                .sorted(Comparator.comparing(MediaSortParameter::getId))
                .map(parameter -> new SortParameterDTO(parameter.getId(), parameter.getName()))
                .toList();
    }

    public List<CategoryDTO> getAllCategoryDTOs(Integer mediaId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        return media.getCategories().values().stream()
                .sorted(Comparator.comparing(Category::getId))
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .toList();
    }

}
