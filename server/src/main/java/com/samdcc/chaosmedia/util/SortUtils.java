package com.samdcc.chaosmedia.util;

import java.util.Comparator;
import java.util.List;

import com.samdcc.chaosmedia.entity.enums.SortOrder;
import com.samdcc.chaosmedia.entity.enums.SortType;
import com.samdcc.chaosmedia.entity.interfaces.SortParameter;
import com.samdcc.chaosmedia.entity.interfaces.SortParameterInstantiation;

public class SortUtils {

    /**
     * Sorts and maps a list of instantiations to their target type based on the
     * given SortParameter.
     *
     * @param <S> SortParameter type
     * @param <I> SortParameterInstantiation type
     * @param <T> The resulting object type
     */
    public static <S extends SortParameter<I>, I extends SortParameterInstantiation<S, T>, T> List<T> sortByParameter(
            S sortParameter) {
        SortType sortType = sortParameter.getSortType();
        SortOrder sortOrder = sortParameter.getSortOrder();
        List<I> instantiations = sortParameter.getSortParameterInstantiations();
        List<T> sorted;

        switch (sortType) {
            case STRING:
                if (sortOrder == SortOrder.ASC) {
                    sorted = instantiations.stream()
                            .sorted(Comparator.comparing(I::getSortValue))
                            .map(I::getInstantiation).toList();
                } else {
                    sorted = instantiations.stream()
                            .sorted(Comparator.comparing(I::getSortValue).reversed())
                            .map(I::getInstantiation).toList();
                }
                break;
            case INT:
                if (sortOrder == SortOrder.ASC) {
                    sorted = instantiations.stream()
                            .sorted(Comparator.comparingInt(
                                    (I inst) -> Integer.parseInt(inst.getSortValue())))
                            .map(I::getInstantiation).toList();
                } else {
                    sorted = instantiations.stream()
                            .sorted(Comparator.comparingInt(
                                    (I inst) -> Integer.parseInt(inst.getSortValue()))
                                    .reversed())
                            .map(I::getInstantiation).toList();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown sort type");
        }

        return sorted;
    }

}
