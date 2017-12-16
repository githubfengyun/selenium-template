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
//    @Test
//    public void getGroups() {
////        ConfigReader cr = new ConfigReader();
////        String testngPath = cr.getTestNgConfigPath();
////        ExcelDriver ed = null;
////        try {
////            ed = new ExcelDriver(testngPath);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        ed.columnDictionary("Groups");
////        String groups = ed.readCell("GroupName", 1);
////        System.out.println("groups>>" + groups);
//        JsonObject jotest = new JsonObject();
//        JsonObject jogroup = new JsonObject();
//        jotest.addProperty("TestName", "tn1");
//        jogroup.addProperty("GroupNane", "g1");
//        jogroup.addProperty("flag", "Y");
//        JsonArray jagroups = new JsonArray();
//        jagroups.add(jogroup);
//        JsonObject jogroup2 = new JsonObject();
//        jogroup2.addProperty("GroupNane", "g1");
//        jogroup2.addProperty("flag", "Y");
//        jagroups.add(jogroup2);
//        jotest.add("Groups", jagroups);
//        for (JsonElement jog:jagroups) {
//            JsonElement j = jogroup.get("flag");
//
//            JsonObject a = (JsonObject) jog;
//            JsonElement jj =a.get("GroupNane");
//            System.out.print("tt");
//        }
//        System.out.println("jo--" + jotest.toString());
//
////        JsonObject.add()
//    }

    @Test
    public  HashMap<String, JsonObject> getTestWithClasses() {
        ed.columnDictionary("Classes");
        int classRow = ExcelDriver.rowCount();
        HashMap<String, JsonObject> testClasses = new HashMap<String, JsonObject>();
        for(int i=1; i <classRow; i++) {
            String currentTestName = ExcelDriver.readCell("TestName", i);
            String currentClassName = ExcelDriver.readCell("ClassName", i);
            String currentPackage = ExcelDriver.readCell("Package", i);
            String currentFlag = ExcelDriver.readCell("Flag", i);

            if(testClasses.get(currentTestName) != null) {
                JsonObject classJO = new JsonObject();
                classJO.addProperty("ClassName", currentClassName);
                JsonObject currentClassesJO = testClasses.get(currentTestName);
                ((JsonArray)currentClassesJO.get("classes")).add(classJO);
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

