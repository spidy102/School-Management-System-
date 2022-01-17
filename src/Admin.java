import java.io.*;
import java.util.*;

public class Admin extends User {
    private final HashSet<String> students;
    private final HashSet<String> teachers;
    private final HashMap<String, List<String>> courseStudents;

    public Admin(String username, String password, HashSet<String> students, HashSet<String> teachers,
                 HashMap<String, List<String>> courses) {
        super(username, password, "admin");
        this.students = students;
        this.teachers = teachers;
        this.courseStudents = courses;
    }

    public int addCourses(ArrayList<String> courses, int currentTotalCourses) {// method returns the new number of
        // courses in the
        // database
        File file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
        try {
            FileWriter fw = new FileWriter(file, true);
            String buffer = "";
            for (int i = 0; i < courses.size(); i++) {
                buffer += String.valueOf(currentTotalCourses) + ":" + courses.get(i);
                currentTotalCourses++;
                buffer += "\n";
            }
            fw.append(buffer);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentTotalCourses + courses.size();
    }

    public void deleteCourses(ArrayList<String> courses) throws IOException {
        HashSet<String> courseList = new HashSet<>();
        for (int i = 0; i < courses.size(); i++) {
            courseList.add(courses.get(i));
        }
        File file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> buffer = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!courseList.contains(line.split(":")[1])) {
                buffer.add(line);
            }
        }
        for (int i = 0; i < buffer.size(); i++) {
            String line = buffer.get(i);
            String lineArr[] = line.split(":");
            lineArr[0] = String.valueOf(i + 1);
            buffer.set(i, String.join(":", lineArr));
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(String.join("\n", buffer) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        buffer = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String lineArr[] = line.split(":");
            List<String> userCreds = Arrays.asList(Arrays.copyOfRange(lineArr, 0, 4));
            List<String> courseListCurrent = Arrays.asList(Arrays.copyOfRange(lineArr, 4, lineArr.length));
            List<String> updatedCourseList = new ArrayList<>();
            for (String curCourse : courseListCurrent) {
                if (!courses.contains(curCourse.split(" ")[0])) {
                    updatedCourseList.add(curCourse);
                }
            }

            buffer.add(String.join(":", userCreds) + ":" + String.join(":", updatedCourseList));

        }

        fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(String.join("\n", buffer) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        buffer = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String lineArr[] = line.split(":");
            List<String> userCreds = Arrays.asList(Arrays.copyOfRange(lineArr, 0, 3));
            List<String> courseListCurrent = Arrays.asList(Arrays.copyOfRange(lineArr, 3, lineArr.length));
            List<String> updatedCourseList = new ArrayList<>();
            for (String curCourse : courseListCurrent) {
                if (!courses.contains(curCourse)) {
                    updatedCourseList.add(curCourse);
                }
            }

            buffer.add(String.join(":", userCreds) + ":" + String.join(":", updatedCourseList));

        }

        fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(String.join("\n", buffer));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

    }

