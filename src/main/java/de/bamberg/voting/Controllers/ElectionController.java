package de.bamberg.voting.Controllers;

import de.bamberg.voting.MailSender;
import de.bamberg.voting.Model.*;
import de.bamberg.voting.services.CandidateService;
import de.bamberg.voting.services.ElectionService;
import de.bamberg.voting.services.VoterService;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
public class ElectionController {

    static final Logger LOGGER = LogManager.getLogger(ElectionController.class);

    @Autowired
    private ElectionService electionService;

    @Autowired
    private VoterService voterService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private MailSender mailSender;

    @PostMapping("/createElection")
    public ModelAndView electionSubmit(@ModelAttribute Election election, BindingResult result) {
        try {

            String emails = new String(election.getParticipants().getBytes());
            election.setStudentEmails(emails);
            Election addedElection = electionService.addElection(election);

            List<String> studentEmails = Arrays.asList(emails.split("\n+"));

            studentEmails.forEach(studentEmail -> {

                Voter voter = new Voter(studentEmail, addedElection.getId());
                LOGGER.info("student with token " + voter.getToken() + " is added");

                // check if the email is valid or not before saving and sending email
                if(EmailValidator.getInstance().isValid(studentEmail)){
                    mailSender.sendMailTo(voter);
                    voterService.addVoter(voter);
                }
            });

            Map<String, String> message = new HashMap<>();
            message.put("message", "Election is created and will start at " + election.getStartDate().toString());
            return new ModelAndView("home", message);
        } catch (Exception ex) {
            return new ModelAndView("error");
        }
    }

    @PostMapping("/election-vote")
    public ModelAndView voting(@ModelAttribute(name = "voteSender") VoteSender voteSender){
        String[] data = voteSender.getVoteString().split(" +");
        Optional<Voter> voterByToken = voterService.getVoterByToken(data[1]);
        if(voterByToken.isPresent()){
            Long electionID = Long.valueOf(voterByToken.get().getToken().substring(30));
            Optional<Election> electionById = electionService.getElectionById(electionID);
            if(electionById.isPresent()){
                System.out.println("election exist");
                Optional<Candidate> candidateById = candidateService.getCandidateById(Long.valueOf(data[0]));
                if(candidateById.isPresent()){
                    System.out.println("Candidate exists");
                    candidateById.get().setVoteCount(candidateById.get().getVoteCount() + 1);
                    candidateService.addCandidate(candidateById.get());
                    voterByToken.get().setVoted(true);
                    voterService.addVoter(voterByToken.get());


                    Map<String, String> message = new HashMap<>();
                    message.put("message", "successful voted");
                    return new ModelAndView("vote", message);
                }
                else{
                    System.out.println("candidate id is wrong");
                    return new ModelAndView("error");
                }
            }
            else{
                System.out.println("No election exist");
                return new ModelAndView("error");
            }
        }
        else{
            System.out.println("Invalid token");
            return new ModelAndView("error");
        }
    }

    private void vote(String token) {

        String onlyTokenPart = token.substring(0, 30);
        Long electionID = Long.valueOf(token.substring(30));

        Optional<Election> election = electionService.getElectionById(electionID);

        if (election.isPresent()) {
            boolean voteIsStart = election.get().getStartDate().isBefore(LocalDate.now());
            if (voteIsStart) {
                Optional<Voter> voter = voterService.getVoterByToken(token);
                if (voter.isPresent()) {
                    if (voter.get().isVoted()) {
                        System.out.println("Voter can't vote because already voted ");
                    } else {
                        System.out.println("Voter can vote");
                        voter.get().setVoted(true);
                        voterService.addVoter(voter.get());
                    }
                } else {
                    System.out.println("Voter doesn't exist");
                }
            } else {
                System.out.println("Vote is not started yet");
            }
        }
        else{
            System.out.println("Not is not started yet");
        }
    }

    @GetMapping("/election-result")
    public ElectionResult getElectionResult() {
        ElectionResult result = new ElectionResult();
        int totalVoters = voterService.getTotalVoters();
        result.setTotalNumberOfVoters(totalVoters);
        List<Candidate> candidates = candidateService.getCandidates();
        candidates.forEach(candidate -> candidate.setPercentage(totalVoters == 0? 0 : ((candidate.getVoteCount() * 100.0) / totalVoters)));
        result.setCandidates(candidateService.getCandidates());
        return result;
    }
}
