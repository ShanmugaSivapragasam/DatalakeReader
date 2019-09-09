package com.shan.datalakereader;

import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;
import com.shan.datalakereader.util.JSONHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static com.shan.datalakereader.repository.DataLakeRepositoryFactory.BIG_QUERY;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatalakereaderApplicationTests extends  BaseTest{

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void loadDataToGCS_Success_Response() throws Exception{

		//prepare
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_JSON);
		DataLoadJob dataLoadJob = podamFactory.manufacturePojo(DataLoadJob.class);
		dataLoadJob.setDataSourceType(BIG_QUERY);
		dataLoadJob.setQuery(null);
		String dataLoadJobJson = JSONHelper.toJson(dataLoadJob);
		HttpEntity<String> dataLoadJobRequestEntity = new HttpEntity<>(dataLoadJobJson, headers);
		ResponseEntity<DataLoadResponse> responseEntity;

		//call
		responseEntity = this.restTemplate.exchange("/gcs/customers", HttpMethod.POST,dataLoadJobRequestEntity,  DataLoadResponse.class);

		//assert
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody() );
		assertTrue( responseEntity.getBody().isSuccess());

	}

}
