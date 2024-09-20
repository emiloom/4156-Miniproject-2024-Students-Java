package dev.coms4156.project.individualproject;

import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class contains all the startup logic for the application.
 *
 * <p>DO NOT MODIFY ANYTHING BELOW THIS POINT WITH REGARD TO FUNCTIONALITY
 * YOU MAY MAKE STYLE/REFACTOR MODIFICATIONS AS NEEDED
 */
@SpringBootApplication
public class IndividualProjectApplication implements CommandLineRunner {

  /**
   * The main launcher for the service all it does
   * is make a call to the overridden run method.
   *
   * @param args A {@code String[]} of any potential runtime arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(IndividualProjectApplication.class, args);
  }

  /**
   * This contains all the setup logic, it will mainly be focused
   * on loading up and creating an instance of the database based
   * off a saved file or will create a fresh database if the file
   * is not present.
   *
   * @param args A {@code String[]} of any potential runtime args
   */
  public void run(String[] args) {
    for (String arg : args) {
      if (arg.equals("setup")) {
        myFileDatabase = new MyFileDatabase(1, "./data.txt");
        resetDataFile();
        System.out.println("System Setup");
        return;
      }
    }
    myFileDatabase = new MyFileDatabase(0, "./data.txt");
    System.out.println("Start up");
  }

  /**
   * Overrides the database reference, used when testing.
   *
   * @param testData A {@code MyFileDatabase} object referencing test data.
   */
  public static void overrideDatabase(MyFileDatabase testData) {
    myFileDatabase = testData;
    saveData = false;
  }

  /**
   * Allows for data to be reset in event of errors.
   */
  public void resetDataFile() {
    String[] times = { "11:40-12:55", "4:10-5:25", "10:10-11:25", "2:40-3:55" };
    String[] locations = { "417 IAB", "309 HAV", "301 URIS" };
    Map<String, Department> departmentMapping = new HashMap<>();

    // data for coms dept
    String[] comsCourseCodes = { "1004", "3134", "3157", "3203", "3261", "3251", "3827", "4156" };
    List<Course> comsCourses = getComsCourses(locations, times);
    addDepartment(departmentMapping, "COMS", comsCourseCodes, comsCourses, "Luca Carloni", 2700);

    // data for econ dept
    String[] econCourseCodes = { "1105", "2257", "3211", "3213", "3412", "4415", "4710", "4840" };
    List<Course> econCourses = getEconCourses(locations, times);
    addDepartment(departmentMapping, "ECON", econCourseCodes, econCourses, "Michael Woodford", 
                            2345);

    // data for ieor dept
    String[] ieorCourseCodes = { "2500", "3404", "3658", "4102", "4106", "4405", "4511", "4540" };
    List<Course> ieorCourses = getIeorCourses(times);
    addDepartment(departmentMapping, "IEOR", ieorCourseCodes, ieorCourses, "Jay Sethuraman", 67);

    // data for chem dept
    String[] chemCourseCodes = { "1403", "1500", "2045", "2444", "2494", "3080", "4071", "4102" };
    List<Course> chemCourses = getChemCourses(locations, times);
    addDepartment(departmentMapping, "CHEM", chemCourseCodes, chemCourses, "Laura J. Kaufman", 250);

    // data for phys dept
    String[] physCourseCodes = { "2802", "3008", "4003", "4018", "4040", "1602", "1001", "1201" };
    List<Course> physCourses = getPhysCourses(times);
    addDepartment(departmentMapping, "PHYS", physCourseCodes, physCourses, "Dmitri N. Basov", 43);

    // data for elen dept
    String[] elenCourseCodes = { "1201", "3082", "3331", "3401", "3701", "4510", "4702", "4830" };
    List<Course> elenCourses = getElenCourses(times);
    addDepartment(departmentMapping, "ELEN", elenCourseCodes, elenCourses, "Ioannis Kymissis", 250);

    // data for psyc dept
    String[] psycCourseCodes = { "1001", "1610", "2235", "2620", "3212", "3445", "4236", "4493" };
    List<Course> psycCourses = getPsycCourses(times);
    addDepartment(departmentMapping, "PSYC", psycCourseCodes, psycCourses, "Nim Tottenham", 437);

    myFileDatabase.setMapping(departmentMapping);
  }

