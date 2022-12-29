package com.example.datajpa.controller;

import com.example.datajpa.entity.Expense;
import com.example.datajpa.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* ExpenseService의 rest api 컨트롤러
* */
@RestController
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;


//    {{url}}/expenses?size=1&page=1
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        int number =1;
        calculateFactorial(number);
        return expenseService.getAllExpenses(page).toList();
    }
//    @GetMapping("/expenses")
//    public List<Expense> getAllExpenses(){
//        return expenseService.getAllExpenses();
//    }
//    @PathVariable - uri에 변수가 들어오는 경우
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id)
    {
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam Long id){
            expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@RequestBody Expense expense)
    {
        return expenseService.saveExpenseDetails(expense);
    }
    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense,
                                        @PathVariable Long id)
    {
        return expenseService.updateExpenseDetails(id, expense);
    }
    public int calculateFactorial(int number){
        return number * calculateFactorial(number-1);
    }








}
