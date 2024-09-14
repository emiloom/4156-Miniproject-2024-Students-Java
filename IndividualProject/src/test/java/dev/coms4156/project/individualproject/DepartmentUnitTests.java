package dev.coms4156.project.individualproject;

import java.util.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

/**
 * Unit tests for Department class
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

    @BeforeAll
    public static void setupDepartmentForTesting() {
        String[] times = { "11:40-12:55", "4:10-5:25", "10:10-11:25", "2:40-3:55" };
        String[] locations = { "417 IAB", "309 HAV", "301 URIS" };
        String[] comsCourseCodes = { "1004", "3134", "3157", "3203", "3261", "3251", "3827", "4156" };
        List<Course> comsCourses = getComsCourses(locations, times);
        Map<String, Course> courseMap = getCourseMap(comsCourseCodes, comsCourses);

        testDepartment = new Department("COMS", courseMap, "Luca Carloni", 2700);
    }

    @Test
    public void addCourseTest(){
        Map<String, Course> expectedMap = new HashMap<>();
        expectedMap.put("1004", dummyCourse);
        testDepartment.addCourse("1004", dummyCourse);
        assertEquals(expectedMap, testDepartment.getCourseSelection());
    }

    @Test
    public void toStringTest() {
        String expectedResult = "COMS 1004: \nInstructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55\n";
        assertEquals(expectedResult, testDepartment.toString());
    }

    /**
     * Populate list of COMS courses
     * 
     * @param locations An array of {@code String} representing locations where the
     *                  courses are held.
     * @param times     An array of {@code String} representing the times when the
     *                  courses are scheduled.
     * @return A {@code List<Course>} containing the COMS department courses.
     */
    private static List<Course> getComsCourses(String[] locations, String[] times) {
        List<Course> comsCourses = new ArrayList<>();

        addCourse(comsCourses, "Adam Cannon", locations[0], times[0], 400, 249);

        return comsCourses;
    }

    /**
     * Adds a new course to the list of courses.
     * 
     * @param courses    A {@Code List<Course>} to which the new {@Code Course} will
     *                   be added.
     * @param instructor A {@Code String} representing the name of the instructor
     *                   for the course.
     * @param location   A {@Code String} indicating the location where the course
     *                   is held.
     * @param time       A {@Code String} specifying the time during which the
     *                   course takes place.
     * @param capacity   An {@Code int} representing the maximum number of students
     *                   that the course can accommodate.
     * @param enrolled   An {@Code int} indicating the number of students currently
     *                   enrolled in the course.
     */
    private static void addCourse(List<Course> courses, String instructor, String location, String time, int capacity,
            int enrolled) {
        courses.add(new Course(instructor, location, time, capacity, enrolled));
    }

    /**
     * Helper method to populate Map of Courses in a department
     * 
     * @param courseCodes A {@Code String[]} of the course numbers
     * @param courses     A {@Code List<Course>} of the courses in a department
     *                    corresponding to course numbers
     * @return A {@Code Map} that is populated with course codes and their
     *         corresponding course
     */
    private static Map<String, Course> getCourseMap(String[] courseCodes, List<Course> courses) {
        Map<String, Course> courseMap = new HashMap<>();

        int i = 0;
        for (Course course : courses) {
            courseMap.put(courseCodes[i], course);
            i++;
        }

        return courseMap;
    }

    /** The test department instance used for testing. */
    public static Department testDepartment;
    private static Course dummyCourse = new Course("Adam Cannon", "417 IAB", "11:40-12:55", 250);
}
