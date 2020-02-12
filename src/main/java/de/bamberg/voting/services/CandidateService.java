package de.bamberg.voting.services;

import com.google.common.collect.Lists;
import de.bamberg.voting.Model.Candidate;
import de.bamberg.voting.Model.Voter;
import de.bamberg.voting.repositories.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;

    public void addCandidate(Candidate candidate){
        candidateRepo.save(candidate);
    }

    public List<Candidate> getCandidateByFacultyName(String facultyName){
        return candidateRepo.findCandidateByFacultyName(facultyName);
    }

    public Optional<Candidate> getCandidateById(Long candidateID){
        return candidateRepo.findById(candidateID);
    }

    public List<Candidate> getCandidateByNominated(boolean status){
        return candidateRepo.findCandidateByNominated(status);
    }

    public List<Candidate> getCandidates(){
       return Lists.newArrayList(candidateRepo.findAll());
    }

}
