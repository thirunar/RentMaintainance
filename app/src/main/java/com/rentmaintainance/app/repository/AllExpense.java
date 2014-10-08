package com.rentmaintainance.app.repository;

import com.rentmaintainance.app.model.Expense;

public class AllExpense {

    private ExpenseRepository expenseRepository;

    public AllExpense(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public long addExpense(Expense expense) {
        return expenseRepository.addExpense(expense);
    }
}
