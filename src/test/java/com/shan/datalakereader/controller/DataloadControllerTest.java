package com.shan.datalakereader.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.datalakereader.BaseTest;
import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;
import com.shan.datalakereader.service.DataLoadService;
import com.shan.datalakereader.util.JSONHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DataloadControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataLoadService dataLoadService;



    @Test
    public  void DataloadControllerJob_SuccessResponse() throws Exception {
        //prepare
        DataLoadJob mockJob = podamFactory.manufacturePojo(DataLoadJob.class);
        DataLoadResponse mockResponse = podamFactory.manufacturePojo(DataLoadResponse.class);
        mockResponse.setSuccess(true);

        //mock conditions
        when(dataLoadService.executeJob(any(DataLoadJob.class))).thenReturn(mockResponse);

        //execute
        MvcResult mvcResult =  mockMvc.perform(post("/gcs/customers")
                .content(JSONHelper.toJson(mockJob))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
//               .andExpect(jsonPath("isSuccess").value(true));
        String responseString = mvcResult.getResponse().getContentAsString();
        DataLoadResponse response = new ObjectMapper().readValue(responseString, DataLoadResponse.class);

        //assert
        assertTrue("response is successful", response.isSuccess());

    }

    @Test
    public  void DataLoadControllerJob_Failure() throws Exception {
        //prepare
        DataLoadJob mockJob = podamFactory.manufacturePojo(DataLoadJob.class);


        //execute
        MvcResult mvcResult = mockMvc.perform(post("/gcs/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSONHelper.toJson(mockJob))
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().is5xxServerError())
                                .andReturn();

        //assert
        assertTrue("DataLoadControllerJob_Failure Response should be empty ", mvcResult.getResponse().getContentAsString().isEmpty());

    }


}
