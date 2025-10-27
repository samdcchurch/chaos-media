package com.samdcc.chaosmedia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "media_sort_parameter_instantiations")
@Access(AccessType.FIELD)
public class MediaSortParameterInstantiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sortValue;

    @ManyToOne
    @JoinColumn(name = "media_sort_parameter_id", nullable = false)
    private MediaSortParameter mediaSortParameter;

    @ManyToOne
    @JoinColumn(name = "media_instantiation_id", nullable = false)
    private MediaInstantiationPreview mediaPreview;

    public Integer getId() {
        return id;
    }

    public String getSortValue() {
        return sortValue;
    }

    public MediaInstantiationPreview getMediaPreview() {
        return mediaPreview;
    }

}
