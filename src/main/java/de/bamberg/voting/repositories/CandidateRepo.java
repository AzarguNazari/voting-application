package de.bamberg.voting.repositories;

import de.bamberg.voting.Model.Candidate;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.List;

public interface CandidateRepo extends DatastoreRepository<Candidate, Long> {
    List<Candidate> findCandidateByFacultyName(String facultyName);
    List<Candidate> findCandidateByNominated(boolean status);
}
