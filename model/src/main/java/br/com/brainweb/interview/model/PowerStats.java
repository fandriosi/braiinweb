package br.com.brainweb.interview.model;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

public class PowerStats {
    @Id
    private Long id;
    @NotNull
    private Integer strength;
    @NotNull
    private Integer agility;
    @NotNull
    private Integer dexterity;
    @NotNull
    private Integer intelligence;
    @NotNull
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate created_at;
    @NotNull
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }
}
