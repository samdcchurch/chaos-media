package com.samdcc.chaosmedia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;

@Entity
@Table(name = "media_previews")
@Access(AccessType.FIELD)
public class MediaInstantiationPreview {

    @Id
    private Integer id;

    private String name;

    private String imagePath;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