  /**
   * Populate list of COMS courses.
   *
   * @param locations An array of {@code String} representing locations where the
   *                  courses are held.
   * @param times     An array of {@code String} representing the times when the
   *                  courses are scheduled.
   * @return A {@code List<Course>} containing the COMS department courses.
   */
  private List<Course> getComsCourses(String[] locations, String[] times) {
    List<Course> comsCourses = new ArrayList<>();

    addCourse(comsCourses, "Adam Cannon", locations[0], times[0], 400, 249);
    addCourse(comsCourses, "Brian Borowski", locations[2], times[1], 250, 242);
    addCourse(comsCourses, "Jae Lee", locations[0], times[1], 400, 311);
    addCourse(comsCourses, "Ansaf Salleb-Aouissi", locations[2], times[2], 250, 215);
    addCourse(comsCourses, "Josh Alman", locations[0], times[3], 150, 140);
    addCourse(comsCourses, "Tony Dear", "402 CHANDLER", "1:10-3:40", 125, 99);
    addCourse(comsCourses, "Daniel Rubenstein", "207 Math", times[2], 300, 283);
    addCourse(comsCourses, "Gail Kaiser", "501 NWC", times[2], 120, 109);

    return comsCourses;
  }

  /**
   * Populate list of ECON courses.
   * 
   *
   * @param locations An array of {@code String} representing locations where the
   *                  courses are held.
   * @param times     An array of {@code String} representing the times when the
   *                  courses are scheduled.
   * @return A {@code List<Course>} containing the ECON department courses.
   */
  private List<Course> getEconCourses(String[] locations, String[] times) {
    List<Course> econCourses = new ArrayList<>();

    addCourse(econCourses, "Waseem Noor", locations[1], times[3], 210, 187);
    addCourse(econCourses, "Tamrat Gashaw", "428 PUP", times[2], 125, 63);
    addCourse(econCourses, "Murat Yilmaz", "310 FAY", times[1], 96, 81);
    addCourse(econCourses, "Miles Leahey", "702 HAM", times[1], 86, 77);
    addCourse(econCourses, "Thomas Piskula", "702 HAM", times[0], 86, 81);
    addCourse(econCourses, "Evan D Sadler", locations[1], times[2], 110, 63);
    addCourse(econCourses, "Matthieu Gomez", "517 HAM", "8:40-9:55", 86, 37);
    addCourse(econCourses, "Mark Dean", "142 URIS", times[3], 108, 67);

    return econCourses;
  }

  /**
   * Populate list of IEOR courses.
   *
   * @param times An array of {@code String} representing the times when the
   *              courses are scheduled.
   * @return A {@code List<Course>} containing the IEOR department courses.
   */
  private List<Course> getIeorCourses(String[] times) {
    List<Course> ieorCourses = new ArrayList<>();

    addCourse(ieorCourses, "Uday Menon", "627 MUDD", times[0], 50, 52);
    addCourse(ieorCourses, "Christopher J Dolan", "303 MUDD", times[2], 73, 80);
    addCourse(ieorCourses, "Daniel Lacker", "310 FAY", times[2], 96, 87);
    addCourse(ieorCourses, "Antonius B Dieker", "209 HAM", times[2], 110, 92);
    addCourse(ieorCourses, "Kaizheng Wang", "501 NWC", times[2], 150, 161);
    addCourse(ieorCourses, "Yuri Faenza", "517 HAV", times[0], 80, 19);
    addCourse(ieorCourses, "Michael Robbins", "633 MUDD", "9:00-11:30", 150, 50);
    addCourse(ieorCourses, "Krzysztof M Choromanski", "633 MUDD", "7:10-9:40", 60, 33);

    return ieorCourses;
  }

