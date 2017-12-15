package com.changan.ddt.testngdriver;

import com.changan.ddt.ExcelDriver;
import com.changan.util.ConfigReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.IOException;

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
    @Test
    public void getGroups() {
//        ConfigReader cr = new ConfigReader();
//        String testngPath = cr.getTestNgConfigPath();
//        ExcelDriver ed = null;
//        try {
//            ed = new ExcelDriver(testngPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ed.columnDictionary("Groups");
//        String groups = ed.readCell("GroupName", 1);
//        System.out.println("groups>>" + groups);
        JsonObject jotest = new JsonObject();
        JsonObject jogroup = new JsonObject();
        jotest.addProperty("TestName", "tn1");
        jogroup.addProperty("GroupNane", "g1");
        jogroup.addProperty("flag", "Y");
        JsonArray jagroups = new JsonArray();
        jagroups.add(jogroup);
        JsonObject jogroup2 = new JsonObject();
        jogroup2.addProperty("GroupNane", "g1");
        jogroup2.addProperty("flag", "Y");
        jagroups.add(jogroup2);
        jotest.add("Groups", jagroups);
        System.out.println("jo--" + jotest.toString());

//        JsonObject.add()
    }
}

