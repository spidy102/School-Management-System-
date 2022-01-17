import java.io.*;

public class Student extends User{
    private String username;
    public Student(String username, String password) {
        super(username, password, "student");
        this.username = username;
    }

    public void getAttendanceAllCourses() throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
            br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                String[] details = line.split(":");
                if (details[1].equals(username)) {
                    int k = 1;
                    for (int i = 4; i < details.length; i++) {
                        String[] courseDetails = details[i].split(" ");
                        double percentageAttendance = Double.parseDouble(courseDetails[2]) / TOTAL_DAYS;
                        String result = String.format("%.2f", percentageAttendance * 100);
                        System.out.println(k++ + " " + courseDetails[0] + " " + Integer.parseInt(courseDetails[2]) + " " + result + "%");
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

    public void getAttendanceSpecificCourse(String courseName) throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                String[] details = line.split(":");
                if (details[1].equals(username)) {
                    for (int i = 4; i < details.length; i++) {
                        String[] courseDetails = details[i].split(" ");
                        if (courseDetails[0].equals(courseName)) {
                            double percentageAttendance = Double.parseDouble(courseDetails[2]) / TOTAL_DAYS;
                            String result = String.format("%.2f", percentageAttendance * 100);
                            System.out.println(courseName + " " + Integer.parseInt(courseDetails[2]) + " " + result + "%");
                        }
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

    public void getCourses() throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                String[] details = line.split(":");
                if (details[1].equals(username)) {
                    int k = 1;
                    for (int i = 4; i < details.length; i++) {
                        String[] courseDetails = details[i].split(" ");
                        System.out.println(k++ + " " + courseDetails[0]);
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

    public void getMarksSpecificCourse(String courseName) throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                String[] details = line.split(":");
                if (details[1].equals(username)) {
                    for (int i = 4; i < details.length; i++) {
                        String[] courseDetails = details[i].split(" ");
                        if (courseDetails[0].equals(courseName)) {
                            double percentageMarks = Double.parseDouble(courseDetails[1]) / TOTAL_MARKS;
                            String result = String.format("%.2f", percentageMarks * 100);
                            System.out.println(courseName + " " + courseDetails[1] + " " + result + "%");
                        }
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

    public void getMarksAllCourses() throws IOException {
        BufferedReader br = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/Student.txt");
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                String[] details = line.split(":");
                if (details[1].equals(username)) {
                    for (int i = 4; i < details.length; i++) {
                        String[] courseDetails = details[i].split(" ");
                        double percentageMarks = Double.parseDouble(courseDetails[1]) / TOTAL_MARKS;
                        String result = String.format("%.2f", percentageMarks * 100);
                        System.out.println(courseDetails[0] + " " + Integer.parseInt(courseDetails[1]) + " " + result + "%");
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
}
