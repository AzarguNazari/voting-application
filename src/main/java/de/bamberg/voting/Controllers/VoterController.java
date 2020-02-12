package de.bamberg.voting.Controllers;

import de.bamberg.voting.repositories.VoterRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoterController {

    static final Logger LOGGER = LogManager.getLogger(VoterController.class);


    final VoterRepo voterRepo;

    public VoterController(VoterRepo voterRepo) {
        this.voterRepo = voterRepo;
    }

    @GetMapping("/valid")
    public String validVoter() {
        return "valid voters " + this.voterRepo.findVoterByVoted(false).size();
    }

    @GetMapping("/vc")
    public String clear() {
        this.voterRepo.deleteAll();
        return "voters clear";
    }

}
