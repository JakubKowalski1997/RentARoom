package com.edu.agh.fis.RentARoom.user.utils;

import com.edu.agh.fis.RentARoom.security.user.utils.PasswordGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PasswordGeneratorTest {


    @Test
    public void testPasswordGenerator(){

        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String tmpPasswd = passwordGenerator.generate(15);
        assertEquals("wrong len: " + tmpPasswd, 15, tmpPasswd.length());
        assertTrue("no digits: "+ tmpPasswd, tmpPasswd.matches(".*[0-9]+.*")); //useDigits
        assertTrue("no upper case: "+ tmpPasswd , tmpPasswd.matches(".*[A-Z]+.*")); // useUper
        assertTrue("no lower case: " + tmpPasswd, tmpPasswd.matches(".*[a-z]+.*")); // useLower
    }
}