import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    // Find an institution by name
    Institution findByName(String name);

    // Find all institutions with names containing the given string
    List<Institution> findByNameContaining(String search);

    // Find all institutions sorted by name in ascending order
    List<Institution> findAllByOrderByNameAsc();

    // Find all institutions sorted by name in descending order
    List<Institution> findAllByOrderByNameDesc();
}

