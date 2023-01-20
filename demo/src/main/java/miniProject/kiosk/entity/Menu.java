package miniProject.kiosk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniProject.kiosk.dto.MenuRequestDto;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String imageUrl;

    public Menu(MenuRequestDto menuRequestDto){
        this.menuName = menuRequestDto.getMenuName();
        this.price = menuRequestDto.getPrice();
        this.imageUrl = menuRequestDto.getImageUrl();
    }
}
