# ingest
This is a simple example that demonstrates file to file processing using Camel DSL.

Run your Spring Boot application locally.
```shell
mvn spring-boot:run
```

Copy the file `src/test/data/purchase-order.xml` to the `target/input` directory.
```shell
cp src/test/data/purchase-order.xml target/input
```

In [PurchaseOrderRoute.java](src/main/java/com/redhat/examples/PurchaseOrderRoute.java) we configure a Route to read the purchase order and process the ship to and bill to address information.

Confirm the Camel wrote the file contents `target/output/single-weld.json
```shell
cat target/output/purchase-order.xml
```
