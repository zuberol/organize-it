package com.zuber.organizeit.Model.Repository;

import com.zuber.organizeit.Model.ReferenceResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceResourcesRepository extends JpaRepository<ReferenceResource, Long> {

    @Query(value = "SELECT nextval('" + ReferenceResource.ID_SEQ_NAME + "')", nativeQuery = true)
    Long getIdFromSeq();
    
}
