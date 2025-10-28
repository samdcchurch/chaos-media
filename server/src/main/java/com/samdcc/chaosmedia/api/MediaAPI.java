package com.samdcc.chaosmedia.api;

import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.MediaInstantiationPreviewsDTO;

import java.util.List;

public record MediaAPI(List<SortParameterDTO> sortParameters, List<CategoryDTO> categories,
                MediaInstantiationPreviewsDTO media) {

}
