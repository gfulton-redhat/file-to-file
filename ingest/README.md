# ingest
This is a simple example that demonstrates file to file processing using Camel DSL.

Run your Spring Boot application locally.
```shell
mvn spring-boot:run
```

Copy the file `src/test/data/single-weld.json` to the `target/input` directory.
```shell
cp src/test/data/single-weld.json target/input
```

Observer the input file being processed in your terminal console.
```text
[Camel (batch-weld-ingest) thread #0 - file://target/input/] INFO  c.redhat.examples.CamelConfiguration - Picked up batch weld file: [single-weld.json]
[Camel (batch-weld-ingest) thread #0 - file://target/input/] INFO  c.redhat.examples.CamelConfiguration - Sending weld: [{"KSQL_COL_1":"VCDM46017|+|FLC 72B","COLLECTOR_PC":"VCDM46017","WELD_ID":864435,"WELD_TIMER_MFG":"WTC","TIMER_NAME":"FLC 72B","WELD_TIMESTAMP_GMT":1615280225000,"TIMEZONE":"America/New_York","PROGRAM_NO":47,"AVG_RESISTANCE_VALUE":208,"REF_RESISTANCE_VALUE":107,"UCL_VALUE":122,"LCL_VALUE":92,"EXPULSION_TIME":0,"SPATTER_FLAG":0,"FEEDBACK_FLAG":1,"MODEL_CODE_WELDABILITY":"TYA (MDX)","AIRWELD_FLAG":0,"ASSETNUM_MAXIMO":"A131346","ASSET_DESC_MAXIMO":"Floor Comp 08 Station 72B T-Stud Weld Controller","PLANT_ID_MAXIMO":1,"DEPT_CD_MAXIMO":"WE","LINE_ID_MAXIMO":1,"ZONE_CD_MAXIMO":"A0","PROCESS_CD_MAXIMO":"FL","STATION_CD_MAXIMO":7,"ASSET_SYSTEM_CD_MAXIMO":"2B","SITE_CD":"ELP","PROCESS_DESCRIPTION":"Floor Comp","UPDATE_TIMESTAMP":1614040000000}]
```

Confirm the Camel wrote the file contents `target/output/single-weld.json
```shell
cat target/output/single-weld.json
```
