package com.shan.datalakereader.contract;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@AllArgsConstructor
public class DataLoadJobException extends RuntimeException {

    DataLoadJob dataLoadJob;
}
