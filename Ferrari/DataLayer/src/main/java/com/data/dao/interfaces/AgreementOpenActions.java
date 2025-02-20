package com.data.dao.interfaces;

import com.data.actions.general.Create;
import com.data.actions.general.Delete;
import com.data.actions.general.Read;
import com.data.actions.general.ReadAll;
import com.data.actions.general.Update;
import com.model.entities.Agreement;

import java.util.List;

public interface AgreementOpenActions extends Read<Agreement, Integer>, ReadAll<List<Agreement>, Void>, Delete<Boolean, Agreement>, 
    Create<Agreement, Agreement>, Update<Boolean, Agreement>{
}
