// username and password cant have spaces in them

import java.io.*;
import java.util.*;

public class Login implements Course {
    static BufferedReader br = null;
    private static HashSet<String> students = new HashSet<>();
    private static HashSet<String> teachers = new HashSet<>();
    private static HashMap<String, List<String>> courses = new HashMap<>();
    private static HashMap<String, List<String>> coursesTeacher = new HashMap<>();

    public void initializeStudents() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] studentDetails = line.split(":");
                students.add(studentDetails[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void initializeTeachers() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] teacherDetails = line.split(":");
                teachers.add(teacherDetails[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void initializeCourses() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] courseDetails = line.split(":");
                List<String> courseNames = new ArrayList<>();
                for (int i = 2; i < courseDetails.length; i++) {
                    courseNames.add(courseDetails[i]);
                }
                courses.put(courseDetails[1], courseNames);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    @Override
    public void populateHashmap() throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArr = line.split(":");
            ArrayList<String> coursesList = new ArrayList<>();
            for (int i = 4; i < lineArr.length; i++) {
                coursesList.add(lineArr[i]);
            }
            teacherCourses.put(lineArr[1], coursesList);
        }
        this.coursesTeacher = teacherCourses;

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void populateAll() throws IOException {
        initializeCourses();
        initializeStudents();
        initializeTeachers();
        populateHashmap();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Login login = new Login();
        login.populateAll();
        // Admin ad = new Admin("admin_name", "12345678", students, teachers);
        // ad.addStudent("hmmmm", "dhfhfhf", "hdd dfhjf", new ArrayList<String>() {
        // {
        // add("Physics");
        // add("Maths");
        // add("English");
        // add("Chemistry");
        // }
        // });
        // ad.addTeacher("teacher_name5", "1133@abab", "Teacher Name", new
        // ArrayList<String>() {
        // {
        // add("chemistry");
        // add("maths");
        // }
        // });
        // ad.updateStudentPassword("random_name", "abc@15432");
        // ad.updateStudentName("random_name", "Randoi Name");
        // ad.deleteStudentDetails("random_name1");
        // ad.deleteStudentCourses("random_name3", new ArrayList<>() {
        // {
        // add("biology");
        // add("englis");
        // }
        // });
        Scanner sc = new Scanner(System.in);
        System.out.println("*************************************************************");
        System.out.println("***************  School Management System  ******************");
        System.out.println("*************************************************************");
        System.out.println();
        while (true) {
            System.out.println("*********************  Login Page  **************************");
            System.out.println("-----To login as Admin     : input 1");
            System.out.println("-----To login as a Student : input 2");
            System.out.println("-----To login as a Teacher : input 3");
            System.out.println("-----To close the Window   : input 4");
            int choice = 0;
            while (true) {
                boolean bool = false;
                try {
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 4) {
                        bool = false;
                        System.out.println("Please input appropriate value!");
                    } else {
                        bool = true;
                    }
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Please input integer value!");
                    sc.nextLine();
                }

                if (bool) {
                    break;
                }
            }
            if (choice == 1) {
                // Login.clearScreen();
                System.out.println("-----Enter Username:");
                String username = sc.next();
                sc.nextLine();
                System.out.println("-----Enter Password:");
                String password = sc.next();
                sc.nextLine();
                try {
                    File file = new File(System.getProperty("user.dir") + "/src/Admin.txt");
                    br = new BufferedReader(new FileReader(file));
                    String[] creds = br.readLine().split(" ");
                    if (creds[0].equals(username) && creds[1].equals(password)) {
                        System.out.println("Welcome " + username);
                        while (true) {
                            Admin admin = new Admin(username, password, students, teachers, courses);
                            System.out.println("-----To add a new student                     : input 1");
                            System.out.println("-----To add a new teacher                     : input 2");
                            System.out.println("-----To update the password of a student      : input 3");
                            System.out.println("-----To update the full name of the student   : input 4");
                            System.out.println("-----To delete a particular Student's details : input 5");
                            System.out.println("-----To update the password of a teacher      : input 6");
                            System.out.println("-----To update the full name of the teacher   : input 7");
                            System.out.println("-----To delete a particular Teacher's details : input 8");
                            System.out.println("-----To add Teacher courses                   : input 9");
                            System.out.println("-----To add Student courses                   : input 10");
                            System.out.println("-----To delete Teacher courses                : input 11");
                            System.out.println("-----To delete Student courses                : input 12");
                            System.out.println("-----To add new courses                       : input 13");
                            System.out.println("-----To delete courses                        : input 14");
                            System.out.println("-----To check and update total working days   : input 15");
                            System.out.println("-----To check and update total marks          : input 16");
                            System.out.println("-----To Logout                                : input 17");
                            int inpChoice = 0;
                            while (true) {
                                boolean bool = false;
                                try {
                                    inpChoice = sc.nextInt();
                                    if (inpChoice < 1 || inpChoice > 17) {
                                        bool = false;
                                        System.out.println("Please input appropriate value!");
                                    } else {
                                        bool = true;
                                    }
                                    sc.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Please input integer value!");
                                    sc.nextLine();
                                }

                                if (bool) {
                                    break;
                                }
                            }
                            if (inpChoice == 1) {
                                System.out.println("-----Enter the username of the student:");
                                String studentUsername;
                                while (true) {
                                    studentUsername = sc.next();
                                    sc.nextLine();
                                    if (students.contains(studentUsername)) {
                                        System.out.println(
                                                "The username already exists in the database, please input another!");
                                    } else {
                                        break;
                                    }
                                }
                                System.out.println("-----Enter password for the new student:");
                                String studentPassword = sc.next();
                                sc.nextLine();
                                System.out.println("-----Enter the full name of the new student:");
                                String studentFullName = sc.nextLine();
                                ArrayList<String> studentCourses = new ArrayList<>();
                                System.out.println("Available courses: ");
                                int k = 1;
                                for (Map.Entry<String, List<String>> entry : courses.entrySet()) {
                                    System.out.println(k++ + " " + entry.getKey());
                                }
                                System.out
                                        .println("-----Enter the number of courses you want to register student for:");
                                int numberOfCourses = 0;
                                while (true) {
                                    boolean bool = false;
                                    try {
                                        numberOfCourses = sc.nextInt();
                                        if (numberOfCourses < 0 || numberOfCourses > courses.size()) {
                                            System.out.println("Please input appropriate value!");
                                            bool = false;
                                        } else {
                                            bool = true;
                                        }
                                        sc.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Please input integer value!");
                                        sc.nextLine();
                                    }

                                    if (bool) {
                                        break;
                                    }
                                }
                                System.out.println("Enter the courses for which you want to register student for: ");
                                for (int i = 0; i < numberOfCourses; i++) {
                                    String input = sc.nextLine().toLowerCase(Locale.ROOT);
                                    if (!courses.containsKey(input)) {
                                        System.out.println("Course is unavailable, try entering another");
                                        i--;
                                    } else {
                                        studentCourses.add(input);
                                    }
                                }
                                admin.addStudent(studentUsername, studentPassword, studentFullName, studentCourses);
                                System.out.println("Student with details added successfully");
                                students.add(studentUsername);
                                login.populateAll();
                            } else if (inpChoice == 2) {
                                System.out.println("-----Enter the username of the teacher:");
                                String teacherUsername;
                                while (true) {
                                    teacherUsername = sc.next();
                                    sc.nextLine();
                                    if (teachers.contains(teacherUsername)) {
                                        System.out.println(
                                                "The username already exists in the database, please input another!");
                                    } else {
                                        break;
                                    }
                                }
                                System.out.println("-----Enter password for the new teacher:");
                                String teacherPassword = sc.next();
                                sc.nextLine();
                                System.out.println("-----Enter the full name of the new teacher:");
                                String teacherFullName = sc.nextLine();
                                ArrayList<String> teacherCourses = new ArrayList<>();
                                System.out.println("Available courses: ");
                                HashSet<String> currentTeacherCourses = new HashSet<>();
                                for (Map.Entry<String, List<String>> entry : coursesTeacher.entrySet()) {
                                    for (int i = 0; i < entry.getValue().size(); i++) {
                                        currentTeacherCourses.add(entry.getValue().get(i));
                                    }
                                }
                                int k = 1;
                                for (Map.Entry<String, List<String>> entry : courses.entrySet()) {
                                    if (!currentTeacherCourses.contains(entry.getKey())) {
                                        System.out.println(k++ + " " + entry.getKey());
                                    }
                                }
                                if (k == 1) {
                                    System.out.println("No course is available currently! Ask admin to add more courses");
                                }
                                System.out
                                        .println("-----Enter the number of courses you want to register teacher for:");
                                int numberOfCourses = 0;
                                while (true) {
                                    boolean bool = false;
                                    try {
                                        numberOfCourses = sc.nextInt();
                                        bool = true;
                                        sc.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Please input integer value!");
                                        sc.nextLine();
                                    }

                                    if (bool) {
                                        break;
                                    }
                                }
                                System.out.println("Enter the courses for which you want to register teacher for: ");
                                for (int i = 0; i < numberOfCourses; i++) {
                                    String input = sc.nextLine();
                                    if (!courses.containsKey(input)) {
                                        System.out.println("Course is unavailable, try entering another");
                                        i--;
                                    } else {
                                        teacherCourses.add(input);
                                    }
                                }
                                admin.addTeacher(teacherUsername, teacherPassword, teacherFullName, teacherCourses);
                                System.out.println("Teacher with details added successfully");
                                login.populateAll();
                            } else if (inpChoice == 3) {
                                System.out.println(
                                        "-----Enter the username of the student for which the password is to be updated:");
                                String studentUsername = sc.next();
                                sc.nextLine();
                                if (!students.contains(studentUsername)) {
                                    System.out.println(
                                            "The student username does not exist in the database, kindly cross-verify");
                                } else {
                                    System.out.println("-----Enter the updated password for the student:");
                                    String updatedPassword = sc.next();
                                    sc.nextLine();
                                    admin.updateStudentPassword(studentUsername, updatedPassword);
                                }
                                System.out.println("Student password updated successfully!");
                            } else if (inpChoice == 4) {
                                System.out.println(
                                        "-----Enter the username of the student for which the full name is to be updated:");
                                String studentUsername = sc.next();
                                sc.nextLine();
                                if (!students.contains(studentUsername)) {
                                    System.out.println(
                                            "The student username does not exist in the database, kindly cross-verify");
                                } else {
                                    System.out.println("-----Enter the updated name for the student:");
                                    String updatedName = sc.nextLine();
                                    admin.updateStudentName(studentUsername, updatedName);
                                }
                                System.out.println("Student full name updated successfully!");
                            } else if (inpChoice == 5) {
                                System.out.println(
                                        "-----Enter the username of the student to be deleted from the database:");
                                String studentUsername = sc.next();
                                sc.nextLine();
                                if (!students.contains(studentUsername)) {
                                    System.out
                                            .println("The username does not exist in out records, kindly cross-verify");
                                } else {
                                    admin.deleteStudentDetails(studentUsername);
                                }
                                System.out.println("Student details deleted successfully!");
                                login.populateAll();
                            } else if (inpChoice == 6) {
                                System.out.println(
                                        "-----Enter the username of the teacher for which the password is to be updated:");
                                String teacherUsername = sc.next();
                                sc.nextLine();
                                if (!teachers.contains(teacherUsername)) {
                                    System.out.println(
                                            "The teacher username does not exist in the database, kindly cross-verify");
                                } else {
                                    System.out.println("-----Enter the updated password for the teacher:");
                                    String updatedPassword = sc.next();
                                    sc.nextLine();
                                    admin.updateTeacherPassword(teacherUsername, updatedPassword);
                                }
                                System.out.println("Teacher password updated successfully!");
                            } else if (inpChoice == 7) {
                                System.out.println(
                                        "-----Enter the username of the teacher for which the full name is to be updated:");
                                String teacherUsername = sc.next();
                                sc.nextLine();
                                if (!teachers.contains(teacherUsername)) {
                                    System.out.println(
                                            "The teacher username does not exist in the database, kindly cross-verify");
                                } else {
                                    System.out.println("-----Enter the updated full name for the teacher:");
                                    String updatedName = sc.nextLine();
                                    admin.updateTeacherName(teacherUsername, updatedName);
                                }
                                System.out.println("Teacher full name updated successfully!");
                            } else if (inpChoice == 8) {
                                System.out.println(
                                        "-----Enter the username of the teacher to be deleted from the database:");
                                String teacherUsername = sc.next();
                                sc.nextLine();
                                if (!teachers.contains(teacherUsername)) {
                                    System.out
                                            .println("The username does not exist in our records, kindly cross-verify");
                                } else {
                                    admin.deleteTeacherDetails(teacherUsername);
                                }
                                System.out.println("Teacher details deleted successfully!");
                                login.populateAll();
                            } else if (inpChoice == 9) {
                                boolean store = true;
                                System.out.println("-----Enter the username of the teacher:");
                                String teacherUsername = sc.next();
                                sc.nextLine();
                                if (!teachers.contains(teacherUsername)) {
                                    System.out
                                            .println("The username does not exist in our records, kindly cross-verify");
                                } else {
                                    HashSet<String> currentTeacherCourses = new HashSet<>();
                                    for (Map.Entry<String, List<String>> entry : coursesTeacher.entrySet()) {
                                        for (int i = 0; i < entry.getValue().size(); i++) {
                                            currentTeacherCourses.add(entry.getValue().get(i));
                                        }
                                    }
                                    System.out.println("Available Courses:");
                                    int k = 1;
                                    for (Map.Entry<String, List<String>> entry : courses.entrySet()) {
                                        if (!currentTeacherCourses.contains(entry.getKey())) {
                                            System.out.println(k++ + " " + entry.getKey());
                                        }
                                    }
                                    if (k == 1) {
                                        System.out.println(
                                                "No course is available currently! Ask admin to add more courses");
                                        store = false;
                                    }
                                    if (store) {
                                        ArrayList<String> teacherCourses = new ArrayList<>();
                                        System.out.println("-----Enter the number of courses you want to add:");
                                        int numberOfCourses = 0;
                                        while (true) {
                                            boolean bool = false;
                                            try {
                                                numberOfCourses = sc.nextInt();
                                                bool = true;
                                                sc.nextLine();
                                            } catch (Exception e) {
                                                System.out.println("Please input integer value!");
                                                sc.nextLine();
                                            }

                                            if (bool) {
                                                break;
                                            }
                                        }
                                        System.out.println("Enter the courses:");
                                        for (int i = 0; i < numberOfCourses; i++) {
                                            String line = sc.nextLine().toLowerCase(Locale.ROOT);
                                            for (Map.Entry<String, List<String>> entry : coursesTeacher.entrySet()) {
                                                if (entry.getValue().contains(line)) {
                                                    System.out.println(
                                                            "Please try adding another course! This course is already registered under some different teacher");
                                                    i--;
                                                } else {
                                                    teacherCourses.add(line);
                                                    break;
                                                }
                                            }
                                        }
                                        admin.addTeacherCourses(teacherUsername, teacherCourses);
                                        System.out.println("Courses added successfully!");
                                        System.out.println();
                                        System.out.println();
                                        login.populateAll();
                                    }
                                }
                            } else if (inpChoice == 10) {
                                System.out.println("-----Enter the username of the student:");
                                String studentUsername = sc.next();
                                sc.nextLine();
                                if (!students.contains(studentUsername)) {
                                    System.out
                                            .println("The username does not exist in our records, kindly cross-verify");
                                } else {
                                    HashSet<String> studentCoursesHashset = new HashSet<>();
                                    System.out.println("Available courses which can be added:");
                                    int k = 1;
                                    for (Map.Entry<String, List<String>> entry : courses.entrySet()) {
                                        if (entry.getValue().contains(studentUsername)) {
                                            continue;
                                        }
                                        System.out.println(k++ + " " + entry.getKey());
                                        studentCoursesHashset.add(entry.getKey());
                                    }
                                    ArrayList<String> studentCourses = new ArrayList<>();
                                    System.out.println("-----Enter the number of courses you want to add:");
                                    int numberOfCourses = 0;
                                    while (true) {
                                        boolean bool = false;
                                        try {
                                            numberOfCourses = sc.nextInt();
                                            bool = true;
                                            sc.nextLine();
                                        } catch (Exception e) {
                                            System.out.println("Please input integer value!");
                                            sc.nextLine();
                                        }

                                        if (bool) {
                                            break;
                                        }
                                    }
                                    System.out.println("-----Enter the courses:");
                                    for (int i = 0; i < numberOfCourses; i++) {
                                        String line = sc.nextLine().toLowerCase(Locale.ROOT);
                                        if (!studentCoursesHashset.contains(line) && courses.containsKey(line)) {
                                            System.out.println(
                                                    "Please try adding another course as student is already registered in this course!");
                                            i--;
                                        } else {
                                            if (courses.containsKey(line) && studentCoursesHashset.contains(line)) {
                                                studentCourses.add(line);
                                            } else {
                                                System.out.println("Please enter correct input!");
                                                i--;
                                            }
                                        }
                                    }
                                    admin.addStudentCourses(studentUsername, studentCourses);
                                    System.out.println("Courses added successfully!");
                                    System.out.println();
                                    System.out.println();
                                    login.populateAll();
                                }
                            } else if (inpChoice == 11) {
                                System.out.println("-----Enter the username of the teacher:");
                                String teacherUsername = sc.next();
                                sc.nextLine();
                                if (!teachers.contains(teacherUsername)) {
                                    System.out
                                            .println("The username does not exist in our records, kindly cross-verify");
                                } else {
                                    Scanner sc1 = null;
                                    File file1 = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
                                    try {
                                        sc1 = new Scanner(file1);
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    HashSet<String> registeredCourses = new HashSet<>();
                                    while (sc1.hasNextLine()) {
                                        String line = sc1.nextLine();
                                        String[] teacherDetails = line.split(":");
                                        if (teacherDetails[1].equals(teacherUsername)) {
                                            System.out.println("Registered courses of the teacher:");
                                            for (int i = 4; i < teacherDetails.length; i++) {
                                                System.out.println(teacherDetails[i]);
                                                registeredCourses.add(teacherDetails[i]);
                                            }
                                        }
                                    }
                                    ArrayList<String> teacherCourses = new ArrayList<>();
                                    System.out.println("-----Enter the number of courses you want to delete:");
                                    int numberOfCourses = 0;
                                    while (true) {
                                        boolean bool = false;
                                        try {
                                            numberOfCourses = sc.nextInt();
                                            bool = true;
                                            sc.nextLine();
                                        } catch (Exception e) {
                                            System.out.println("Please input integer value!");
                                            sc.nextLine();
                                        }

                                        if (bool) {
                                            break;
                                        }
                                    }
                                    System.out.println("Enter the courses:");
                                    for (int i = 0; i < numberOfCourses; i++) {
                                        String line = sc.nextLine().toLowerCase(Locale.ROOT);
                                        if (!registeredCourses.contains(line)) {
                                            System.out.println(
                                                    "Please try deleting another course! This course is not registered under the teacher");
                                        } else {
                                            teacherCourses.add(line);
                                        }
                                    }
                                    admin.deleteTeacherCourses(teacherUsername, teacherCourses);
                                    System.out.println("Courses deleted successfully!");
                                    System.out.println();
                                    System.out.println();
                                    login.populateAll();
                                }
                            } else if (inpChoice == 12) {
                                System.out.println("-----Enter the username of the student:");
                                String studentUsername = sc.next();
                                sc.nextLine();
                                if (!students.contains(studentUsername)) {
                                    System.out
                                            .println("The username does not exist in our records, kindly cross-verify");
                                } else {
                                    Scanner sc1 = null;
                                    File file1 = new File(System.getProperty("user.dir") + "/src/Student.txt");
                                    try {
                                        sc1 = new Scanner(file1);
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    HashSet<String> registeredCourses = new HashSet<>();
                                    while (sc1.hasNextLine()) {
                                        String line = sc1.nextLine();
                                        String[] studentDetails = line.split(":");
                                        if (studentDetails[1].equals(studentUsername)) {
                                            System.out.println("Registered courses of the student:");
                                            int k = 1;
                                            for (int i = 4; i < studentDetails.length; i++) {
                                                String[] studentCourseDetails = studentDetails[i].split(" ");
                                                System.out.println(k++ + " " + studentCourseDetails[0]);
                                                registeredCourses.add(studentCourseDetails[0]);
                                            }
                                        }
                                    }
                                    ArrayList<String> studentCourses = new ArrayList<>();
                                    System.out.println("-----Enter the number of courses you want to delete:");
                                    int numberOfCourses = 0;
                                    while (true) {
                                        boolean bool = false;
                                        try {
                                            numberOfCourses = sc.nextInt();
                                            bool = true;
                                            sc.nextLine();
                                        } catch (Exception e) {
                                            System.out.println("Please input integer value!");
                                            sc.nextLine();
                                        }

                                        if (bool) {
                                            break;
                                        }
                                    }
                                    System.out.println("Enter the courses:");
                                    for (int i = 0; i < numberOfCourses; i++) {
                                        String line = sc.nextLine().toLowerCase(Locale.ROOT);
                                        if (!registeredCourses.contains(line)) {
                                            System.out.println(
                                                    "Please try deleting another course! Student is not registered in this course");
                                        } else {
                                            studentCourses.add(line);
                                        }
                                    }
                                    admin.deleteStudentCourses(studentUsername, studentCourses);
                                    System.out.println("Courses deleted successfully!");
                                    System.out.println();
                                    System.out.println();
                                    login.populateAll();
                                }
                            } else if (inpChoice == 13) {
                                System.out.println("-----Enter the number of courses you want to add: ");
                                int numberOfCourses = 0;
                                while (true) {
                                    boolean bool = false;
                                    try {
                                        numberOfCourses = sc.nextInt();
                                        bool = true;
                                        sc.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Please input integer value!");
                                        sc.nextLine();
                                    }

                                    if (bool) {
                                        break;
                                    }
                                }
                                ArrayList<String> coursesNew = new ArrayList<>();
                                System.out.println("Enter courses: ");
                                for (int i = 0; i < numberOfCourses; i++) {
                                    String input = sc.nextLine();
                                    if (courses.containsKey(input)) {
                                        System.out.println("Course is already present! Please input another");
                                    }
                                    coursesNew.add(input);
                                }
                                admin.addCourses(coursesNew, courses.size() + 1);
                                System.out.println("Courses added successfully!");
                                System.out.println();
                                login.populateAll();
                            } else if (inpChoice == 14) {
                                System.out.println("-----Enter the number of courses you want to delete: ");
                                int numberOfCourses = 0;
                                while (true) {
                                    boolean bool = false;
                                    try {
                                        numberOfCourses = sc.nextInt();
                                        bool = true;
                                        sc.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Please input integer value!");
                                        sc.nextLine();
                                    }

                                    if (bool) {
                                        break;
                                    }
                                }
                                ArrayList<String> coursesDelete = new ArrayList<>();
                                System.out.println("Enter courses: ");
                                for (int i = 0; i < numberOfCourses; i++) {
                                    String input = sc.nextLine();
                                    if (!courses.containsKey(input)) {
                                        System.out.println("Course is not present! Please input another");
                                    }
                                    coursesDelete.add(input);
                                }
                                admin.deleteCourses(coursesDelete);
                                login.populateAll();
                            } else if (inpChoice == 15) {
                                System.out.println("Total working days: ");
                                System.out.println(admin.days());
                                System.out.println("Enter the updated value: ");
                                double updatedDays = sc.nextDouble();
                                admin.updateDays(updatedDays);
                            } else if (inpChoice == 16) {
                                System.out.println("Total marks of a course: ");
                                System.out.println(admin.marks());
                                System.out.println("Enter the updated value: ");
                                double updatedMarks = sc.nextDouble();
                                admin.updateMarks(updatedMarks);
                            } else if (inpChoice == 17) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("The credentials entered seem incorrect, please cross-verify");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (Exception e) {

                        }
                        ;
                    }
                }
            } else if (choice == 2) {
                Login.clearScreen();
                System.out.println("-----Enter Username:");
                String username = sc.next();
                sc.nextLine();
                System.out.println("-----Enter Password:");
                String password = sc.next();
                sc.nextLine();
                try {
                    File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
                    br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] details = line.split(":");
                        if (details[1].equals(username) && details[2].equals(password)) {
                            System.out.println("Welcome " + details[3]);
                            while (true) {
                                Student student = new Student(username, password);
                                System.out.println("-----To get all the courses                 :  input 1");
                                System.out.println("-----To get attendance of all the courses   :  input 2");
                                System.out.println("-----To get attendance of a specific course :  input 3");
                                System.out.println("-----To get marks of all the courses:       :  input 4");
                                System.out.println("-----To get marks of a specific course:     :  input 5");
                                System.out.println("-----To return to the Main menu             :  input 6");
                                int inpChoice = 0;
                                while (true) {
                                    boolean bool = false;
                                    try {
                                        inpChoice = sc.nextInt();
                                        if (inpChoice < 1 || inpChoice > 6) {
                                            bool = false;
                                            System.out.println("Please input appropriate value!");
                                        } else {
                                            bool = true;
                                        }
                                        sc.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Please input integer value!");
                                        sc.nextLine();
                                    }

                                    if (bool) {
                                        break;
                                    }
                                }
                                if (inpChoice == 2) {
                                    System.out.println();
                                    System.out.println("Attendance in courses registered under student: ");
                                    student.getAttendanceAllCourses();
                                    System.out.println();
                                } else if (inpChoice == 3) {
                                    HashSet<String> coursesStudent = new HashSet<>();
                                    System.out.println("Courses: ");
                                    System.out.println();
                                    int k = 1;
                                    for (int i = 4; i < details.length; i++) {
                                        String[] courseDetails = details[i].split(" ");
                                        System.out.println(k++ + " " + courseDetails[0]);
                                        coursesStudent.add(courseDetails[0]);
                                    }
                                    System.out.println(
                                            "-----Enter the course name out of the following mentioned courses:");
                                    for (int i = 0; i < 1; i++) {
                                        String courseName = sc.nextLine().toLowerCase(Locale.ROOT);
                                        if (coursesStudent.contains(courseName)) {
                                            student.getAttendanceSpecificCourse(courseName);
                                            System.out.println();
                                        } else if (!coursesStudent.contains(courseName)
                                                && courses.containsKey(courseName)) {
                                            System.out.println(
                                                    "Student in not registered in this course! Please input correct one");
                                            i--;
                                        } else {
                                            System.out.println("Please input correct course name!");
                                            i--;
                                        }
                                    }
                                } else if (inpChoice == 1) {
                                    System.out.println();
                                    System.out.println("Registered courses: ");
                                    student.getCourses();
                                    System.out.println();
                                } else if (inpChoice == 4) {
                                    System.out.println();
                                    System.out.println("Registered courses");
                                    student.getMarksAllCourses();
                                    System.out.println();
                                } else if (inpChoice == 5) {
                                    HashSet<String> coursesStudent = new HashSet<>();
                                    System.out.println("Courses: ");
                                    System.out.println();
                                    int k = 1;
                                    for (int i = 4; i < details.length; i++) {
                                        String[] courseDetails = details[i].split(" ");
                                        System.out.println(k++ + " " + courseDetails[0]);
                                        coursesStudent.add(courseDetails[0]);
                                    }
                                    System.out.println(
                                            "-----Enter the course name out of the following mentioned courses:");
                                    for (int i = 0; i < 1; i++) {
                                        String courseName = sc.next().toLowerCase(Locale.ROOT);
                                        sc.nextLine();
                                        if (coursesStudent.contains(courseName)) {
                                            student.getMarksSpecificCourse(courseName);
                                            System.out.println();
                                        } else if (!coursesStudent.contains(courseName)
                                                && courses.containsKey(courseName)) {
                                            System.out.println(
                                                    "Student in not registered in this course! Please input correct one");
                                            i--;
                                        } else {
                                            System.out.println("Please input correct course name!");
                                            i--;
                                        }
                                    }
                                } else if (inpChoice == 6) {
                                    break;
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (choice == 3) {
                System.out.println("-----Enter Username:");
                String username = sc.next();
                sc.nextLine();
                System.out.println("-----Enter Password");
                String password = sc.next();
                sc.nextLine();
                try {
                    File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
                    br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] details = line.split(":");
                        if (details[1].equals(username) && details[2].equals(password)) {
                            System.out.println();
                            System.out.println("Welcome " + details[3]);
                            while (true) {
                                Teacher teacher = new Teacher(username, password);
                                System.out.println("-----To get all the courses            : input 1");
                                System.out.println("-----To set marks of a course          : input 2");
                                System.out.println("-----To set attendance of a course     : input 3");
                                System.out.println("-----To find the students registered   : input 4");
                                System.out.println("-----To logout                         : input 5");
                                int inpChoice = 0;
                                while (true) {
                                    boolean bool = false;
                                    try {
                                        inpChoice = sc.nextInt();
                                        if (inpChoice < 1 || inpChoice > 5) {
                                            bool = false;
                                            System.out.println("Please input appropriate value!");
                                        } else {
                                            bool = true;
                                        }
                                        sc.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Please input integer value!");
                                        sc.nextLine();
                                    }

                                    if (bool) {
                                        break;
                                    }
                                }
                                if (inpChoice == 1) {
                                    System.out.println();
                                    System.out.println("Registered courses: ");
                                    teacher.getCourses();
                                    System.out.println();
                                } else if (inpChoice == 2) {
                                    HashSet<String> coursesTeacher = new HashSet<>();
                                    System.out.println();
                                    System.out.println("-----Available Courses: ");
                                    int k = 1;
                                    for (int i = 4; i < details.length; i++) {
                                        System.out.println(k++ + " " + details[i]);
                                        coursesTeacher.add(details[i]);
                                    }
                                    for (int i = 0; i < 1 && k != 1; i++) {
                                        System.out.println("-----Enter course name:");
                                        String courseName = sc.nextLine().toLowerCase(Locale.ROOT);
                                        if (coursesTeacher.contains(courseName)) {
                                            System.out.println("-----Students registered in the course: ");
                                            System.out.println(courses.get(courseName));
                                            System.out.println("Select student out of the mentioned ones: ");
                                            String studentUserName = sc.next();
                                            sc.nextLine();
                                            File file1 = new File(System.getProperty("user.dir") + "/src/Student.txt");
                                            BufferedReader br1 = new BufferedReader(new FileReader(file1));
                                            String line1;
                                            while ((line1 = br1.readLine()) != null) {
                                                String[] detailss = line1.split(":");
                                                if (detailss[1].equals(studentUserName)) {
                                                    for (int j = 4; j < detailss.length; j++) {
                                                        String[] temp = detailss[j].split(" ");
                                                        if (temp[0].equals(courseName)) {
                                                            System.out.println("Current marks in the course: ");
                                                            System.out.println(temp[1]);
                                                        }
                                                    }
                                                }
                                            }
                                            br1.close();
                                            System.out.println("-----Enter Marks: ");
                                            int marks = 0;
                                            while (true) {
                                                boolean bool = false;
                                                try {
                                                    marks = sc.nextInt();
                                                    bool = true;
                                                    sc.nextLine();
                                                } catch (Exception e) {
                                                    System.out.println("Please input integer value!");
                                                    sc.nextLine();
                                                }

                                                if (bool) {
                                                    break;
                                                }
                                            }
                                            teacher.setMarks(studentUserName, courseName, marks);
                                            System.out.println("Marks updated successfully!");
                                            System.out.println();
                                        } else if (courses.containsKey(courseName)
                                                && !coursesTeacher.contains(courseName)) {
                                            System.out.println("Teacher not registered in this course!");
                                            i--;
                                        } else {
                                            System.out.println("Please input correct course name!");
                                            i--;
                                        }
                                    }
                                } else if (inpChoice == 3) {
                                    HashSet<String> coursesTeacher = new HashSet<>();
                                    System.out.println();
                                    System.out.println("-----Available Courses: ");
                                    int k = 1;
                                    for (int i = 4; i < details.length; i++) {
                                        System.out.println(k++ + " " + details[i]);
                                        coursesTeacher.add(details[i]);
                                    }
                                    for (int i = 0; i < 1; i++) {
                                        System.out.println("-----Enter course name:");
                                        String courseName = sc.nextLine().toLowerCase(Locale.ROOT);
                                        if (coursesTeacher.contains(courseName)) {
                                            System.out.println("-----Students registered in the course: ");
                                            System.out.println(courses.get(courseName));
                                            System.out.println("Select student out of the mentioned ones: ");
                                            String studentUserName = sc.next();
                                            sc.nextLine();
                                            File file1 = new File(System.getProperty("user.dir") + "/src/Student.txt");
                                            BufferedReader br1 = new BufferedReader(new FileReader(file1));
                                            String line1;
                                            while ((line1 = br1.readLine()) != null) {
                                                String[] detailss = line1.split(":");
                                                if (detailss[1].equals(studentUserName)) {
                                                    for (int j = 4; j < detailss.length; j++) {
                                                        String[] temp = detailss[j].split(" ");
                                                        if (temp[0].equals(courseName)) {
                                                            System.out.println("Current attendance in the course: ");
                                                            System.out.println(temp[2]);
                                                        }
                                                    }
                                                }
                                            }
                                            br1.close();
                                            System.out.println("-----Enter Attendance: ");
                                            int attendance = 0;
                                            while (true) {
                                                boolean bool = false;
                                                try {
                                                    attendance = sc.nextInt();
                                                    bool = true;
                                                    sc.nextLine();
                                                } catch (Exception e) {
                                                    System.out.println("Please input integer value!");
                                                    sc.nextLine();
                                                }

                                                if (bool) {
                                                    break;
                                                }
                                            }
                                            teacher.setAttendance(studentUserName, courseName, attendance);
                                            System.out.println("Attendance updated successfully!");
                                            System.out.println();
                                        } else if (courses.containsKey(courseName)
                                                && !coursesTeacher.contains(courseName)) {
                                            System.out.println("Teacher not registered in this course!");
                                            i--;
                                        } else {
                                            System.out.println("Please input correct course name!");
                                            i--;
                                        }
                                    }
                                } else if (inpChoice == 4) {
                                    teacher.displayTeacherStudentRelation();
                                }

                                else if (inpChoice == 5) {
                                    break;
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (choice == 4) {
                // Login.clearScreen();
                System.exit(0);
            }
        }
    }
}