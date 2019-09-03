package com.shan.datalakereader.contract;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@JsonInclude(value =  JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class DataLoadJob {
    Long id;
    String jobDate;

}
