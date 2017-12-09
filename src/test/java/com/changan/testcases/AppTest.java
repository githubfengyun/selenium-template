package com.changan.testcases;


import com.changan.selenium.App;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testAdd(){
        App a1 = new App();
        Assert.assertEquals(6, a1.add(1,5));
    }
}