package com.eshc.backend.respositories;

import com.eshc.backend.models.ActionPoint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionPointRepository extends PagingAndSortingRepository<ActionPoint,Long> {

}
