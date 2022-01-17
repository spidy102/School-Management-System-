import java.io.*;
import java.util.*;

public class Teacher extends User implements Course{
    private String username;
    private HashMap<String, List<String>> teacherStudents;

    public Teacher(String username, String password) throws IOException {
        super(username, password, "teacher");
        this.username = username;
        populateHashmap();
    }

    public void getCourses() throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Teacher.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(":");
                int k = 1;
                if (details[1].equals(username)) {
                    for (int i = 4; i < details.length; i++) {
                        System.out.println(k++ + " " + details[i]);
                    }
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
    }

    public void setMarks(String studentUsername, String courseName, int marks) {
//        System.out.println("Inside set");
        Scanner sc = null;
        File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("Processing");
        String buffer = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] details = line.split(":");
            if (details[1].equals(studentUsername)) {
                for (int i = 4; i < details.length; i++) {
                    String[] courseDetails = details[i].split(" ");
                    if (courseDetails[0].equals(courseName)) {
                        courseDetails[1] = String.valueOf(marks);
                    }
                    String updatedCourseDetails = String.join(" ", courseDetails);
                    details[i] = updatedCourseDetails;
                }
                String finalDetails = String.join(":", details);
                buffer += finalDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }
//        System.out.println("FileWriter Process");
        FileWriter fw = null;
        try {
            fw = new FileWriter(System.getProperty("user.dir") + "/src/Student.txt");
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println("Done");
    }

    public void setAttendance(String studentUsername, String courseName, int attendance) {
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
            String[] details = line.split(":");
            if (details[1].equals(studentUsername)) {
                for (int i = 4; i < details.length; i++) {
                    String[] courseDetails = details[i].split(" ");
                    if (courseDetails[0].equals(courseName)) {
                        courseDetails[2] = String.valueOf(attendance);
                    }
                    String updatedCourseDetails = String.join(" ", courseDetails);
                    details[i] = updatedCourseDetails;
                }
                String finalDetails = String.join(":", details);
                buffer += finalDetails;
                buffer += "\n";
            } else {
                buffer += line;
                buffer += "\n";
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(System.getProperty("user.dir") + "/src/Student.txt");
            fw.write(buffer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void populateHashmap() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/src/Courses.txt");
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] lineArr = line.split(":");
            String currentCourse = lineArr[1];
            String[] studentsList = Arrays.copyOfRange(lineArr, 2,lineArr.length);
            File file1 = new File(System.getProperty("user.dir")+"/src/Teacher.txt");
            Scanner sc1 = new Scanner(file1);
            while (sc1.hasNextLine()){
                String line1 = sc1.nextLine();
                String[] line1Arr = line1.split(":");
                if (line1Arr.length>4){
                    for (int i=4;i<line1Arr.length;i++){
                        if (line1Arr[i].equals(currentCourse)){
                            studentTeachers.put(line1Arr[1], List.of(studentsList));
                        }
                    }
                } else {
                    continue;
                }
            }
            sc1.close();
        }
        sc.close();
        this.teacherStudents = studentTeachers;
    }

    public void displayTeacherStudentRelation() {
        System.out.println("List of students registered in different courses: ");
        boolean bool = false;
        for (Map.Entry<String, List<String>> entry: teacherStudents.entrySet()) {
            if (entry.getKey().equals(username)) {
                bool = true;
                System.out.println(Arrays.toString(entry.getValue().toArray()));
            }
        }
        if (!bool) {
            System.out.println("No students are registered currently!");
        }
    }

    public void updateTotalMarks(int updatedMarks) {
        TOTAL_MARKS = updatedMarks;
    }

    public void updateTotalWorkingDays(int updatedDays) {
        TOTAL_DAYS = updatedDays;
    }
}
