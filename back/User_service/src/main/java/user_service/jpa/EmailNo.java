package user_service.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="EmailNos")
public class EmailNo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column()
    private String email;

    @Column()
    private String no;
}
