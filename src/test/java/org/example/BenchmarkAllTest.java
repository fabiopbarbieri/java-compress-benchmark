package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple BenchmarkAll.
 */
public class BenchmarkAllTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BenchmarkAllTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(BenchmarkAllTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}
