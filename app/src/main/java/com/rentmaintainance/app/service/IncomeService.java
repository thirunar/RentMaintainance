package com.rentmaintainance.app.service;

import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.model.Income;
import com.rentmaintainance.app.repository.AllIncome;

public class IncomeService {

    private AllIncome allIncome;
    private Context context;

    public IncomeService() {
        context = Context.getInstance();
        allIncome = context.allIncome();
    }

    public void addIncome(Income income) {
        allIncome.addIncomce(income);
    }
}
