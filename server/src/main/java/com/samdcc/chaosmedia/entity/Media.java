package com.samdcc.chaosmedia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapKey;

import java.util.Map;

@Entity
@Table(name = "media")
@Access(AccessType.FIELD)
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String imagePath;

    @OneToOne
    @JoinTable(name = "media_default_sort", joinColumns = @JoinColumn(name = "media_id"), inverseJoinColumns = @JoinColumn(name = "media_sort_parameter_id"))
    private MediaSortParameter defaultSort;

    @OneToMany(mappedBy = "media", fetch = FetchType.LAZY)
    @MapKey(name = "id")
    private Map<Integer, MediaSortParameter> mediaSortParameters;

    @OneToMany(mappedBy = "media", fetch = FetchType.LAZY)
    @MapKey(name = "id")
    private Map<Integer, Category> categories;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MediaSortParameter getDefaultSort() {
        return defaultSort;
    }

    public Map<Integer, MediaSortParameter> getMediaSortParameters() {
        return mediaSortParameters;
    }

    public Map<Integer, Category> getCategories() {
        return categories;
    }

}