    public void addStudent(String userName, String password, String name, ArrayList<String> courses)
            throws IOException {
        FileWriter fw = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
            fw = new FileWriter(file, true);
            List<String> newStudent = new ArrayList<>();
            newStudent.add(String.valueOf((students.size() + 1)));
            newStudent.add(userName);
            newStudent.add(password);
            newStudent.add(name);
            for (String cours : courses) {
                newStudent.add(cours + " 0 0");
            }
            String finalDetails = String.join(":", newStudent);
            finalDetails += "\n";
            // System.out.println(finalDetails);
            fw.append(finalDetails);
            // students.add(userName); // Login initialize method can be replaced with this
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        String buffer = "";
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            buffer = "";
            while ((line = br.readLine()) != null) {
                String[] abc = line.split(":");
                if (courses.contains(abc[1])) {
                    buffer += line;
                    buffer += ":";
                    buffer += userName;
                    buffer += "\n";
                } else {
                    buffer += line;
                    buffer += "\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        fw = null;
        try {
            fw = new FileWriter(new File(System.getProperty("user.dir") + "/src/Courses.txt"));
            fw.write(buffer.toCharArray());
            for (int i = 0; i < courses.size(); i++) {
                courseStudents.get(courses.get(i)).add(userName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void addTeacher(String userName, String password, String name, ArrayList<String> courses)
            throws IOException {
        FileWriter fw = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
            fw = new FileWriter(file, true);
            List<String> newTeacher = new ArrayList<>();
            newTeacher.add(String.valueOf((teachers.size() + 1)));
            newTeacher.add(userName);
            newTeacher.add(password);
            newTeacher.add(name);
            for (String cours : courses) {
                newTeacher.add(cours);
            }
            String finalDetails = String.join(":", newTeacher);
            finalDetails += "\n";
            // System.out.println(finalDetails);
            fw.append(finalDetails);
            // teachers.add(userName); // Login initialize method can be replaced with this
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void updateStudentPassword(String userName, String updatedPassword) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc != null && sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] studentDetails = line.split(":");
            if (studentDetails[1].equals(userName)) {
                studentDetails[2] = updatedPassword;
                String updatedStudentDetails = String.join(":", studentDetails);
                buffer += updatedStudentDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void updateStudentName(String userName, String updatedName) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] studentDetails = line.split(":");
            if (studentDetails[1].equals(userName)) {
                studentDetails[3] = updatedName;
                String updatedStudentDetails = String.join(":", studentDetails);
                buffer += updatedStudentDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void deleteStudentDetails(String userName) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index = 0;
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String studentDetails[] = line.split(":");
            if (studentDetails[1].equals(userName)) {
                continue;
            } else {
                index++;
                studentDetails[0] = String.valueOf(index);
                String updatedStudentDetails = String.join(":", studentDetails);
                buffer += updatedStudentDetails;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> bufferx = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String lineArr[] = line.split(":");
            List<String> courseName = Arrays.asList(Arrays.copyOfRange(lineArr, 0, 2));
            List<String> studentListCurrent = Arrays.asList(Arrays.copyOfRange(lineArr, 2, lineArr.length));
            List<String> updatedStudentList = new ArrayList<>();
            for (String curUsername : studentListCurrent) {
                if (!userName.equals(curUsername)) {
                    updatedStudentList.add(curUsername);
                }
            }

            bufferx.add(String.join(":", courseName) + ":" + String.join(":", updatedStudentList));

        }

        fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(String.join("\n", bufferx));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

    }

    public void updateTeacherPassword(String userName, String updatedPassword) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] teacherDetails = line.split(":");
            if (teacherDetails[1].equals(userName)) {
                teacherDetails[2] = updatedPassword;
                String updatedTeacherDetails = String.join(":", teacherDetails);
                buffer += updatedTeacherDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void updateTeacherName(String userName, String updatedName) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] teacherDetails = line.split(":");
            if (teacherDetails[1].equals(userName)) {
                teacherDetails[3] = updatedName;
                String updatedTeacherDetails = String.join(":", teacherDetails);
                buffer += updatedTeacherDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void deleteTeacherDetails(String userName) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index = 0;
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String teacherDetails[] = line.split(":");
            if (teacherDetails[1].equals(userName)) {
                continue;
            } else {
                index++;
                teacherDetails[0] = String.valueOf(index);
                String updatedTeacherDetails = String.join(":", teacherDetails);
                buffer += updatedTeacherDetails;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void addTeacherCourses(String userName, ArrayList<String> courses) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] teacherDetails = line.split(":");
            ArrayList<String> teacherArray = new ArrayList<>();
            teacherArray.addAll(List.of(teacherDetails));
            HashSet<String> currentCourses = new HashSet<>();
            for (int i = 4; i < teacherDetails.length; i++) {
                currentCourses.add(teacherDetails[i]);
            }
            if (teacherDetails[1].equals(userName)) {
                for (int i = 0; i < courses.size(); i++) {
                    if (currentCourses.contains(courses.get(i))) {
                        System.out.println(courses.get(i) + " is already present! Try Adding Different Course.");
                    } else {
                        teacherArray.add(courses.get(i));
                        System.out.println(courses.get(i) + " added successfully!");
                    }
                }
                String finalTeacherDetails = String.join(":", teacherArray);
                buffer += finalTeacherDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void addStudentCourses(String userName, ArrayList<String> courses) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] studentDetails = line.split(":");
            ArrayList<String> studentArray = new ArrayList<>();
            studentArray.addAll(List.of(studentDetails));
            // HashSet<String> currentCourses = new HashSet<>();
            // for (int i = 4; i < studentDetails.length; i++) {
            // String[] studentCourseDetails = studentDetails[i].split(" ");
            // currentCourses.add(studentCourseDetails[0]);
            // }
            if (studentDetails[1].equals(userName)) {
                for (int i = 0; i < courses.size(); i++) {
                    // if (currentCourses.contains(courses.get(i))) {
                    // System.out.println(courses.get(i) + " is already present! Try Adding
                    // Different Course.");
                    // } else {
                    studentArray.add(courses.get(i) + " 0 0");
                    System.out.println(courses.get(i) + " added successfully!");
                    // }
                }
                String finalStudentDetails = String.join(":", studentArray);
                buffer += finalStudentDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
        BufferedReader br = null;
        String bufferx = "";
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(":");
                if (courses.contains(split[1])) {
                    bufferx += line;
                    bufferx += ":";
                    bufferx += userName;
                    bufferx += "\n";
                } else {
                    bufferx += line;
                    bufferx += "\n";
                }
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
        fw = null;
        try {
            fw = new FileWriter(System.getProperty("user.dir") + "/src/Courses.txt");
            fw.write(bufferx.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void deleteTeacherCourses(String userName, ArrayList<String> courses) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] teacherDetails = line.split(":");
            ArrayList<String> teacherArray = new ArrayList<>();
            teacherArray.addAll(List.of(teacherDetails));
            HashSet<String> currentCourses = new HashSet<>();
            for (int i = 4; i < teacherDetails.length; i++) {
                currentCourses.add(teacherDetails[i]);
            }
            if (teacherDetails[1].equals(userName)) {
                for (int i = 0; i < courses.size(); i++) {
                    if (currentCourses.contains(courses.get(i))) {
                        System.out.println(courses.get(i) + " deleted successfully!");
                        teacherArray.remove(courses.get(i));
                        currentCourses.remove(courses.get(i));
                        continue;
                    } else {
                        System.out.println(courses.get(i) + " is not vaild! Try deleting another course.");
                    }
                }
                String finalTeacherDetails = String.join(":", teacherArray);
                buffer += finalTeacherDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public void deleteStudentCourses(String userName, ArrayList<String> courses) throws IOException {
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] studentDetails = line.split(":");
            ArrayList<String> studentArray = new ArrayList<>();
            studentArray.addAll(List.of(studentDetails));
            HashSet<String> currentCourses = new HashSet<>();
            for (int i = 4; i < studentDetails.length; i++) {
                String[] studentCourseDetails = studentDetails[i].split(" ");
                currentCourses.add(studentCourseDetails[0]);
            }
            if (studentDetails[1].equals(userName)) {
                for (int i = 0; i < courses.size(); i++) {
                    if (currentCourses.contains(courses.get(i))) {
                        System.out.println(courses.get(i) + " deleted successfully!");
                        for (int j = 0; j < studentArray.size(); j++) {
                            String[] courseDetails = studentArray.get(j).split(" ");
                            if (courseDetails[0].equals(courses.get(i))) {
                                studentArray.remove(j);
                            }
                        }
                        currentCourses.remove(courses.get(i));
                        continue;
                    } else {
                        System.out.println(courses.get(i)
                                + " is either not vaild or does not exist! Try deleting another course.");
                    }
                }
                String finalStudentDetails = String.join(":", studentArray);
                buffer += finalStudentDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        file = new File(System.getProperty("user.dir") + "/src/Courses.txt");
        BufferedReader br = null;
        String bufferx = "";
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(":");
                if (courses.contains(split[1])) {
                    ArrayList<String> studentsList = new ArrayList<String>();
                    for (int i = 2; i < split.length; i++) {
                        if (!split[i].equals(userName)) {
                            studentsList.add(split[i]);
                        }
                    }
                    bufferx += String.join(":", Arrays.copyOfRange(split, 0, 2));
                    bufferx += ":";
                    bufferx += String.join(":", studentsList);
                    bufferx += "\n";
                } else {
                    bufferx += line;
                    bufferx += "\n";
                }
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
        fw = null;
        try {
            fw = new FileWriter(new File(System.getProperty("user.dir") + "/src/Courses.txt"));
            fw.write(bufferx.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public double days() {
        return TOTAL_DAYS;
    }

    public double marks() {
        return TOTAL_MARKS;
    }

    public void updateDays(double updatedDays) {
        TOTAL_DAYS = updatedDays;
    }

    public void updateMarks(double updatedMarks) {
        TOTAL_MARKS = updatedMarks;
    }
}