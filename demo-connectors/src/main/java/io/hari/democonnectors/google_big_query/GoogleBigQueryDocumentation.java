package io.hari.democonnectors.google_big_query;

import com.google.cloud.bigquery.*;
import lombok.SneakyThrows;

import java.util.UUID;

/**
 * @Author hayadav
 * @create 4/29/2021
 */
public class GoogleBigQueryDocumentation {

    public static final String QUERY_SELECT_ALL = "SELECT * FROM `bigquery-public-data.austin_311.311_service_requests` LIMIT 100";//290+mb, on all columns
    public static final String QUERY_SELECT_1COLUMN = "SELECT source FROM `bigquery-public-data.austin_311.311_service_requests` LIMIT 50";//8mb : on column
    public static final String QUERY_SELECT_2COLUMN = "SELECT source, status FROM `bigquery-public-data.austin_311.311_service_requests` LIMIT 50";
    public static final String QUERY_RECORD_TYPE = "SELECT Master1_Nullable FROM `api-project-80697026669.0_EU_Region.MainTable` LIMIT 5";

    public static void main(String[] args) {
        final BigQuery bigQuery = getBigQuery();
        executeQuery_getFlatRows_AndRecordType(bigQuery, QUERY_SELECT_2COLUMN);
        executeQuery_getFlatRows_AndRecordType(bigQuery, QUERY_RECORD_TYPE);

    }

    public void browseTable() {

    }

    @SneakyThrows
    private static void executeQuery_getFlatRows_AndRecordType(BigQuery bigQuery, String query) {
        final QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query)
                .setUseLegacySql(false).build();

        //create a job id, job info obj -> then run job info obj
        final JobId jobId = JobId.of(UUID.randomUUID().toString());
        final JobInfo jobInfo = JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build();
        Job job = bigQuery.create(jobInfo);//create has 3 methods, Job option, Table option, dataset option

        //wait for query to complete
        job = job.waitFor();//used callable inside
        Thread.sleep(3000);
        System.out.println("job.getStatus() = " + job.getStatus());

        // Check for errors
        if (job == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (job.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(job.getStatus().getError().toString());
        }
        // Displaying the query result
        final TableResult tableResult = job.getQueryResults();
        final Iterable<FieldValueList> lists = tableResult.iterateAll();
        int rowNumber = 0;
        for (FieldValueList fieldValues : lists) {
            rowNumber++;
            for (int i = 0; i < fieldValues.size(); i++) {
                System.out.println(rowNumber);

                final FieldValue fieldValue = fieldValues.get(i);
                if (fieldValue.getAttribute().equals(FieldValue.Attribute.PRIMITIVE)) {
                    primitivePrint(fieldValue);
                } else if (fieldValue.getAttribute().equals(FieldValue.Attribute.RECORD)) {
                    recordPrint(fieldValue);
                }
            }
            System.out.println();
        }
    }

    private static void recordPrint(FieldValue fieldValue) {
        System.out.println("Printing record "+fieldValue);
        final FieldValueList recordValue = fieldValue.getRecordValue();
        for (int j = 0; j < recordValue.size(); j++) {
            final FieldValue fieldValue1 = recordValue.get(j);
            if (fieldValue1.getAttribute().equals(FieldValue.Attribute.PRIMITIVE))
                primitivePrint(fieldValue1);
            else if (fieldValue1.getAttribute().equals(FieldValue.Attribute.RECORD))
                recordPrint(fieldValue1);
        }
    }

    private static void primitivePrint(FieldValue fieldValue) {
        System.out.println(fieldValue);
    }

    private static BigQuery getBigQuery() {
        return BigQueryOptions.getDefaultInstance().getService();
    }
}
