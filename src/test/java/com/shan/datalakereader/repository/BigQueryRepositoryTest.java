package com.shan.datalakereader.repository;

import com.shan.datalakereader.BaseTest;
import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.shan.datalakereader.repository.DataLakeRepositoryFactory.BIG_QUERY;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BigQueryRepositoryTest extends BaseTest {

    private BigQueryRepository bigQueryRepository;


    @Before
    public void setup(){
        bigQueryRepository = new BigQueryRepository();

    }

    @Test
    public  void executeDataLoadJob_Success() throws Exception {
        //prepare
        DataLoadJob dataLoadJob = podamFactory.manufacturePojo(DataLoadJob.class);
        dataLoadJob.setDataSourceType(BIG_QUERY);
        dataLoadJob.setQuery(null);

        //execute
        DataLoadResponse response =  bigQueryRepository.extractData(dataLoadJob);


        //assert
        assertNotNull("executeDataLoadJob_Success should NOT be null", response);

    }

}