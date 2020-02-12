package de.bamberg.voting.config;

import de.bamberg.voting.Controllers.ElectionController;
import de.bamberg.voting.Model.*;
import de.bamberg.voting.services.CandidateService;
import de.bamberg.voting.services.ElectionService;
import de.bamberg.voting.services.VoterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThymeleafPageConfig {

    static final Logger LOGGER = LogManager.getLogger(ThymeleafPageConfig.class);

    @Autowired
    private VoterService voterService;

    @Autowired
    private ElectionService electionService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ElectionController electionController;

    @RequestMapping(path = {"/", "/home"})
    public ModelAndView home() {
        LOGGER.info("Loading home page...");
        return new ModelAndView("home");
    }

    @RequestMapping("/register-candidate")
    public String createVote(Model model) {
        LOGGER.info("Loading create-candidate page...");
        model.addAttribute("candidate", new Candidate());
        return "candidate";
    }


    @RequestMapping("/voting/{token}")
    public String vote(@PathVariable("token") String token, Model model) {
        LOGGER.info("Loading vote page...");
        if(voterService.getVoterByToken(token).isPresent() && !voterService.getVoterByToken(token).get().isVoted()){
            VoteWrapper voteWrapper = new VoteWrapper();
            voteWrapper.setToken(token);
            voteWrapper.setCandidateList(candidateService.getCandidates());
            model.addAttribute("data", voteWrapper);
            model.addAttribute("voteSender", new VoteSender());
        }
        else{
            model.addAttribute("error", "error");
        }
        return "vote";
    }

    @RequestMapping("/create-election")
    public String createElection(Model model) {
        LOGGER.info("Loading createElection page...");
        model.addAttribute("election", new Election());
        return "election";
    }

    @RequestMapping("/greeting")
    public ModelAndView greeting() {
        LOGGER.info("Loading vote page...");
        return new ModelAndView("greeting");
    }

    @RequestMapping("/result")
    public String voteResult(Model model) {
        ElectionResult electionResult = electionController.getElectionResult();
        electionResult.getCandidates().stream().sorted((Candidate c1, Candidate c2) -> c1.getVoteCount() - c2.getVoteCount());
        model.addAttribute("electionResult", electionResult);
        LOGGER.info("Loading voting result page...");
        return "result";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        LOGGER.info("Loading login page...");
        return new ModelAndView("login");
    }
}
