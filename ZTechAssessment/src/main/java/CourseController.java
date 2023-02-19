
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{institutionId}")
    public List<CourseDTO> getCourses(@PathVariable int institutionId) {
        return courseService.getCourses(institutionId);
    }

    @GetMapping("/{institutionId}/{search}")
    public List<CourseDTO> searchCourses(@PathVariable int institutionId, @PathVariable String search) {
        return courseService.searchCourses(institutionId, search);
    }

    @PostMapping("/{institutionId}")
    public void addCourse(@PathVariable int institutionId, @RequestBody CourseDTO courseDTO) {
        courseService.addCourse(institutionId, courseDTO);
    }

    @PutMapping("/{courseId}")
    public void updateCourse(@PathVariable int courseId, @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(courseId, courseDTO);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
    }
}

