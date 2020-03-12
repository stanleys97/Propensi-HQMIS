package com.project.propensib8.service;

import com.project.propensib8.model.UnitParameterModel;
import com.project.propensib8.repository.UnitParameterDB;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
public class UnitParameterServiceImpl implements UnitParameterService{

    @Autowired
    UnitParameterDB unitParameterDb;

    @Override
    public List<UnitParameterModel> findAllParameterInUnit(String namaUnit) {
        List<UnitParameterModel> listOfParameter = new ArrayList<>();
        for(UnitParameterModel unitParameter : unitParameterDb.findAll()){
            if(unitParameter.getUnit().getNama().equalsIgnoreCase(namaUnit)){
                listOfParameter.add(unitParameter);
            }
        }
        return listOfParameter;
    }

}
