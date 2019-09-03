package com.shan.datalakereader.controller;


import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;
import com.shan.datalakereader.util.DateTimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataLoadController {

    @PostMapping("/gcs/customers")
    private ResponseEntity<DataLoadResponse> loadCustomersToGCS(@RequestBody DataLoadJob dataLoadJob){
        String startTime = DateTimeUtil.getCurrentLocaTimeInHypenMilliSeconds();
        DataLoadResponse response = new DataLoadResponse();
        response.setStartTime(startTime);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
