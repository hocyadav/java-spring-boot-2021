package io.hari.democonnectors.google_big_query;

import com.google.cloud.bigquery.*;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
public class GBQNestedColumnImpl {
    public static final String DATASET_NAME = "hariom_dataset";
    public static final String TABLE_NAME = "nested_table";
    public static final String DOT = ".";


    @Test //todo : create nested column
    public void nestedColumn() {
        createTableWithNestedRepeatedSchema(DATASET_NAME, TABLE_NAME);
    }

    private void createTableWithNestedRepeatedSchema(String datasetName, String tableName) {
        //step 1 : create table info obj -> required 2 things : table id + table definitions
        final TableId tableId = TableId.of(datasetName, tableName);
        final StandardTableDefinition tableDefinition = StandardTableDefinition.of(getSchema());//table definition required schema
        final TableInfo tableInfo = TableInfo.of(tableId, tableDefinition);

        //step 2 : BQ instance + call create()
        final BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
        bigQuery.create(tableInfo);

        System.out.println("Table with nested field created successful !!");
    }

    @Test //todo : fetch data from nested column or from any table
    public void fetchDataFromNestedTable() {
        String sql1 = "select * from " + DATASET_NAME + DOT + TABLE_NAME;
        String sql2 = "select * from hariom_dataset.source_table t limit 50";
        fetchDataFromNestedTableSQL(sql1);
    }

    @SneakyThrows
    private void fetchDataFromNestedTableSQL(String sql) {
        JobId jobId = JobId.newBuilder().setRandomJob().build();
        JobConfiguration jobConfig = QueryJobConfiguration.newBuilder(sql).setUseLegacySql(false).build();
        final JobInfo jobInfo = JobInfo.of(jobId, jobConfig);

        final BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
        final BigQueryOptions.Builder builder = BigQueryOptions.newBuilder();
        Job job = bigQuery.create(jobInfo);
        job = job.waitFor();
        System.out.println("job.getStatus() = " + job.getStatus());
        printTableDataFromJob(job);
    }

    private void printTableDataFromJob(Job job) throws InterruptedException {
        final TableResult queryResults = job.getQueryResults();
        final Iterable<FieldValueList> fieldValueLists = queryResults.iterateAll();
        for (FieldValueList fieldValues : fieldValueLists) {
            System.out.println("fieldValues = " + fieldValues);
        }
    }

    private Schema getSchema() {//required field name and type
        final Field addressNestedField = getNestedRepeatedField();
        Schema schema =
                Schema.of(
                        Field.of("id", LegacySQLTypeName.STRING),
                        Field.of("first_name", LegacySQLTypeName.STRING),
                        Field.of("dob", LegacySQLTypeName.DATE),
                        addressNestedField);
        return schema;
    }

    private Field getNestedRepeatedField() {//required field name and type
        final Field field =
                Field.newBuilder(
                        "addresses",
                        StandardSQLTypeName.STRUCT,
                        Field.of("address", LegacySQLTypeName.STRING),
                        Field.of("city", LegacySQLTypeName.STRING),
                        Field.of("zip", LegacySQLTypeName.INTEGER))
                        .setMode(Field.Mode.REPEATED)
                        .build();
        return field;
    }

    @Test //todo: add new column
    public void updateColumnCRUD() {
        String newColumnName = "new_column";
        LegacySQLTypeName newColumnType = LegacySQLTypeName.STRING;
        addEmptyColumn(DATASET_NAME, TABLE_NAME, newColumnName, newColumnType);
    }

    private void addEmptyColumn(String datasetName, String tableName, String newColumnName, LegacySQLTypeName newColumnType) {
        //step 1: get table object -> get schema object -> get field object -> create new field and add it
        final BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
        final Table oldTable = bigQuery.getTable(datasetName, tableName);
        final Schema schema = oldTable.getDefinition().getSchema();
        final FieldList oldSchemaField = schema.getFields();

        //step 2 : create new schema = old fields + new field
        final List<Field> oldFields = oldSchemaField.stream().collect(Collectors.toList());
        final Field newField = Field.of(newColumnName, newColumnType);
        oldFields.add(newField);

        final Schema newSchema = Schema.of(oldFields);

        //step 3: create table object and call update()
        //approach : convert oldTable obj to builder obj and set new schema
        TableDefinition newTableDefinition = StandardTableDefinition.of(newSchema);
        final Table newTable = oldTable.toBuilder()
                .setDefinition(newTableDefinition)
                .build();
        newTable.update();

        System.out.println("empty column added successfully !!");
    }
}
