package com.rentmaintainance.app.repository;

import android.util.SparseArray;
import com.rentmaintainance.app.model.Income;

import java.util.List;

public class AllIncome {

    private IncomeRepository incomeRepository;

    public AllIncome(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public long addIncomce(Income income) {
        return incomeRepository.addIncome(income);
    }

    public List<Income> getAllIncome() {
        return incomeRepository.getAllIncome();
    }

    public SparseArray<SparseArray<String>> getAllIncomeData() {
        return incomeRepository.getAllIncomeData();
    }

}
