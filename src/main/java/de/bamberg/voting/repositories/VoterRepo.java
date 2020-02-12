package de.bamberg.voting.repositories;

import de.bamberg.voting.Model.Voter;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface VoterRepo extends DatastoreRepository<Voter,Long> {
    Optional<Voter> getVoterByEmail(String email);
    Optional<Voter> getVoterByToken(String token);
    List<Voter> findVoterByVoted(boolean status);
}
