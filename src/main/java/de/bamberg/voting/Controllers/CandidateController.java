package de.bamberg.voting.Controllers;

import de.bamberg.voting.Model.Candidate;
import de.bamberg.voting.services.CandidateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/api/v1/candidates")
public class CandidateController {

    static final Logger LOGGER = LogManager.getLogger(CandidateController.class);

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/addCandidate")
    public ModelAndView addCandidate(@ModelAttribute Candidate candidate) {
        try {
            candidateService.addCandidate(candidate);
            LOGGER.info(candidate + " is added");
            Map<String, String> message = new HashMap<>();
            message.put("message", candidate.toString() + " is registered as candidate");
            return new ModelAndView("candidate", message);
        } catch (Exception ex) {
            return new ModelAndView("error");
        }
    }

    @GetMapping("/candidates")
    public List<Candidate> getCandidates(){
        return candidateService.getCandidates();
    }
}
