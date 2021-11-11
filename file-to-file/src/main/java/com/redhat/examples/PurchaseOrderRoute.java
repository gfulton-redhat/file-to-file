/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.redhat.examples;

import com.redhat.examples.model.PurchaseOrderType;
import com.redhat.examples.model.USAddress;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;


@Component
public class PurchaseOrderRoute extends RouteBuilder {

    private static final Logger log = LoggerFactory.getLogger(PurchaseOrderRoute.class);

    @Autowired
    private ApplicationProperties properties;

    @Override
    public void configure() throws Exception {
        // Create a new XML handler using JAXB
        JaxbDataFormat xmlDataFormat = new JaxbDataFormat(true);

        // Set the context to point to the generated classes
        xmlDataFormat.setContextPath(PurchaseOrderType.class.getPackage().getName());

        // Point to the purchase order schema
        xmlDataFormat.setSchema("classpath:purchase-order.xsd");

        // Read from the input directory
        fromF("file:%s?delete=true&readLock=fileLock", properties.getInputDir())

                // Write a log messages when a new file is detected
                .log(LoggingLevel.INFO, log, "Picked up a file: [${headers.CamelFileName}]")

                // Convert the XML to Java POJO
                .unmarshal(xmlDataFormat)

                // Process the purchase order
                .process(processor())

                // Convert the Java POJO to XML
                .marshal(xmlDataFormat)

                // Write XML to a file
                .toF("file:%s", properties.getOutputDir())

                // Finish the Route
                .end();
    }

    public Processor processor() {
        return exchange -> {
            // Read in the purchase order
            PurchaseOrderType purchaseOrderType = exchange.getIn().getBody(PurchaseOrderType.class);

            // Update the bill to city
            purchaseOrderType.setBillTo(updateBillTo(purchaseOrderType.getBillTo()));

            // Update the list of ship to
            purchaseOrderType.getShipTo().stream().forEach(shipTo -> updateShipTo(shipTo));

            // Write the updated purchase order
            exchange.getIn().setBody(purchaseOrderType);
        };
    }

    public USAddress updateBillTo(USAddress billTo) {
        billTo.setState("North Carolina");
        billTo.setCity("Farmville");
        billTo.setCountry("United States");
        billTo.setStreet("504 Cameron Street");
        billTo.setZip(BigInteger.valueOf(92105));
        return billTo;
    }

    public USAddress updateShipTo(USAddress shipTo) {
        shipTo.setState("North Carolina");
        shipTo.setCity("Farmville");
        shipTo.setCountry("United States");
        shipTo.setStreet("504 Cameron Street");
        shipTo.setZip(BigInteger.valueOf(92105));
        return shipTo;
    }
}



