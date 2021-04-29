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

    /**
     * 1. Set the environment variable GOOGLE_APPLICATION_CREDENTIALS to the path of the JSON file that contains your service account key
     * https://cloud.google.com/bigquery/docs/quickstarts/quickstart-client-libraries
     */
    private static BigQuery getBigQuery() {
        return BigQueryOptions.getDefaultInstance().getService();
    }
}
/**
 1
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=updated}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=602.234}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}]}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}]}]}
 FieldValue{attribute=PRIMITIVE, value=updated}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=602.234}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}
 FieldValue{attribute=PRIMITIVE, value=str}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=10.5}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}

 2
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=updated}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=602.234}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}]}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}]}]}
 FieldValue{attribute=PRIMITIVE, value=updated}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=602.234}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}
 FieldValue{attribute=PRIMITIVE, value=str}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=10.5}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}

 3
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=updated}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=602.234}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}]}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}]}]}
 FieldValue{attribute=PRIMITIVE, value=updated}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=602.234}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}
 FieldValue{attribute=PRIMITIVE, value=str}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=10.5}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}

 4
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=updated}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=602.234}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}]}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}]}]}
 FieldValue{attribute=PRIMITIVE, value=updated}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=602.234}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}
 FieldValue{attribute=PRIMITIVE, value=str}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=10.5}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}

 5
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=updated}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=602.234}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}]}, FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}]}]}
 FieldValue{attribute=PRIMITIVE, value=updated}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=602.234}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=PRIMITIVE, value=str}, FieldValue{attribute=PRIMITIVE, value=10}, FieldValue{attribute=PRIMITIVE, value=10.5}, FieldValue{attribute=PRIMITIVE, value=2014-08-18}, FieldValue{attribute=PRIMITIVE, value=07:36:33}, FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}, FieldValue{attribute=PRIMITIVE, value=1408432295.0}, FieldValue{attribute=PRIMITIVE, value=false}]}
 FieldValue{attribute=PRIMITIVE, value=str}
 FieldValue{attribute=PRIMITIVE, value=10}
 FieldValue{attribute=PRIMITIVE, value=10.5}
 FieldValue{attribute=PRIMITIVE, value=2014-08-18}
 FieldValue{attribute=PRIMITIVE, value=07:36:33}
 FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}
 FieldValue{attribute=PRIMITIVE, value=1408432295.0}
 FieldValue{attribute=PRIMITIVE, value=false}
 Printing record FieldValue{attribute=RECORD, value=[FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=str}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=10.5}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2014-08-18}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=2016-11-15T07:36:33}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=1408432295.0}]}, FieldValue{attribute=REPEATED, value=[FieldValue{attribute=PRIMITIVE, value=false}]}]}

 */
