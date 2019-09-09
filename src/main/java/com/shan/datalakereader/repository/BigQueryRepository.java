package com.shan.datalakereader.repository;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import com.shan.datalakereader.contract.DataLoadJob;
import com.shan.datalakereader.contract.DataLoadResponse;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Repository
@Log
public class BigQueryRepository extends DataLakeRepository {


    private static final String DEFAULT_QUERY = "SELECT NBR, CREATE_TS, NAME FROM  demo.sample";


    public DataLoadResponse extractData(DataLoadJob dataLoadJob) throws InterruptedException, IOException {


        String keyFilePath = Paths.get("").toAbsolutePath().toString() + "/src/main/java/com/shan/datalakereader/security/";
//        String routePublishJson = new String(Files.readAllBytes(Paths.get(keyFilePath + "RoutePublish_Response.json")));

        BigQuery bigQuery = BigQueryOptions.newBuilder()
                                .setProjectId("shan-demos") //TODO move to env var
                                .setCredentials(
                                        ServiceAccountCredentials.fromStream(
                                                new FileInputStream(new File(keyFilePath + "key-orig.json"))
                                        )
                                )
                                .build()
                .getService();
        String query = dataLoadJob.getQuery();
        if (query == null) {
            query = DEFAULT_QUERY;
        }
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).build();

        TableResult allRows = bigQuery.query(queryJobConfiguration);
        allRows.iterateAll().forEach( row ->  {

            row.forEach(field -> {
                log.info(String.valueOf(field.getValue() )+ ",");
            });
            log.info(" \n");
        });
        DataLoadResponse dataLoadResponse = new DataLoadResponse();
        dataLoadResponse.setSuccess(true);
        return dataLoadResponse;
    }


}
