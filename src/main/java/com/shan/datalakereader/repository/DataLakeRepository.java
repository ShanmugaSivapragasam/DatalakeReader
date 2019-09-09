package com.shan.datalakereader.repository;

import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;

public abstract class  DataLakeRepository {

    public abstract DataLoadResponse extractData(DataLoadJob dataLoadJob) throws Exception;

}
