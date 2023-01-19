package miniProject.kiosk.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderNumber extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private List<Integer> waitingNum;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Long ordernum;




}
