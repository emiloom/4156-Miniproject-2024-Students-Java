package dev.coms4156.project.individualproject;

import java.util.*;
import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.http.ResponseEntity;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Route Controller class
 */
@SpringBootTest
@ContextConfiguration
public class RouteControllerUnitTests {
    @Mock 
    private MyFileDatabase fakeMyFileDatabase;

    @InjectMocks
    private RouteController controller;

    Map<String, Department> departmentMap = new HashMap<>();

    @BeforeEach 
    public void setupRouteControllerForTesting() {
        MockitoAnnotations.openMocks(this);
        Map<String, Course> comsCourses = new HashMap<>();
        comsCourses.put("1004", new Course("Adam Cannon", "417 IAB", "11:40-12:55", 400));
        comsCourses.put("3134", new Course("Brian Borowski", "301 URIS", "4:10-5:25", 250));

        departmentMap.put("COMS", new Department("COMS", comsCourses, "Luca Carloni", 2700));
        
        when(fakeMyFileDatabase.getDepartmentMapping()).thenReturn(departmentMap);
        IndividualProjectApplication.myFileDatabase = fakeMyFileDatabase;
    }
  
    @Test
    public void testSetEnrollmentCount_Success() {
        ResponseEntity<?> response = controller.setEnrollmentCount("COMS", 1004, 200);
        assertEquals(HttpStatus.OK, response.getStatusCode()); 

    }

    @Test
    public void testSetEnrollmentCount_Failure() {
        ResponseEntity<?> response = controller.setEnrollmentCount("COMS", 0, 200);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    // @Test
    // public void testDropStudent_Success() {
    //     ResponseEntity<?> response = controller.dropStudent("COMS", 1004);
    //     assertEquals(HttpStatus.OK, response.getStatusCode()); 
    // }

    @Test
    public void testDropStudent_Failure() {
        ResponseEntity<?> response = controller.dropStudent("COMS", 1005);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    @Test
    public void addMajorToDept_Success() {
        ResponseEntity<?> response = controller.addMajorToDept("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
    }

    @Test
    public void addMajorToDept_Failure() {
        ResponseEntity<?> response = controller.addMajorToDept("MUSC");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    @Test
    public void testRetrieveDepartment_Success() {
        ResponseEntity<?> response = controller.retrieveDepartment("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentMap.get("COMS").toString(), response.getBody());
    }

    @Test
    public void testRetrieveDepartment_Failure() {
        ResponseEntity<?> response = controller.retrieveDepartment("MUSC");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testChangeCourseLocation_Success(){
        ResponseEntity<?> response = controller.changeCourseLocation("COMS", 1004, "peppa pig's house");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testChangeCourseLocation_Failure(){
        ResponseEntity<?> response = controller.changeCourseLocation("COMS", 0, "peppa pig's house");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testChangeCourseTeacher_Success(){
        ResponseEntity<?> response = controller.changeCourseTeacher("COMS", 1004, "peppa pig");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testChangeCourseTeacher_Failure(){
        ResponseEntity<?> response = controller.changeCourseTeacher("COMS", 0, "peppa pig");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testChangeCourseTime_Success(){
        ResponseEntity<?> response = controller.changeCourseTime("COMS", 1004, "never");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testChangeCourseTime_Failure(){
        ResponseEntity<?> response = controller.changeCourseTime("COMS", 0, "never");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testFindCourseInstrutor_Success(){
        ResponseEntity<?> response = controller.findCourseInstructor("COMS", 1004);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void ttestFindCourseInstrutor_Failure(){
        ResponseEntity<?> response = controller.findCourseInstructor("COMS", 0);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testFindCourseTime_Success(){
        ResponseEntity<?> response = controller.findCourseTime("COMS", 1004);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void ttestFindCourseTime_Failure(){
        ResponseEntity<?> response = controller.findCourseTime("COMS", 0);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}