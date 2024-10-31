package ru.leonid.labs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.leonid.labs.entity.MathFunctionEntity;

import java.util.List;

@Repository
public interface MathFunctionRepository extends CrudRepository<MathFunctionEntity, Long> {
    List<MathFunctionEntity> findByName(String functionName);
}
