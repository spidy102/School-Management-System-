import java.io.IOException;
import java.util.HashMap;
import java.util.List;

interface Course {
    HashMap<String, List<String>> studentTeachers = new HashMap<>();
    HashMap<String, List<String>> teacherCourses = new HashMap<>();

    void populateHashmap() throws IOException;
}