
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findByInstitutionId(Long institutionId);

    Optional<Course> findById(Long id);

    Optional<Course> findByNameAndInstitutionId(String name, Long institutionId);

    List<Course> findAll();

    void save(Course course);

    void delete(Course course);
}
