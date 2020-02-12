package de.bamberg.voting.Model;

import java.util.List;

public class ElectionResult {

    private int totalNumberOfVoters;
    private List<Candidate> candidates;

    public ElectionResult(){}

    public int getTotalNumberOfVoters() {
        return totalNumberOfVoters;
    }

    public void setTotalNumberOfVoters(int totalNumberOfVoters) {
        this.totalNumberOfVoters = totalNumberOfVoters;
    }

    public List<Candidate> getCandidates() {
        candidates.sort((c1, c2) -> c2.getVoteCount() - c1.getVoteCount());
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return "ElectionResult{" +
                "totalNumberOfVoters=" + totalNumberOfVoters +
                ", candidates=" + candidates +
                '}';
    }
}
