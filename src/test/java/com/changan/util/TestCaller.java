package com.changan.util;

import com.changan.ddt.XmlUtil;
import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.List;

public class TestCaller {

    @Test
    public void startRun() {
        XmlUtil.initXmlUtil();
        XmlUtil.generateTestngXML();
        XmlUtil.autoRunXml();
    }
}
