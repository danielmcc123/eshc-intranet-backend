package com.eshc.backend.respositories;

import com.eshc.backend.models.ActionPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ActionPointRepository extends PagingAndSortingRepository<ActionPoint,Long> {

    List<ActionPoint> findByIdIn(Collection<Long> ids, Pageable pageable);

}
