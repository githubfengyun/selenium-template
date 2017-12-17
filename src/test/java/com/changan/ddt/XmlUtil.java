package com.changan.ddt;

import com.changan.ddt.ExcelDriver;
import com.changan.util.ConfigReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.testng.annotations.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class XmlUtil {
    private static ExcelDriver ed;

    public void XmlUtil() {
        ConfigReader cr = new ConfigReader();
        String testngPath = cr.getTestNgConfigPath();
        try {
            ed = new ExcelDriver(testngPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateTestngXML() {
        ConfigReader cr = new ConfigReader();
        String testngPath = cr.getTestNgXMLPath();
        String projectName = cr.getProjectName();
        HashMap<String, JsonObject> testHM = getTestWithClassesandGroups();

        try {
            Document document = DocumentHelper.createDocument();
            document.addDocType("suite", null,"http://testng.org/testng-1.0.dtd");
            Element rootElement = document.addElement("suite");
            rootElement.addAttribute("name", projectName);
            rootElement.addAttribute("verbose", "1");

            for (String key : testHM.keySet()) {
                Element testElement = rootElement.addElement("test");
                testElement.addAttribute("name", key);

                if(testHM.get(key).get("groups") !=null){
                    Element groupsElement = testElement.addElement("groups");
                    Element runElement = groupsElement.addElement("run");
                    JsonArray groupsJA = (JsonArray) testHM.get(key).get("groups");
                    for(JsonElement groupJE:groupsJA) {
                        String groupName = ((JsonObject)groupJE).get("GroupName").getAsString();
                        Element includeElement = runElement.addElement("include");
                        includeElement.addAttribute("name", groupName);
                    }
                }

                if(testHM.get(key).get("classes") !=null) {
                    Element classesElement = testElement.addElement("classes");
                    JsonArray classesJA = (JsonArray) testHM.get(key).get("classes");
                    for(JsonElement classJE:classesJA) {
                        String className = ((JsonObject)classJE).get("ClassName").getAsString();
                        Element classElement = classesElement.addElement("class");
                        classElement.addAttribute("name", className);
                    }
                }
            }
            Writer fileWriter = new FileWriter(testngPath);
            XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HashMap<String, JsonObject> getTestWithClasses() {
        ConfigReader cr = new ConfigReader();
        String testngPath = cr.getTestNgConfigPath();
        try {
            ed = new ExcelDriver(testngPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ed.columnDictionary("Classes");
        int classRow = ExcelDriver.rowCount();
        HashMap<String, JsonObject> testClasses = new HashMap<String, JsonObject>();
        for (int i = 1; i <= classRow; i++) {
            String currentTestName = ExcelDriver.readCell("TestName", i);
            String currentClassName = ExcelDriver.readCell("ClassName", i);
            String currentPackage = ExcelDriver.readCell("Package", i);
            String currentFlag = ExcelDriver.readCell("Flag", i);

            if(currentFlag.equalsIgnoreCase("N")) {
                continue;
            }

            if (testClasses.get(currentTestName) != null) {
                JsonObject classJO = new JsonObject();
                classJO.addProperty("ClassName", currentPackage + "." + currentClassName);
                JsonObject currentClassesJO = testClasses.get(currentTestName);
                ((JsonArray) currentClassesJO.get("classes")).add(classJO);
            } else {
                JsonObject classesJO = new JsonObject();
                JsonObject classJO = new JsonObject();
                JsonArray classJA = new JsonArray();
                classJO.addProperty("ClassName", currentPackage + "." + currentClassName);
                classJA.add(classJO);
                classesJO.add("classes", classJA);
                testClasses.put(currentTestName, classesJO);
            }
        }
        return testClasses;
    }

    private HashMap<String, JsonObject> getTestWithClassesandGroups() {
        HashMap<String, JsonObject> testClasses = getTestWithClasses();
        ed.columnDictionary("Groups");
        int groupRow = ExcelDriver.rowCount();

        for (int i = 1; i <= groupRow; i++) {
            String currentTestName = ExcelDriver.readCell("TestName", i);
            String currentGroupName = ExcelDriver.readCell("GroupName", i);
            String currentFlag = ExcelDriver.readCell("Flag", i);

            if(currentFlag.equalsIgnoreCase("N")) {
                continue;
            }

            if (testClasses.get(currentTestName) != null && testClasses.get(currentTestName).get("groups") != null) {
                JsonObject groupJO = new JsonObject();
                groupJO.addProperty("GroupName", currentGroupName);
                JsonObject currentgroupsJO = testClasses.get(currentTestName);
                ((JsonArray) currentgroupsJO.get("groups")).add(groupJO);
            } else {
                JsonObject groupJO = new JsonObject();
                JsonArray groupJA = new JsonArray();
                groupJO.addProperty("GroupName", currentGroupName);
                groupJA.add(groupJO);
                testClasses.get(currentTestName).add("groups", groupJA);
            }
        }
        return testClasses;
    }

}

