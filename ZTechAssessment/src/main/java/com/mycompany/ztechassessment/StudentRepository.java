import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAllByInstitutionIdAndCourseName(Long institutionId, String courseName, int page, int size);
    List<Student> findAllByCourseId(Long courseId);
    Optional<Student> findById(Long id);
    Student save(Student student);
    void delete(Student student);
}