  /**
   * Populate list of CHEM courses.
   *
   * @param locations An array of {@code String} representing locations where the
   *                  courses are held.
   * @param times     An array of {@code String} representing the times when the
   *                  courses are scheduled.
   * @return A {@code List<Course>} containing the CHEM department courses.
   */
  private List<Course> getChemCourses(String[] locations, String[] times) {
    List<Course> chemCourses = new ArrayList<>();

    addCourse(chemCourses, "Ruben M Savizky", locations[1], "6:10-7:25", 120, 100);
    addCourse(chemCourses, "Joseph C Ulichny", "302 HAV", "6:10-9:50", 46, 50);
    addCourse(chemCourses, "Luis M Campos", "209 HAV", "1:10-2:25", 50, 29);
    addCourse(chemCourses, "Christopher Eckdahl", locations[1], times[0], 150, 150);
    addCourse(chemCourses, "Talha Siddiqui", "202 HAV", "1:10-5:00", 24, 18);
    addCourse(chemCourses, "Milan Delor", "209 HAV", times[2], 60, 18);
    addCourse(chemCourses, "Jonathan S Owen", "320 HAV", "8:40-9:55", 42, 29);
    addCourse(chemCourses, "Dalibor Sames", "320 HAV", times[2], 28, 27);

    return chemCourses;
  }

  /**
   * Populate list of PHYS courses.
   *
   * @param times An array of {@code String} representing the times when the
   *              courses are scheduled.
   * @return A {@code List<Course>} containing the PHYS department courses.
   */
  private List<Course> getPhysCourses(String[] times) {
    List<Course> physCourses = new ArrayList<>();

    addCourse(physCourses, "Szabolcs Marka", "301 PUP", times[3], 150, 131);
    addCourse(physCourses, "Eric Raymer", "428 PUP", times[3], 145, 130);
    addCourse(physCourses, "Kerstin M Perez", "428 PUP", times[2], 140, 77);
    addCourse(physCourses, "Yury Levin", "329 PUP", "10:10-12:00", 60, 23);
    addCourse(physCourses, "William A Zajc", "329 PUP", times[2], 75, 60);
    addCourse(physCourses, "Frederik Denef", "214 PUP", times[1], 50, 19);
    addCourse(physCourses, "James W McIver", "307 PUP", times[3], 30, 18);
    addCourse(physCourses, "James C Hill", "214 PUP", times[1], 50, 31);

    return physCourses;
  }

  /**
   * Populate list of ELEN courses.
   *
   * @param times An array of {@code String} representing the times when the
   *              courses are scheduled.
   * @return A {@code List<Course>} containing the ELEN department courses.
   */
  private List<Course> getElenCourses(String[] times) {
    List<Course> elenCourses = new ArrayList<>();

    addCourse(elenCourses, "David G Vallancourt", "301 PUP", times[1], 120, 108);
    addCourse(elenCourses, "Kenneth Shepard", "1205 MUDD", "4:10-6:40", 32, 30);
    addCourse(elenCourses, "David G Vallancourt", "203 MATH", times[0], 80, 54);
    addCourse(elenCourses, "Keren Bergman", "829 MUDD", times[3], 40, 25);
    addCourse(elenCourses, "Irving Kalet", "333 URIS", times[3], 50, 24);
    addCourse(elenCourses, "Mohamed Kamaludeen", "903 SSW", "7:00-9:30", 30, 22);
    addCourse(elenCourses, "Alexei Ashikhmin", "332 URIS", "7:00-9:30", 50, 5);
    addCourse(elenCourses, "Christine P Hendon", "633 MUDD", "10:10-12:40", 60, 22);

    return elenCourses;
  }

