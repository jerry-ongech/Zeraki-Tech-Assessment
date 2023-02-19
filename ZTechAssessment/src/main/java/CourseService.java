import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByInstitution(Long institutionId) {
        Optional<Institution> institutionOptional = institutionRepository.findById(institutionId);
        if (institutionOptional.isPresent()) {
            return institutionOptional.get().getCourses();
        } else {
            throw new EntityNotFoundException("Institution not found");
        }
    }

    public List<Course> searchCourses(String keyword) {
        return courseRepository.searchCourses(keyword);
    }

    public List<Course> sortCoursesByName(List<Course> courses, String order) {
        if (order.equals("asc")) {
            courses.sort(Comparator.comparing(Course::getName));
        } else {
            courses.sort(Comparator.comparing(Course::getName).reversed());
        }
        return courses;
    }

    public Course createCourse(Course course, Long institutionId) {
        Optional<Institution> institutionOptional = institutionRepository.findById(institutionId);
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            if (institution.getCourses().stream().anyMatch(c -> c.getName().equals(course.getName()))) {
                throw new DuplicateResourceException("Course already exists for this institution");
            }
            course.setInstitution(institution);
            return courseRepository.save(course);
        } else {
            throw new EntityNotFoundException("Institution not found");
        }
    }

    public Course updateCourse(Long id, Course course) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course existingCourse = courseOptional.get();
            existingCourse.setName(course.getName());

            Optional<Institution> institutionOptional = institutionRepository.findById(course.getInstitution().getId());
            if (institutionOptional.isPresent()) {
                Institution institution = institutionOptional.get();
                if (institution.getCourses().stream().anyMatch(c -> c.getName().equals(course.getName()) && !c.getId().equals(course.getId()))) {
                    throw new DuplicateResourceException("Course already exists for this institution");
                }
                existingCourse.setInstitution(institution);
            } else {
                throw new EntityNotFoundException("Institution not found");
            }

            return courseRepository.save(existingCourse);
        } else {
            throw new EntityNotFoundException("Course not found");
        }
    }

    public void deleteCourse(int id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            if (!course.getStudents().isEmpty()) {
                throw new InvalidRequestException("Cannot delete course assigned to student(s)");
            }
            courseRepository.delete(course);
        } else {
            throw new EntityNotFoundException("Course not found");
        }
    }
}

