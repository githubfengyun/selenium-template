package com.changan.ddt.testngdriver;

public class TestClass {
    String className;
    String packagePath;
    String testName;
    Boolean flag;
    public TestClass(String className, String packagePath, String testName, Boolean flag) {
        className = className;
        packagePath = packagePath;
        testName = testName;
        flag = flag;
    }
}
