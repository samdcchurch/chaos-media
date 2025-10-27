package com.samdcc.chaosmedia.api;

import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.entity.MediaInstantiationPreview;

import java.util.List;

public record MediaAPI(List<SortParameterDTO> sortParameters, List<CategoryDTO> categories,
                List<MediaInstantiationPreview> mediaInstantiationPreviews) {

}
