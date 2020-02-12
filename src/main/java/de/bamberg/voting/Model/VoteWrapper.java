package de.bamberg.voting.Model;

import java.util.List;

public class VoteWrapper {
    private String token;
    private List<Candidate> candidateList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }
}
