
public class Student {
    private String name;
    private Course course;

    public Student(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (this.course == course) {
            return;
        }

        if (this.course != null && this.course.getCourseStudents().size() == 1) {
            throw new IllegalArgumentException("This student is the only student assigned to their current course and cannot be unassigned.");
        }

        if (course.getCourseStudents().stream().anyMatch(s -> s != this)) {
            throw new IllegalArgumentException("This course already has a student assigned to it.");
        }

        this.course.removeStudent(this);
        this.course = course;
        course.addStudent(this);
    }

    public void transferCourse(Course course) {
        if (this.course.getInstitution() != course.getInstitution()) {
            throw new IllegalArgumentException("Cannot transfer to a course in a different institution.");
        }


