package br.com.brainweb.interview.core.features.powerstats;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PowerStatsController {
    @Autowired
    private  PowerStatsRepository repository;
    @PostMapping("/stats")
    public @ResponseBody ResponseEntity<PowerStats> createHero(@RequestBody PowerStats stats){
        PowerStats s = repository.save(stats);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
