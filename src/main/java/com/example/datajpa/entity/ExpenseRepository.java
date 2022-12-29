package com.example.datajpa.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    Expense save(Expense existingExpense);

    Optional<Expense> findById(Long id);
//    List<Expense> findAll();
    Page<Expense> findAll(Pageable page);

    void deleteById(Long id);
}
