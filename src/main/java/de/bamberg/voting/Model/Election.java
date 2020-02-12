package de.bamberg.voting.Model;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity(name = "election")
public class Election {

    @Id
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String studentEmails;
    @Transient
    private MultipartFile participants;


    public Election() {
    }

    public Election(LocalDate startDate, LocalDate endDate, MultipartFile participants) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public MultipartFile getParticipants() {
        return participants;
    }

    public void setParticipants(MultipartFile participants) {
        this.participants = participants;
    }

    public String getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(String studentEmails) {
        this.studentEmails = studentEmails;
    }

    @Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participants='" + participants + '\'' +
                '}';
    }
}