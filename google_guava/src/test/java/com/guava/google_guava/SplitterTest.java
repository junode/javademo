package com.guava.google_guava;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SplitterTest {

    private String notNullElements = "A,B,C";
    private String notNullElementsWithWhiteSpace = "A, B,C  ";
    private String notNullElementsWithEmtpy = "A,,B,C,";
    private String subStringLimitLength = "A,B,C,D,E";
    private String patternStr = "A1B2C";

    @Test
    public void testBaseSplit() {
        List<String> result = Splitter.on(",").splitToList(notNullElements);
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo("A"));
        assertThat(result.get(1), equalTo("B"));
        assertThat(result.get(2), equalTo("C"));
    }
     
    @Test
    public void testSplitterOnWhitespace() {
        List<String> result = Splitter.on(",").trimResults().splitToList(notNullElementsWithWhiteSpace);
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo("A"));
        assertThat(result.get(1), equalTo("B"));
        assertThat(result.get(2), equalTo("C"));
    }

    @Test
    public void testSplitterOnOmittingEmptyString() {
        List<String> result = Splitter.on(",").omitEmptyStrings().splitToList(notNullElementsWithEmtpy);
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo("A"));
        assertThat(result.get(1), equalTo("B"));
        assertThat(result.get(2), equalTo("C"));
    }

    @Test
    public void testSplitter_On_Limit_SubstringLength() {
        List<String> result = Splitter.on(",").limit(3).splitToList(subStringLimitLength);
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo("A"));
        assertThat(result.get(1), equalTo("B"));
        assertThat(result.get(2), equalTo("C,D,E"));
    }

    @Test
    public void testSplitterOnPattern() {
        List<String> strings = Splitter.onPattern("\\d").splitToList(patternStr);
        assertThat(strings.size(), equalTo(3));
        assertThat(strings.get(0), equalTo("A"));
        assertThat(strings.get(1), equalTo("B"));
        assertThat(strings.get(2), equalTo("C"));
    }
}
