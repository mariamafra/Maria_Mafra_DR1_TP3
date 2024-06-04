package com.example.tp3.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "material-didatico")
public class MaterialDidatico {
    @Id
    private String id;
    private String titulo;
}
