package org.ivzh;


import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        Map<String, Integer> mapWithTestResults = TestUtils.createMapWithTestResults();
        boolean condition;
        for (Map.Entry<String, Integer> e : mapWithTestResults.entrySet()) {
            condition = e.getKey().length() == e.getValue();
            System.out.println("Test results for " + e.getKey() + " and " + e.getValue() + " are " + condition);
            assertTrue(condition);
        }
    }
}
