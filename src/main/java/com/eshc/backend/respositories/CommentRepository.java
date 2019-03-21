package com.eshc.backend.respositories;

import com.eshc.backend.models.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {

    List<Comment> findByIdIn(Collection<Long> ids, Pageable pageable);
}
