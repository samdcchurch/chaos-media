package com.samdcc.chaosmedia.entity.interfaces;

public interface SortParameterInstantiation<I, T> {
    String getSortValue();

    T getInstantiation();
}
