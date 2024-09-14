package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

/**
 * Unit tests for the Course class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentTest() {
    boolean expectedResult = false;
    boolean result = testCourse.enrollStudent();
    assertEquals(expectedResult, testCourse.enrollStudent());
  }

  @Test
  public void dropStudentTest() {
    boolean expectedResult = false;
    boolean result = testCourse.dropStudent();
    assertEquals(expectedResult, testCourse.dropStudent());
  }

  @Test
  public void isCourseFullTest_True(){
    testCourse.setEnrolledStudentCount(550);
    assertEquals(testCourse.isCourseFull(), true);
  }

  @Test
  public void isCourseFull_False(){
    testCourse.setEnrolledStudentCount(20);
    assertEquals(testCourse.isCourseFull(), false);
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}
