package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {
    @Autowired
    private PowerStatsRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HeroRepository mockRepository;
    @Test
    public void testControllerHeroA() throws Exception{
        PowerStats model = new PowerStats();
        model.setStrength(7);
        model.setAgility(6);
        model.setDexterity(5);
        model.setIntelligence(4);
        model.setCreated_at(new Date(2020,02,02));
        model.setUpdated_at(new Date(2020,02,04));
        PowerStats stats =repository.save(model);
        Hero hero = new Hero();
        hero.setPowerStats(stats);
        hero.setName("Volverine");
        hero.setRace(Race.HUMAN.name());
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,04));
        hero.setUpdated_at(new Date(2020,9,04));
        String json = StrJson.getJson("");
        System.out.println(json);
        mockMvc.perform(MockMvcRequestBuilders.post("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power_stats.strength", is(4)))
                .andExpect(jsonPath("$.power_stats.agility", is(4)))
                .andExpect(jsonPath("$.power_stats.dexterity", is(4)))
                .andExpect(jsonPath("$.power_stats.intelligence", is(4)))
                .andExpect(jsonPath("$.race", is(Race.HUMAN.name())))
                .andExpect(jsonPath("$.name", is("Volverine")));
        verify(mockRepository,times(0)).save(any(Hero.class));
    }

    @Test
    public void testControllerHeroB() throws Exception{
        PowerStats model = new PowerStats();
        model.setStrength(7);
        model.setAgility(6);
        model.setDexterity(5);
        model.setIntelligence(4);
        model.setCreated_at(new Date(2020,02,02));
        model.setUpdated_at(new Date(2020,02,04));
        PowerStats stats =repository.save(model);
        Hero hero = new Hero();
        hero.setName("Volverine");
        hero.setPowerStats(stats);
        hero.setRace(Race.HUMAN.name());
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,04));
        hero.setUpdated_at(new Date(2020,9,04));
        String json = StrJson.getJson(hero);
        mockMvc.perform(MockMvcRequestBuilders.post("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power_stats.strength", is(4)))
                .andExpect(jsonPath("$.power_stats.agility", is(4)))
                .andExpect(jsonPath("$.power_stats.dexterity", is(3)))
                .andExpect(jsonPath("$.power_stats.intelligence", is(4)))
                .andExpect(jsonPath("$.race", is(Race.HUMAN.name())))
                .andExpect(jsonPath("$.name", is("Volverine")));
    }

}
