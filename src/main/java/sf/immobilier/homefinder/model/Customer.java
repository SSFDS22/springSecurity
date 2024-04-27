package sf.immobilier.homefinder.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * GenerationType.AUTO , generator = "native".
     * @GenericGenerator(name="native",strategy="native")
     *
     */
    private Long id;
    private String email;
    private String pwd;
    private String role;
}
