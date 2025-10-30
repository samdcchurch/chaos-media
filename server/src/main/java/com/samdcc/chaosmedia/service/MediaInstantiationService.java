package com.samdcc.chaosmedia.service;

import com.samdcc.chaosmedia.dto.MediaInstantiationDTO;
import com.samdcc.chaosmedia.dto.MediaInstantiationPreviewsDTO;
import com.samdcc.chaosmedia.entity.CategoryInstantiation;
import com.samdcc.chaosmedia.entity.Media;
import com.samdcc.chaosmedia.entity.MediaInstantiation;
import com.samdcc.chaosmedia.entity.MediaInstantiationPreview;
import com.samdcc.chaosmedia.entity.MediaSortParameter;
import com.samdcc.chaosmedia.repository.CategoryInstantiationRepository;
import com.samdcc.chaosmedia.repository.MediaInstantiationRepository;
import com.samdcc.chaosmedia.repository.MediaRepository;
import com.samdcc.chaosmedia.util.SortUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MediaInstantiationService {

    private final MediaInstantiationRepository mediaInstantiationRepository;
    private final MediaRepository mediaRepository;
    private final CategoryInstantiationRepository CIRepository;

    public MediaInstantiationService(MediaInstantiationRepository mediaInstantiationRepository,
            MediaRepository mediaRepository, CategoryInstantiationRepository CIRepository) {
        this.mediaInstantiationRepository = mediaInstantiationRepository;
        this.mediaRepository = mediaRepository;
        this.CIRepository = CIRepository;
    }

    public MediaInstantiationDTO getMediaInstantiationDTO(Integer mediaInstantiationId) {
        MediaInstantiation MI = mediaInstantiationRepository.findById(mediaInstantiationId)
                .orElseThrow(() -> new RuntimeException("MediaInstantiation not found."));
        return new MediaInstantiationDTO(MI.getId(), MI.getName(), MI.getDescription(), MI.getImagePath(),
                MI.getFilePath());
    }

    public MediaInstantiationPreviewsDTO getAllPreviewDTOsSorted(Integer mediaId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        return sortMedia(media.getDefaultSort());
    }

    public MediaInstantiationPreviewsDTO getAllPreviewDTOsSorted(Integer mediaId, Integer mediaSortId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        MediaSortParameter mediaSortParameter = media.getMediaSortParameters().get(mediaSortId);
        if (mediaSortParameter == null) {
            throw new RuntimeException("mediaSortParameter not found.");
        }
        return sortMedia(mediaSortParameter);
    }

    private MediaInstantiationPreviewsDTO sortMedia(MediaSortParameter mediaSortParameter) {
        List<MediaInstantiationPreview> MIPs = SortUtils.sortByParameter(mediaSortParameter);
        return new MediaInstantiationPreviewsDTO(mediaSortParameter.getId(), MIPs);
    }

    public MediaInstantiationPreviewsDTO getCIsPreviewDTOsSorted(Integer mediaId, Integer CIId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        CategoryInstantiation CI = CIRepository.findById(CIId).orElseThrow(() -> new RuntimeException("CI not found."));
        return getCIsPreviewDTOsSorted(media, CI, media.getDefaultSort());
    }

    public MediaInstantiationPreviewsDTO getCIsPreviewDTOsSorted(Integer mediaId, Integer CIId, Integer mediaSortId) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found."));
        CategoryInstantiation CI = CIRepository.findById(CIId).orElseThrow(() -> new RuntimeException("CI not found."));
        MediaSortParameter mediaSortParameter = media.getMediaSortParameters().get(mediaSortId);
        if (mediaSortParameter == null) {
            throw new RuntimeException("mediaSortParameter not found.");
        }
        return getCIsPreviewDTOsSorted(media, CI, mediaSortParameter);
    }

    public MediaInstantiationPreviewsDTO getCIsPreviewDTOsSorted(Media media, CategoryInstantiation CI,
            MediaSortParameter mediaSortParameter) {
        List<MediaInstantiationPreview> allMIPsSorted = SortUtils.sortByParameter(mediaSortParameter);
        List<MediaInstantiationPreview> CIsMIPs = CI.getMediaInstantiationPreviews();
        Set<Integer> ciMipIds = CIsMIPs.stream().map(MediaInstantiationPreview::getId).collect(Collectors.toSet());
        List<MediaInstantiationPreview> CIsMIPsSorted = allMIPsSorted.stream()
                .filter(mip -> ciMipIds.contains(mip.getId()))
                .toList();

        return new MediaInstantiationPreviewsDTO(mediaSortParameter.getId(), CIsMIPsSorted);
    }

}
