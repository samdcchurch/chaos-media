package com.samdcc.chaosmedia.entity.interfaces;

import com.samdcc.chaosmedia.entity.enums.SortType;
import com.samdcc.chaosmedia.entity.enums.SortOrder;

import java.util.List;

public interface SortParameter<I extends SortParameterInstantiation<?, ?>> {
    SortType getSortType();

    SortOrder getSortOrder();

    List<I> getSortParameterInstantiations();
}
