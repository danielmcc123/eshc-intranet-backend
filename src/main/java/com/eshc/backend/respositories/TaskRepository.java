package com.eshc.backend.respositories;

import com.eshc.backend.models.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    List<Task> findByIdIn(Collection<Long> ids, Pageable pageable);
}
