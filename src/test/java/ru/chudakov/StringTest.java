package ru.chudakov;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    @Test
    public void split(){
        Pattern pattern = Pattern.compile("\\w+\\(\\w+(,\\w+)*\\)=*+");
        Matcher matcher = pattern.matcher("qw(1,2,3)=a+b");
        System.out.println(matcher.matches());
    }
}
