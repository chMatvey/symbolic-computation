package ru.chudakov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    @Test
    public void split(){
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher("1242");
        System.out.println(matcher.matches());
    }
}
