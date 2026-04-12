package org.example.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailComparatorTest {
    
    private EmailComparator comparator;
    
    @BeforeEach
    void setUp() {
        comparator = new EmailComparator();
    }
    
    @Test
    void testBothNull() {
        assertEquals(0, comparator.compare(null, null));
    }
    
    @Test
    void testFirstNull() {
        assertEquals(-1, comparator.compare(null, "user@test.com"));
    }
    
    @Test
    void testSecondNull() {
        assertEquals(1, comparator.compare("user@test.com", null));
    }
    
    @Test
    void testEqualEmails() {
        assertEquals(0, comparator.compare("alice@test.com", "alice@test.com"));
    }
    
    @Test
    void testFirstLessThanSecond() {
        assertTrue(comparator.compare("alice@test.com", "bob@test.com") < 0);
    }
    
    @Test
    void testFirstGreaterThanSecond() {
        assertTrue(comparator.compare("bob@test.com", "alice@test.com") > 0);
    }
}