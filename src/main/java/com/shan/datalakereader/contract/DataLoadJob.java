package com.shan.datalakereader.contract;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value =  JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class DataLoadJob {
    Long id;
    String jobDate;
    String dataSourceType;
    String query;


}
