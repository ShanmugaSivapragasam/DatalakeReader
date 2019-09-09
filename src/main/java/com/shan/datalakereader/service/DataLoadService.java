package com.shan.datalakereader.service;


import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadJobException;
import com.shan.datalakereader.contract.DataLoadResponse;
import com.shan.datalakereader.repository.DataLakeRepository;
import com.shan.datalakereader.repository.DataLakeRepositoryFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataLoadService {

    @Autowired
    DataLakeRepositoryFactory dataLakeRepositoryFactory;



    public DataLoadResponse executeJob(DataLoadJob dataLoadJob) throws Exception {
        if(dataLoadJob == null) {
            throw new Exception("dataLoad Job cannot be null");
        }
        DataLakeRepository dataLakeRepository = dataLakeRepositoryFactory.getDataLakeRepositoryInstance(dataLoadJob.getDataSourceType());
        return  dataLakeRepository.extractData(dataLoadJob);
    }

}