  /**
   * Populate list of PSYC courses.
   *
   * @param times An array of {@code String} representing the times when the
   *              courses are scheduled.
   * @return A {@code List<Course>} containing the PSYC department courses.
   */
  private List<Course> getPsycCourses(String[] times) {
    List<Course> psycCourses = new ArrayList<>();

    addCourse(psycCourses, "Patricia G Lindemann", "501 SCH", "1:10-2:25", 200, 191);
    addCourse(psycCourses, "Christopher Baldassano", "200 SCH", times[2], 45, 42);
    addCourse(psycCourses, "Katherine T Fox-Glassman", "501 SCH", times[0], 125, 128);
    addCourse(psycCourses, "Jeffrey M Cohen", "303 URIS", "1:10-3:40", 60, 55);
    addCourse(psycCourses, "Mayron Piccolo", "200 SCH", "2:10-4:00", 15, 15);
    addCourse(psycCourses, "Mariam Aly", "405 SCH", "2:10-4:00", 12, 12);
    addCourse(psycCourses, "Trenton Jerde", "405 SCH", "6:10-8:00", 18, 17);
    addCourse(psycCourses, "Jennifer Blaze", "200 SCH", "2:10-4:00", 15, 9);

    return psycCourses;
  }

  /**
   * Helper method to populate Map of Courses in a department.
   *
   * @param courseCodes A {@Code String[]} of the course numbers
   * @param courses     A {@Code List<Course>} of the courses in a department
   *                    corresponding to course numbers
   * @return A {@Code Map} that is populated with course codes and their
   *         corresponding course
   */
  private Map<String, Course> getCourseMap(String[] courseCodes, List<Course> courses) {
    Map<String, Course> courseMap = new HashMap<>();

    int i = 0;
    for (Course course : courses) {
      courseMap.put(courseCodes[i], course);
      i++;
    }

    return courseMap;
  }

  /**
   * Add Department to the departmentMapping Map
   * This method creates a {@Code Department} object using the provided details
   * and adds it
   * to the {@Code Map} of departments. It also generates a mapping of course
   * codes to
   * {@Code Course} objects and assigns this mapping to the new department.
   *
   * @param departmentMapping A {@Code Map<String, Department>} where the key
   *                          is the department
   *                          code and the value is the corresponding
   *                          {@Code Department} object.
   * @param departmentCode    A {@Code String} representing the code identifying
   *                          the department.
   * @param courseCodes       A {@Code String[]} array of course codes
   *                          corresponding to the courses
   *                          offered by the department. Each code will be used to
   *                          map to its
   *                          respective {@Code Course} object.
   * @param courses           A {@Code List<Course>} of {@Code Course} objects
   *                          that the department offers.
   * @param departmentChair   A {@Code String} representing the name of the
   *                          chairperson for the department.
   * @param numberOfMajors    An {@Code int} indicating the number of students
   *                          majoring in the department.
   */
  private void addDepartment(Map<String, Department> departmentMapping,
      String departmentCode,
      String[] courseCodes,
      List<Course> courses,
      String departmentChair,
      int numberOfMajors) {
    Map<String, Course> courseMap = getCourseMap(courseCodes, courses);
    Department department 
            = new Department(departmentCode, courseMap, departmentChair, numberOfMajors);
    departmentMapping.put(departmentCode, department);
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
  private void addCourse(List<Course> courses, String instructor, String location, 
      String time, int capacity, int enrolled) {
    courses.add(new Course(instructor, location, time, capacity, enrolled));
  }

  /**
   * This contains all the overheading teardown logic, it will
   * mainly be focused on saving all the created user data to a
   * file, so it will be ready for the next setup.
   */
  @PreDestroy
  public void onTermination() {
    System.out.println("Termination");
    if (saveData) {
      myFileDatabase.saveContentsToFile();
    }
  }

  // Database Instance
  public static MyFileDatabase myFileDatabase;
  private static boolean saveData = true;
}
