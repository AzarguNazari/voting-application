package de.bamberg.voting.Model;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity(name = "voters")
public class Voter {

    @Id
    Long id;
    String email;
    String token;
    boolean voted;

    public Voter() {
    }

    public Voter(String email, Long electionID) {
        this.email = email;
        this.token = new StringBuilder(RandomStringUtils.randomAlphanumeric(30)).append(electionID).toString() ;
        voted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
