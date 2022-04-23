package tr.com.vodafone.garageservice.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StatusDto {

    private String numberPlate;
    private String color;
    private List<Integer> slots;
}
