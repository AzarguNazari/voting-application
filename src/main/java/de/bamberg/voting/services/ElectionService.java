package de.bamberg.voting.services;

import de.bamberg.voting.Model.Election;
import de.bamberg.voting.repositories.ElectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepo electionRepo;

    public Election addElection(Election election){
        return electionRepo.save(election);
    }

    public Optional<Election> getElectionById(Long electionID){
        return electionRepo.findById(electionID);
    }


}
