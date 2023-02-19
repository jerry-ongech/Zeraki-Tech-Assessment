import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllByInstitutionId(Long institutionId, String courseName, int page, int size);
    List<Student> findAllByCourseId(Long courseId);
    Student findById(Long id);
    Student save(Student student);
    void delete(Student student);
}

