package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.CityActions;
import com.model.dtos.city.get.CityReadGet;
import com.model.entities.City;
//anders
public class CityData implements CityActions {

    private ConnectionData db;
    public CityData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public List<City> readAll(Void v) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Person.uspCityGetAll()}", CityReadGet::new), this::getCity);
    }
    private City getCity(CityReadGet get) {
        return new City(
            get.getZip(),
            get.getCityName()
        );
    }
}
