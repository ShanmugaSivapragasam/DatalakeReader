package com.shan.datalakereader.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataLoadResponse {
    Long id;
    String startTime;
    String endTime;
    boolean isSuccess;


}
