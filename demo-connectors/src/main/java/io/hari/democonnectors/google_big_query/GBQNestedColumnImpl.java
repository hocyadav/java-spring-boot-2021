package io.hari.democonnectors.google_big_query;

import com.google.cloud.bigquery.*;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.hari.democonnectors.google_big_query.UtilityHelper.getCurrentTimeAsSuffix;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
public class GBQNestedColumnImpl {
    public static final String DATASET_NAME = "hariom_dataset";
    public static final String TABLE_NAME = "nested_table";
    public static final String DOT = ".";
    public static final BigQuery BIG_QUERY = BigQueryOptions.getDefaultInstance().getService();
    public static final String UNDERSCORE = "_";


    @Test //todo : create nested column
    public void createTableWithNestedRepeatedSchemaTest() {
        final Schema schema = getSchema();
        createTableWithNestedRepeatedSchema(DATASET_NAME, TABLE_NAME, schema);
    }

    private void createTableWithNestedRepeatedSchema(String datasetName, String tableName, Schema schema) {
        //step 1 : create table info obj -> required 2 things : table id + table definitions
        final TableId tableId = TableId.of(datasetName, tableName);
        final StandardTableDefinition tableDefinition = StandardTableDefinition.of(schema);//table definition required schema
        final TableInfo tableInfo = TableInfo.of(tableId, tableDefinition);

        //step 2 : BQ instance + call create()
        BIG_QUERY.create(tableInfo);

        System.out.println("Table with nested field created successful !!");
    }

    @Test //todo : fetch data from nested column or from any table
    public void fetchDataFromNestedTableSQLTest() {
        String sql1 = "select * from " + DATASET_NAME + DOT + TABLE_NAME;
        String sql2 = "select * from hariom_dataset.source_table t limit 50";
        fetchDataFromNestedTableSQL(sql1);
    }

