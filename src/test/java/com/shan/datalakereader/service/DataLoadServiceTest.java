package com.shan.datalakereader.service;

import com.shan.datalakereader.BaseTest;
import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;
import com.shan.datalakereader.repository.BigQueryRepository;
import com.shan.datalakereader.repository.DataLakeRepositoryFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataLoadServiceTest extends BaseTest {

    @Mock
    DataLakeRepositoryFactory dataLakeRepositoryFactoryMock;

    @Mock
    BigQueryRepository bigQueryRepositoryMock;

    DataLoadService dataLoadService;

    @Before
    public void setup(){
        dataLoadService = new DataLoadService(dataLakeRepositoryFactoryMock);
    }

    @Test
    public  void executeJob_Success() throws Exception {


        //prepare
        DataLoadJob mockJob = podamFactory.manufacturePojo(DataLoadJob.class);
        DataLoadResponse mockResponse = podamFactory.manufacturePojo(DataLoadResponse.class);

        when(dataLakeRepositoryFactoryMock.getDataLakeRepositoryInstance(mockJob.getDataSourceType())).thenReturn(bigQueryRepositoryMock);
        when(bigQueryRepositoryMock.extractData(any(DataLoadJob.class))).thenReturn(mockResponse);

        //execute
        DataLoadResponse response = dataLoadService.executeJob(mockJob);

        //assert
        assertNotNull(" executeJob_Success response should not be null" , response);

    }

    @Test(expected = Exception.class)
    public void executeJob_Failure() throws Exception {
        //prepare
        DataLoadJob mockJob = podamFactory.manufacturePojo(DataLoadJob.class);
        when(dataLakeRepositoryFactoryMock.getDataLakeRepositoryInstance(anyString())).thenReturn(bigQueryRepositoryMock);
        when(bigQueryRepositoryMock.extractData(any())).thenThrow(new RuntimeException("failed for Nothing"));

        //execute
        dataLoadService.executeJob(mockJob);

        //assert
        //expected will assert this
    }

    @Test
    public  void executeJob_NullValue() {
        //prepare

        //execute
        try {
            dataLoadService.executeJob(null);
        }catch (Exception e){
        //assert
            assertTrue("executeJob_NullValue - Error message should be about null", e.getMessage().equalsIgnoreCase("dataLoad Job cannot be null"));
        }

    }

}