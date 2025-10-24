package com.samdcc.chaosmedia.entity;

import com.samdcc.chaosmedia.enums.SortType;
import com.samdcc.chaosmedia.enums.SortOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "media_sort_parameters")
@Access(AccessType.FIELD)
public class MediaSortParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SortType sortType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SortOrder sortOrder;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SortType getSortType() {
        return sortType;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

}
