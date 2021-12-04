package tech.homebody.homebodyuser.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userId;

    @Column
    private int score;

    @Column
    private String part;

    @Builder
    public Ranking(Long idx, String userId, int score, String part) {
        this.idx = idx;
        this.userId = userId;
        this.score = score;
        this.part = part;
    }
}
