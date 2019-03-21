package com.eshc.backend.respositories;

import com.eshc.backend.models.WorkingGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingGroupRepository extends PagingAndSortingRepository<WorkingGroup, Long> {
}
