package mg.legofruit.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.legofruit.server.entity.Users;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchatDTO {
    private Long id;
    private Users userid;
    @NotNull
    private Integer category;
    @NotBlank
    private String name;
    @NotNull
    private Double prix;
    @NotBlank
    private Double quantity;
    @NotNull
    private String image;
}
