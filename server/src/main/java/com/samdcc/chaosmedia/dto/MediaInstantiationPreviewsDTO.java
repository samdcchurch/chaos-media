package com.samdcc.chaosmedia.dto;

import com.samdcc.chaosmedia.entity.MediaInstantiationPreview;

import java.util.List;

public record MediaInstantiationPreviewsDTO(Integer sortId,
                List<MediaInstantiationPreview> mediaInstantiationPreviews) {

}
