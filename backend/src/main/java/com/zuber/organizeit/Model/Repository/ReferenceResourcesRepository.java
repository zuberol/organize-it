package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.Note.ReferenceResource.ReferenceResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceResourcesRepository extends JpaRepository<ReferenceResource, Long> {

    
}
