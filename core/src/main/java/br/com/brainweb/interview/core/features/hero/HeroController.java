package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class HeroController {
    @Autowired
    private HeroRepository repository;
    @PostMapping("/hero")
    public @ResponseBody ResponseEntity<Hero> createHero(@RequestBody Hero hero){
        Hero h = repository.save(hero);
        return new ResponseEntity<>(h, HttpStatus.OK);
    }

    @GetMapping("/hero/{id}")
    public @ResponseBody ResponseEntity<Hero> findById(@PathVariable("id") UUID id){
        Hero hero = repository.findById(id).get();
        if(hero != null)
            return new ResponseEntity<>(hero, HttpStatus.OK);
        else
            return  new ResponseEntity<>(null, HttpStatus.NOT_EXTENDED);
    }

    @GetMapping("/hero/{name}")
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
        Boolean delete = false;
        if(repository.findById(hero.getId()) != null){
            repository.delete(hero);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
