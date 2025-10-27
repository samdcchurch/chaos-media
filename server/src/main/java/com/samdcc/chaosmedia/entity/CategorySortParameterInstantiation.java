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
@Table(name = "category_sort_parameter_instantiations")
@Access(AccessType.FIELD)
public class CategorySortParameterInstantiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sortValue;

    @ManyToOne
    @JoinColumn(name = "category_sort_parameter_id", nullable = false)
    private CategorySortParameter categorySortParameter;

    @ManyToOne
    @JoinColumn(name = "category_instantiation_id", nullable = false)
    private CategoryInstantiation categoryInstantiation;

    public Integer getId() {
        return id;
    }

    public String getSortValue() {
        return sortValue;
    }

    public CategoryInstantiation getCategoryInstantiation() {
        return categoryInstantiation;
    }

}
