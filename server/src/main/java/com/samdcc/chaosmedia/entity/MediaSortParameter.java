package com.samdcc.chaosmedia.entity;

import com.samdcc.chaosmedia.entity.interfaces.SortParameter;
import com.samdcc.chaosmedia.entity.enums.SortOrder;
import com.samdcc.chaosmedia.entity.enums.SortType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.util.List;

@Entity
@Table(name = "media_sort_parameters")
@Access(AccessType.FIELD)
public class MediaSortParameter implements SortParameter<MediaSortParameterInstantiation> {

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

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    @OneToMany(mappedBy = "mediaSortParameter")
    private List<MediaSortParameterInstantiation> mediaSortParameterInstantiations;

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

    public List<MediaSortParameterInstantiation> getSortParameterInstantiations() {
        return mediaSortParameterInstantiations;
    }

}
