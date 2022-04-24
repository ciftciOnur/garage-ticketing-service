package tr.com.vodafone.garageservice.interfaces.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDto {

    private String code;
    private String message;
    private List<String> errors;
}
