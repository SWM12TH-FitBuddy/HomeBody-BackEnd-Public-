package tech.homebody.homebodyuser.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class PoseInfo {
    @Id
    private String name;

    @Column
    private String part;

    @Column
    private String type;

    @Column
    private int averageTimeOfOneRep;

    @Builder
    public PoseInfo(String name, String part, String type, int averageTimeOfOneRep) {
        this.name = name;
        this.part = part;
        this.type = type;
        this.averageTimeOfOneRep = averageTimeOfOneRep;
    }
}
