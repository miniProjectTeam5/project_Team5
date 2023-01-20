package miniProject.kiosk.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class StackPointResponseDto {
    private Integer points;

    public StackPointResponseDto(Integer points) {
        this.points = points;
    }
}
