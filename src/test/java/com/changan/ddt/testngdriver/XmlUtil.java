package com.changan.ddt.testngdriver;

import com.changan.ddt.ExcelDriver;
import com.changan.util.ConfigReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

//[{
//    TestName:xxx,
//        Groups:[{
//            GroupName:xxx,
//            Flag:Y
//        },{}],
//        Class:[{ClassName:xxx,
//            package:xxx,
//            Flag:Y
//        }]
//        },{}]


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
    public HashMap<String, JsonObject> getTestWithClassesandGroups() {
        HashMap<String, JsonObject> testClasses = getTestWithClasses();
        ed.columnDictionary("Groups");
        int groupRow = ExcelDriver.rowCount();

        for (int i = 1; i <= groupRow; i++) {
            String currentTestName = ExcelDriver.readCell("TestName", i);
            String currentGroupName = ExcelDriver.readCell("GroupName", i);
            String currentFlag = ExcelDriver.readCell("Flag", i);

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

            if (testClasses.get(currentTestName) != null) {
                JsonObject classJO = new JsonObject();
                classJO.addProperty("ClassName", currentClassName);
                JsonObject currentClassesJO = testClasses.get(currentTestName);
                ((JsonArray) currentClassesJO.get("classes")).add(classJO);
            } else {
                JsonObject classesJO = new JsonObject();
                JsonObject classJO = new JsonObject();
                JsonArray classJA = new JsonArray();
                classJO.addProperty("ClassName", currentClassName);
                classJA.add(classJO);
                classesJO.add("classes", classJA);
                testClasses.put(currentTestName, classesJO);
            }
        }
        return testClasses;
    }
}

