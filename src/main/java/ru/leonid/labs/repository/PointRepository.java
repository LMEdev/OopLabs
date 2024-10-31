package ru.leonid.labs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.leonid.labs.entity.PointEntity;

@Repository
public interface PointRepository extends CrudRepository<PointEntity, Long> {
}
