import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class InstitutionService {
    private final Map<String, Institution> institutions = new HashMap<>();

    public void addInstitution(InstitutionDto institution) throws InstitutionAlreadyExistsException {
        if (institutions.containsKey(institution.getName())) {
            throw new InstitutionAlreadyExistsException("Institution with name " + institution.getName() + " already exists");
        }
        institutions.put(institution.getName(), new Institution(institution.getName()));
    }

    public List<Institution> getAllInstitutions() {
        return new ArrayList<>(institutions.values());
    }

    public List<Institution> searchInstitutions(String query) {
        List<Institution> results = new ArrayList<>();
        for (Institution institution : institutions.values()) {
            if (institution.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(institution);
            }
        }
        return results;
    }

    public List<Institution> sortInstitutionsByName(boolean ascending) {
        List<Institution> sortedInstitutions = new ArrayList<>(institutions.values());
        sortedInstitutions.sort((i1, i2) -> ascending ? i1.getName().compareTo(i2.getName()) : i2.getName().compareTo(i1.getName()));
        return sortedInstitutions;
    }

    public void deleteInstitution(String institutionName) throws InstitutionAssignedException, InstitutionNotFoundException {
        Institution institution = institutions.get(institutionName);
        if (institution == null) {
            throw new InstitutionNotFoundException("Institution with name " + institutionName + " not found");
        }
        if (institution.getCourses().size() > 0) {
            throw new InstitutionAssignedException("Institution with name " + institutionName + " has assigned courses");
        }
        institutions.remove(institutionName);
    }

    public void updateInstitution(String institutionName, InstitutionDto newInstitution) throws InstitutionAlreadyExistsException, InstitutionNotFoundException {
        Institution institution = institutions.get(institutionName);
        if (institution == null) {
            throw new InstitutionNotFoundException("Institution with name " + institutionName + " not found");
        }
        if (!institutionName.equals(newInstitution.getName()) && institutions.containsKey(newInstitution.getName())) {
            throw new InstitutionAlreadyExistsException("Institution with name " + newInstitution.getName() + " already exists");
        }
        institution.setName(newInstitution.getName());
    }
}
