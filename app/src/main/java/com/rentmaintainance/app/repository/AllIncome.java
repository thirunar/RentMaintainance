package com.rentmaintainance.app.repository;

import com.rentmaintainance.app.model.Income;

public class AllIncome {

    private IncomeRepository incomeRepository;

    public AllIncome(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public long addIncomce(Income income) {
        return incomeRepository.addIncome(income);
    }
}
