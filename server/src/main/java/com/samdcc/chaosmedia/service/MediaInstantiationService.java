package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.entity.MediaInstantiationPreview;
import com.samdcc.chaosmedia.entity.Media;
import com.samdcc.chaosmedia.entity.MediaSortParameter;
import com.samdcc.chaosmedia.entity.MediaSortParameterInstantiation;
import com.samdcc.chaosmedia.enums.SortType;
import com.samdcc.chaosmedia.enums.SortOrder;
import com.samdcc.chaosmedia.repository.MediaRepository;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MediaInstantiationService {

    private final MediaRepository mediaRepository;

    public MediaInstantiationService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<MediaInstantiationPreview> getAllMediaPreviewsSorted(Integer mediaId, Integer mediaSortId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        MediaSortParameter mediaSortParameter = media.getMediaSortParameters().get(mediaSortId);
        if (mediaSortParameter == null) {
            throw new RuntimeException("mediaSortParameter not found.");
        }
        SortType sortType = mediaSortParameter.getSortType();
        SortOrder sortOrder = mediaSortParameter.getSortOrder();
        List<MediaSortParameterInstantiation> instantiations = mediaSortParameter.getMediaSortParameterInstantiations();
        List<MediaInstantiationPreview> mediaPreviews;

        switch (sortType) {
            case STRING:
                if (sortOrder == SortOrder.ASC) {
                    mediaPreviews = instantiations.stream()
                            .sorted(Comparator.comparing(MediaSortParameterInstantiation::getSortValue))
                            .map(MediaSortParameterInstantiation::getMediaPreview).toList();
                } else {
                    mediaPreviews = instantiations.stream()
                            .sorted(Comparator.comparing(MediaSortParameterInstantiation::getSortValue).reversed())
                            .map(MediaSortParameterInstantiation::getMediaPreview).toList();
                }
                break;
            case INT:
                if (sortOrder == SortOrder.ASC) {
                    mediaPreviews = instantiations.stream()
                            .sorted(Comparator.comparingInt(
                                    (MediaSortParameterInstantiation inst) -> Integer.parseInt(inst.getSortValue())))
                            .map(MediaSortParameterInstantiation::getMediaPreview).toList();
                } else {
                    mediaPreviews = instantiations.stream()
                            .sorted(Comparator.comparingInt(
                                    (MediaSortParameterInstantiation inst) -> Integer.parseInt(inst.getSortValue()))
                                    .reversed())
                            .map(MediaSortParameterInstantiation::getMediaPreview).toList();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown sort type");
        }

        return mediaPreviews;
    }

}
