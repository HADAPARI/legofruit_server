package mg.legofruit.server.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.legofruit.server.entity.Users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO {
    private Long id;
    private Users userid;
    private Integer category;
    private String name;
    private Double prix;
    private Double quantity;
    private LocalDate daterecolte;
    private String image;
    private LocalDate datepublication ;

   //public ProduitDTO() {

    //}
}
