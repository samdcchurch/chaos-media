package com.samdcc.chaosmedia.api;

import com.samdcc.chaosmedia.dto.CategoryDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationDTO;
import com.samdcc.chaosmedia.dto.CategoryInstantiationsDTO;
import com.samdcc.chaosmedia.dto.SortParameterDTO;

import java.util.List;

public record CategoryInstantiationSubcategoryAPI(List<SortParameterDTO> sortParameter, List<CategoryDTO> subcategories,
        CategoryInstantiationDTO categoryInstantiation, CategoryInstantiationsDTO categories) {

}
