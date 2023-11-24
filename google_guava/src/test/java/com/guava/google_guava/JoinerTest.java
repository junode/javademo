package com.guava.google_guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JoinerTest {

    private String targetFileNanem = "D:/hello.txt";

    private List<String> elementsWithNotNull = Arrays.asList("A", "B", "C");

    private List<String> elementsWihtNullValue = Arrays.asList("A", null, "C");


    @Test
    public void testJoin() {
        String result = Joiner.on(",").join(elementsWithNotNull);
        assertThat(result, equalTo("A,B,C"));
        // Result: "A, B, C"
    }

    @Test
    public void handleNullWithDefaultValue() {
        // use default value to handle null value
        String result = Joiner.on(",").useForNull("NULL").join(elementsWihtNullValue);
        assertThat(result, equalTo("A,NULL,C"));
    }

    @Test
    public void handleNullButSkip() {
        // use default value to handle null value
        String result = Joiner.on(",").skipNulls().join(elementsWihtNullValue);
        assertThat(result, equalTo("A,C"));
    }

    @Test
    public void appendToStringBuilder() {
        StringBuilder builder = new StringBuilder("Prefix: ");
        Joiner.on(",").appendTo(builder, elementsWithNotNull);
        assertThat(builder.toString(), equalTo("Prefix: A,B,C"));
    }

    @Test
    public void joinMapEntities() {
        Map<String, String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
        String mapStr = Joiner.on(",").withKeyValueSeparator("=").join(map);
        assertThat(mapStr, equalTo("key1=value1,key2=value2"));
    }

    @Test
    public void testJoinOnAppendToWriter() {
        try(FileWriter fileWriter = new FileWriter(new File(targetFileNanem))) {
            Joiner.on("#").useForNull("DEFAULT").appendTo(fileWriter, elementsWihtNullValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
