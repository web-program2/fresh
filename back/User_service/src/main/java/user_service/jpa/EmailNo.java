package user_service.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="EmailNos")
public class EmailNo {
    @Id
    private String email;

    @Column()
    private String no;
}
