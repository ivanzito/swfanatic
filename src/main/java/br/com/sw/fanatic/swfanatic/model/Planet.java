package br.com.sw.fanatic.swfanatic.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String climate;

    @Column
    private String terrain;

    @Transient
    private List<String> films;

    @JsonIgnore
    @Column(name = "films")
    private int totalFilms;

    @JsonGetter
    public int getFilms() {
        return films == null ? 0 : films.size();
    }
}
