package com.samdcc.chaosmedia.api;

import com.samdcc.chaosmedia.dto.SortParameterDTO;
import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationsDTO;

import java.util.List;

public record CategoryAPI(List<SortParameterDTO> sortParameters, List<CategoryDTO> categories,
                CategoryInstantiationsDTO categoryInstantiations) {

}
