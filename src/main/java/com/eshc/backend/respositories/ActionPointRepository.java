package com.eshc.backend.respositories;

import com.eshc.backend.models.ActionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionPointRepository extends JpaRepository<ActionPoint,Long> {

}
