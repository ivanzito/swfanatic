package br.com.sw.fanatic.swfanatic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Planets {

    private int count;

    private String next;

    private String previous;

    @JsonProperty("results")
    private List<Planet> Planet;

    public void setNext(String next) {
        if(next != null) {
            String[] split = next.split("\\?");
            this.next = next.replace(split[0],"http://localhost:8080/Planet/" + split[1].split("=")[1]).split("\\?")[0];
        }
    }

    public void setPrevious(String previous) {
        if(previous != null) {
            String[] split = previous.split("\\?");
            this.previous = previous.replace(split[0],"http://localhost:8080/Planet/" + split[1].split("=")[1]).split("\\?")[0];
        }
    }
}
