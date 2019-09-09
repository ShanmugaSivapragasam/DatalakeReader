package com.shan.datalakereader.repository;

import org.springframework.stereotype.Component;

@Component
public class DataLakeRepositoryFactory {

    public static final String BIG_QUERY = "BQ";

    public  DataLakeRepository getDataLakeRepositoryInstance(String dataSourceType) {
        DataLakeRepository dataLakeRepository = null;
        if (BIG_QUERY.equalsIgnoreCase(dataSourceType)){
             dataLakeRepository = new BigQueryRepository();
        }

        return  dataLakeRepository;
    }



}
