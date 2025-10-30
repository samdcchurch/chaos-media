package com.samdcc.chaosmedia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "categories")
@Access(AccessType.FIELD)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinTable(name = "category_default_sort", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "category_sort_parameter_id"))
    private CategorySortParameter defaultSort;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @MapKey(name = "id")
    private Map<Integer, CategorySortParameter> categorySortParameters;

    @ManyToMany
    @JoinTable(name = "category_to_category", joinColumns = @JoinColumn(name = "parent_category_id"), inverseJoinColumns = @JoinColumn(name = "child_category_id"))
    private List<Category> subcategories;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategorySortParameter getDefaultSort() {
        return defaultSort;
    }

    public Map<Integer, CategorySortParameter> getCategorySortParameters() {
        return categorySortParameters;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

}
