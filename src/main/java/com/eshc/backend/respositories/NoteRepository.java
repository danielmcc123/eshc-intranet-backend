package com.eshc.backend.respositories;

import com.eshc.backend.models.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface NoteRepository extends PagingAndSortingRepository<Note,Long> {

    List<Note> findByIdIn(Collection<Long> ids, Pageable pageable);
}
