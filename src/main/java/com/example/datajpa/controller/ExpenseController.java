package com.example.datajpa.controller;

import com.example.datajpa.entity.Expense;
import com.example.datajpa.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* ExpenseService의 rest api 컨트롤러
* */
@RestController
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }
//    @PathVariable - uri에 변수가 들어오는 경우
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id)
    {
        return expenseService.getExpenseById(id);
    }
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam Long id){
            expenseService.deleteExpenseById(id);
    }

    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@RequestBody Expense expense)
    {
        return expenseService.saveExpenseDetails(expense);
    }
    @PostMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense,
                                        @PathVariable Long id)
    {
        return expenseService.updateExpenseDetails(id, expense);
    }








}