    @SneakyThrows
    private void fetchDataFromNestedTableSQL(String sql) {
        JobId jobId = JobId.newBuilder().setRandomJob().build();
        JobConfiguration jobConfig = QueryJobConfiguration.newBuilder(sql).setUseLegacySql(false).build();
        final JobInfo jobInfo = JobInfo.of(jobId, jobConfig);

        Job job = BIG_QUERY.create(jobInfo);
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
    public void addEmptyColumnTest() {
        String newColumnName = "new_column" + getCurrentTimeAsSuffix();
        LegacySQLTypeName newColumnType = LegacySQLTypeName.STRING;
        addEmptyColumn(newColumnName, newColumnType);
    }

    private void addEmptyColumn(String newColumnName, LegacySQLTypeName newColumnType) {
        //step 1: get table object -> get schema object -> get field object -> create new field and add it
        final Table oldTable = BIG_QUERY.getTable(DATASET_NAME, TABLE_NAME);
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

    @Test
    public void addRecordTypeColumnTest() {
        String newColumnName = "new_column_record" + getCurrentTimeAsSuffix();
        StandardSQLTypeName newColumnType = StandardSQLTypeName.STRUCT;
        addRecordTypeColumn(newColumnName, newColumnType);
    }

    //1 : get table schema -> old fields -> create new field -> create a new schema -> update table
    private void addRecordTypeColumn(String newColumnName, StandardSQLTypeName newColumnType) {
        final Table oldTable = BIG_QUERY.getTable(DATASET_NAME, TABLE_NAME);
        final Schema oldSchema = oldTable.getDefinition().getSchema();
        final List<Field> oldFields = oldSchema.getFields().stream().collect(Collectors.toList());

        final Field newFieldRecordType = recordTypeField(newColumnName, newColumnType);
        oldFields.add(newFieldRecordType);

        final Schema newSchema = Schema.of(oldFields);
        TableDefinition tableDefinition = StandardTableDefinition.of(newSchema);
        final Table newTable = oldTable.toBuilder()
                .setDefinition(tableDefinition)
                .build();
        newTable.update();
        System.out.println("table updated successfully !!");
    }

    public Field recordTypeField(String newColumnName, StandardSQLTypeName newColumnType) {
        final Field.Builder fieldBuilder = Field.newBuilder(newColumnName, newColumnType,
                Field.of("address", LegacySQLTypeName.STRING),
                Field.of("city", LegacySQLTypeName.STRING));
        return fieldBuilder.setMode(Field.Mode.REPEATED).build();
    }

    @Test //todo : not working -> GBQ support manual delete -> ALTER TABLE DROP COLUMN
    public void deleteFieldTest() {
        deleteFieldUsingJAVA("new_column");
    }

    public void deleteFieldUsingJAVA(String columnName) {
        final Table oldTable = BIG_QUERY.getTable(DATASET_NAME, TABLE_NAME);
        final Schema oldSchema = oldTable.getDefinition().getSchema();
        final List<Field> oldFieldsWithRemovedInputField = oldSchema.getFields().stream()
                .filter(field -> !field.getName().equalsIgnoreCase(columnName))
                .collect(Collectors.toList());

        final Schema newSchema = Schema.of(oldFieldsWithRemovedInputField);
        TableDefinition tableDefinition = StandardTableDefinition.of(newSchema);
        final Table newTable = oldTable.toBuilder()
                .setDefinition(tableDefinition)
                .build();
        newTable.update();
        System.out.println("Field deleted successfully");
    }

    public void deleteColumnUsingSQL(String columnName) {

    }

    @Test
    public void fetchSchemaTest() {
        fetchSchema(DATASET_NAME, TABLE_NAME);
    }

    private void fetchSchema(String datasetName, String tableName) {
        final Table table = BIG_QUERY.getTable(datasetName, tableName);
        final Schema schema = table.getDefinition().getSchema();
        System.out.println("schema = " + schema);
        final FieldList fields = schema.getFields();
        final Iterator<Field> allColumns = fields.iterator();
        Schema schema1 = Schema.of(fields);
        while (allColumns.hasNext()) {
            final Field field = allColumns.next();
            System.out.println("field = " + field);
        }
    }

    @Test
    public void createReplicaTableWithOldSchemaTest() {
        final Table table = BIG_QUERY.getTable(DATASET_NAME, TABLE_NAME);
        final Schema schema = table.getDefinition().getSchema();
        createReplicaTableWithOldSchema(schema);
    }

    private void createReplicaTableWithOldSchema(Schema schema) {
        final Schema newSchema = Schema.of(schema.getFields());
        final String newTableName = TABLE_NAME + "_01_new_table";
        createTableWithNestedRepeatedSchema(DATASET_NAME, newTableName, newSchema);
    }

    @Test // todo testing pending
    public void createTableWithOldSchemaButRemoveFewRecordTypeFieldTest() {
        createTableWithOldSchemaButRemoveFewRecordTypeField();
    }

    public void createTableWithOldSchemaButRemoveFewRecordTypeField() {
        final Table table = BIG_QUERY.getTable(DATASET_NAME, TABLE_NAME);
        final Schema schema = table.getDefinition().getSchema();
        final Schema newSchema = Schema.of(schema.getFields());
        final FieldList fields = newSchema.getFields();
        final List<Field> fieldList = fields.stream().collect(Collectors.toList());
        System.out.println("fieldList = " + fieldList);
        for (Field field : fieldList) {
            if (Objects.nonNull(field.getSubFields()) && field.getSubFields().size() > 0) {
                final FieldList subFields = field.getSubFields();
                System.out.println("subFields = " + subFields);
                final Iterator<Field> iterator = subFields.iterator();//we cant get field list object - class is final and only we can get iterator object of inside list, but we can apply on stream
                List<Field> newList = new LinkedList<>();
                subFields.stream().forEach(fetchedField -> {
                    if (!fetchedField.getName().equalsIgnoreCase("zip")) {
                        newList.add(fetchedField);
                    }
                });
                System.out.println("newList = " + newList);
            }
        }
    }
}
