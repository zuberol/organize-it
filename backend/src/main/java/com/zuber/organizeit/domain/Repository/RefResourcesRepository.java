package com.zuber.organizeit.domain.Repository;

import com.zuber.organizeit.domain.ReferenceResource.ReferenceResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefResourcesRepository extends JpaRepository<ReferenceResource, Long> {

    
}
