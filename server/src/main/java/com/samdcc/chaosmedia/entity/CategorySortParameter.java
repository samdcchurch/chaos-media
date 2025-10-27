package com.samdcc.chaosmedia.entity;

import com.samdcc.chaosmedia.enums.SortType;

import java.util.List;

import com.samdcc.chaosmedia.enums.SortOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
@Table(name = "category_sort_parameters")
@Access(AccessType.FIELD)
public class CategorySortParameter {

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
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "categorySortParameter")
    private List<CategorySortParameterInstantiation> categorySortParameterInstantiations;

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

    public List<CategorySortParameterInstantiation> getCategorySortParameterInstantiations() {
        return categorySortParameterInstantiations;
    }

}
