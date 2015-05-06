package com.rentmaintainance.app.repository;

import android.util.SparseArray;
import com.rentmaintainance.app.model.Expense;

import java.util.List;

public class AllExpense {

    private ExpenseRepository expenseRepository;

    public AllExpense(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public long addExpense(Expense expense) {
        return expenseRepository.addExpense(expense);
    }

    public List<Expense> getAllExpense() {
        return expenseRepository.getAllExpense();
    }

    public SparseArray<SparseArray<String>> getAllExpenseData() {
        return expenseRepository.getAllExpenseData();
    }

}
