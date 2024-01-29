package java12.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Job {
    private Long id;
    private String position;
    private String profession;
    private String description;
    private int experience;

    public Job(String position, String profession, String description, int experience) {
        this.position = position;
        this.profession = profession;
        this.description = description;
        this.experience = experience;
    }
}

