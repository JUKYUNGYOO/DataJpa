package com.example.datajpa.service;

import com.example.datajpa.entity.Expense;
import com.example.datajpa.entity.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
* ExpenseService를 구현함, ExpenseService 인터페이스를 상속 받음
* ExpenseRepository의 데이터를 가지고옴..
* */
@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;

//    @Override
//    public List<Expense> getAllExpenses(){
//        return expenseRepo.findAll();
//    }
    @Override
    public Page<Expense> getAllExpenses(Pageable page){
        return expenseRepo.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id){
        Optional<Expense> expense = expenseRepo.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new RuntimeException("Expense is not found for the id" + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepo.deleteById(id); 
    }
    @Override
    public Expense saveExpenseDetails(Expense expense){
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id,Expense expense){
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null?
                expense.getName():existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null?
                expense.getDescription():existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null?
                expense.getCategory():existingExpense.getCategory());
        existingExpense.setAmount(expense.getAmount() != null?
                expense.getAmount():existingExpense.getAmount());
        existingExpense.setDate(expense.getDate() != null?
                expense.getDate():existingExpense.getDate());
        return expenseRepo.save(existingExpense);

    }


}
