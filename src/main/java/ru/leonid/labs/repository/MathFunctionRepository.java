package ru.leonid.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leonid.labs.entity.MathFunction;

import java.util.Optional;

@Repository
public interface MathFunctionRepository extends JpaRepository<MathFunction, Long> {
    Optional<MathFunction> findByFunctionType(String functionType);
}