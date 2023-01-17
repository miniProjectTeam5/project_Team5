package miniProject.kiosk.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class MenuRequestDto {


    private String menuName;

    private Integer price;

    private String imageUrl;
}
