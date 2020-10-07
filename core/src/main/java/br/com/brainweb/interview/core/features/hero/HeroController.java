package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.core.util.HeroCompared;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class HeroController {
    @Autowired
    private HeroRepository repository;
    @GetMapping("/hero")
    public @ResponseBody ResponseEntity<List<Hero>> findAll(){
        List<Hero> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/hero")
    public @ResponseBody ResponseEntity<Hero> createHero(@Valid @RequestBody Hero hero){
        Hero h = repository.save(hero);
        return new ResponseEntity<>(h, HttpStatus.OK);
    }

    @GetMapping("/hero/{id}")
    public @ResponseBody ResponseEntity<Hero> findById(@PathVariable("id") UUID id){
        Optional<Hero> hero = repository.findById(id);
        if(hero.isPresent())
            return new ResponseEntity<>(hero.get(), HttpStatus.OK);
        else
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/hero/{heroId}/{hero1Id}")
    public @ResponseBody ResponseEntity<Hero> comparedHeroes(@PathVariable("heroId") UUID heroId,
                                                             @PathVariable("hero1Id") UUID hero1Id){
        Optional<Hero> hero = repository.findById(heroId);
        Optional<Hero>  hero1 = repository.findById(heroId);
        if(hero.isPresent() && hero1.isPresent())
            return new ResponseEntity<>(new HeroCompared().getHeroCompared(hero.get(), hero1.get()), HttpStatus.OK);
        else
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByName/{name}")
    public @ResponseBody ResponseEntity<List<Hero>> findByName(@PathVariable("name") String nome){
        List<Hero> list  =repository.findByName(nome);
        if(list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/hero")
    public @ResponseBody ResponseEntity<Hero> updateHero(@Valid @RequestBody Hero hero){
        Hero h = repository.save(hero);
        if(h != null)
            return new ResponseEntity<>(h, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/hero")
    public @ResponseBody ResponseEntity<Void> removeHero(@RequestBody Hero hero){
        Optional<Hero> h = repository.findById(hero.getId());
        if(h.isPresent()){
            repository.delete(h.get());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
