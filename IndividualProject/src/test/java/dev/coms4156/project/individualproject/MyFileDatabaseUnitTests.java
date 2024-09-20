package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.beans.Transient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseUnitTests {

  private static final String GOOD_PATH = "./data.txt";
  private static final String BAD_PATH = "./fake.txt";

  private static MyFileDatabase testDatabase = new MyFileDatabase(0, GOOD_PATH);
  private static MyFileDatabase testDatabase2 = new MyFileDatabase(1, GOOD_PATH);

  @Test
  public void deSerializeObjectFromFile_Success() {
    assertNotNull(testDatabase.getDepartmentMapping());
    assertNull(testDatabase2.getDepartmentMapping());
  }

  @Test
  public void toStringTest() {
    assertEquals(testDatabase.toString(), testDatabase.toString());
  }

}