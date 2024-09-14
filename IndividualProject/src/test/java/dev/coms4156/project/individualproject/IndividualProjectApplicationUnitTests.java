package dev.coms4156.project.individualproject;

import java.util.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.beans.Transient;
import org.mockito.Mockito;

/**
 * Unit tests for IndividualProjectApplication class
 */
@SpringBootTest
@ContextConfiguration
public class IndividualProjectApplicationUnitTests {
    private static IndividualProjectApplication testIndividualProjectApplication = new IndividualProjectApplication();
    private static MyFileDatabase testDatabase = Mockito.mock(MyFileDatabase.class);

    @Test
    public void testReset(){
        IndividualProjectApplication.overrideDatabase(testDatabase);
        testIndividualProjectApplication.resetDataFile();

        verify(testDatabase, times(1)).setMapping(anyMap());
    }

    @Test 
    public void testOnTerminationNoSave(){
        IndividualProjectApplication.overrideDatabase(testDatabase);
        testIndividualProjectApplication.onTermination();

        verify(testDatabase, times(0)).saveContentsToFile();
    }
}
