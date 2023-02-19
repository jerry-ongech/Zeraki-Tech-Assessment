
import java.util.ArrayList;
import java.util.List;

public class Institution {
    private final String name;
    private final List<Course> courses;

    public Institution(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (courses.stream().anyMatch(c -> c.getName().equals(course.getName()))) {
            throw new IllegalArgumentException("Course with the same name already exists in this institution.");
        }

        courses.add(course);
    }

    public void removeCourse(Course course) {
        if (course.getInstitution() != this) {
            throw new IllegalArgumentException("This course does not belong to this institution.");
        }

        if (course.getCourseStudents().size() > 0) {
            throw new IllegalArgumentException("This course has students assigned to it and cannot be deleted.");
        }

        courses.remove(course);
    }
}

