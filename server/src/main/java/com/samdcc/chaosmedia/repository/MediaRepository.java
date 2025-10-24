package com.samdcc.chaosmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samdcc.chaosmedia.entity.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {

}
