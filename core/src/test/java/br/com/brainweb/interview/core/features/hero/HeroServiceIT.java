package br.com.brainweb.interview.core.features.hero;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
public class HeroServiceIT {
    @Autowired
    private PowerStatsRepository repository;
    @Autowired
    HeroRepository heroRepository;

    @Test
    public void createPower() {
        PowerStats model = new PowerStats();
        model.setStrength(4);
        model.setAgility(4);
        model.setDexterity(3);
        model.setIntelligence(4);
        model.setCreated_at(LocalDate.now());
        model.setUpdated_at(LocalDate.now());
        repository.save(model);
    }
}
