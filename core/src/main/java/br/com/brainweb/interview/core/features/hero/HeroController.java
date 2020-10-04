package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HeroController {
    @Autowired
    private HeroRepository repository;
    @PostMapping("/hero")
    public @ResponseBody ResponseEntity<Hero> createHero(@RequestBody Hero hero){
        repository.save(hero);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
