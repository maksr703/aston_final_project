package org.example.sorting;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class EmailComparatorTest {

    private final Comparator<String> comparator = new EmailComparator();

    @Test
    public void testNulls() {
        assertEquals(0, comparator.compare(null, null));
        assertTrue(comparator.compare(null, "a@b.com") < 0);
        assertTrue(comparator.compare("a@b.com", null) > 0);
    }

    @Test
    public void testSameEmails() {
        assertEquals(0, comparator.compare("a@b.com", "a@b.com"));
    }

    @Test
    public void testInvalidEmails() {
        assertTrue(comparator.compare("abc", "bcd") < 0);
        assertTrue(comparator.compare("zzz", "abc") > 0);
    }

    @Test
    public void testDifferentDomains() {
        assertTrue(comparator.compare("a@a.com", "a@b.com") < 0);
    }

    @Test
    public void testSameDomainDifferentLocal() {
        assertTrue(comparator.compare("a@b.com", "b@b.com") < 0);
        assertTrue(comparator.compare("b@b.com", "a@b.com") > 0);
    }
}