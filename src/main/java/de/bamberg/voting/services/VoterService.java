package de.bamberg.voting.services;

import de.bamberg.voting.Model.Voter;
import de.bamberg.voting.repositories.VoterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    private VoterRepo voterRepo;

    public void addVoter(Voter voter){
        voterRepo.save(voter);
    }

    public Iterable<Voter> getVoters(){
        return voterRepo.findAll();
    }

    public Optional<Voter> getVoterByEmail(String email){
        return voterRepo.getVoterByEmail(email);
    }

    public Optional<Voter> getVoterByToken(String token){
        return voterRepo.getVoterByToken(token);
    }

    public List<Voter> getVoterByVotedStatus(boolean status){
        return voterRepo.findVoterByVoted(status);
    }

    public int getTotalVoters(){
        return voterRepo.findVoterByVoted(true).size();
    }
}
