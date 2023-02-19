public class Course {
    private final String name;
    private final Institution institution;

    public Course(String name, Institution institution) {
        this.name = name;
        this.institution = institution;
    }

    public String getName() {
        return name;
    }

    public Institution getInstitution() {
        return institution;
    }

    Object getCourseStudents() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}