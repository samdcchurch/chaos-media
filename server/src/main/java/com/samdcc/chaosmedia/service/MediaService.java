package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.dto.MediaHomeViewDTO;
import com.samdcc.chaosmedia.entity.Media;
import com.samdcc.chaosmedia.repository.MediaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<MediaHomeViewDTO> getAllMediaHomeViews() {
        List<Media> medias = mediaRepository.findAll();
        return medias.stream().map(m -> new MediaHomeViewDTO(m.getName(), m.getImagePath()))
                .collect(Collectors.toList());
    }

}
