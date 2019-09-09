package com.shan.datalakereader.controller;


import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadJobException;
import com.shan.datalakereader.contract.DataLoadResponse;
import com.shan.datalakereader.service.DataLoadService;
import com.shan.datalakereader.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataLoadController {

    @Autowired
    DataLoadService dataLoadService;

    @PostMapping("/gcs/customers")
    private ResponseEntity<DataLoadResponse> loadCustomersToGCS(@RequestBody DataLoadJob dataLoadJob) throws Exception {
        String startTime = DateTimeUtil.getCurrentLocaTimeInHypenMilliSeconds();
        DataLoadResponse dataLoadResponse =   dataLoadService.executeJob(dataLoadJob);
        if(dataLoadResponse!=null && dataLoadResponse.isSuccess()){
            return  new ResponseEntity<>(dataLoadResponse, HttpStatus.CREATED);
        }
        else
        {
            throw new DataLoadJobException(dataLoadJob);
        }

    }

}
