package com.samdcc.chaosmedia.api;

import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.dto.MediaInstantiationPreviewsDTO;
import com.samdcc.chaosmedia.dto.SortParameterDTO;

import java.util.List;

public record CategoryInstantiationMediaAPI(List<SortParameterDTO> sortParameter, List<CategoryDTO> subcategories,
                CategoryInstantiationDTO categoryInstantiation, MediaInstantiationPreviewsDTO media) {

}
