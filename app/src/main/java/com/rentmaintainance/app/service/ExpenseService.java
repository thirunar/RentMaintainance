package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.model.Expense;
import com.rentmaintainance.app.repository.AllExpense;

public class ExpenseService {

    private AllExpense allExpense;
    private Context context;

    public ExpenseService() {
        context = Context.getInstance();
        allExpense = context.allExpense();
    }

    public void addExpense(Expense expense) {
        allExpense.addExpense(expense);
    }
}
