package com.samdcc.chaosmedia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "category_instantiations")
@Access(AccessType.FIELD)
public class CategoryInstantiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(name = "media_instantiation_to_category_instantiation", joinColumns = @JoinColumn(name = "category_instantiation_id"), inverseJoinColumns = @JoinColumn(name = "media_instantiation_id"))
    private List<MediaInstantiationPreview> mediaInstantiationPreviews;

    @ManyToMany
    @JoinTable(name = "category_instantiation_to_category_instantiation", joinColumns = @JoinColumn(name = "parent_category_instantiation_id"), inverseJoinColumns = @JoinColumn(name = "child_category_instantiation_id"))
    private List<CategoryInstantiation> categoryInstantiations;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Category getCategory() {
        return category;
    }

    public List<MediaInstantiationPreview> getMediaInstantiationPreviews() {
        return mediaInstantiationPreviews;
    }

    public List<CategoryInstantiation> getCategoryInstantiations() {
        return categoryInstantiations;
    }

}
