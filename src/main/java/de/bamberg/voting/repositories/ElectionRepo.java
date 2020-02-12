package de.bamberg.voting.repositories;

import de.bamberg.voting.Model.Election;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface ElectionRepo extends DatastoreRepository<Election,Long> {

}
