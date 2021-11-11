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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private static String XML_STREAM_XML_INPUT_FACTORY = "xml.stream.XMLInputFactory";
    private static String XML_STREAM_XML_OUTPUT_FACTORY = "xml.stream.XMLOutputFactory";
    private static String XML_STREAM_XML_EVENT_FACTORY = "xml.stream.XMLEventFactory";
    private String inputDir;
    private String outputDir;
    private String xmlInputFactory;
    private String xmlOutputFactory;
    private String xmlEventFactory;

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String dir) {
        this.inputDir = dir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String dir) {
        this.outputDir = dir;
    }

    public String getXmlInputFactory() {
        return xmlInputFactory;
    }

    public void setXmlInputFactory(String xmlInputFactory) {
        this.xmlInputFactory = xmlInputFactory;
    }

    public String getXmlOutputFactory() {
        return xmlOutputFactory;
    }

    public void setXmlOutputFactory(String xmlOutputFactory) {
        this.xmlOutputFactory = xmlOutputFactory;
    }

    public String getXmlEventFactory() {
        return xmlEventFactory;
    }

    public void setXmlEventFactory(String xmlEventFactory) {
        this.xmlEventFactory = xmlEventFactory;
    }

    @PostConstruct
    public void setProperty() {
        // Use woodstock fast xml for parsing
        System.setProperty(XML_STREAM_XML_INPUT_FACTORY, getXmlInputFactory());
        System.setProperty(XML_STREAM_XML_OUTPUT_FACTORY, getXmlOutputFactory());
        System.setProperty(XML_STREAM_XML_EVENT_FACTORY, getXmlEventFactory());
    }

}
