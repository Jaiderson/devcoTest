package com.devco.testingreso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="etapa_intermedia")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Data
public class EtapaIntermedia {

    @Id
    @Column(name="idetapaM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position=1, dataType="Long", value="Identificador unico de la etapa 2.<br>", example="200", required=true)
	private Long idEtapaM;

    @OneToOne
    @JoinColumn(name = "idetapa")
    @NotNull(message = "Etapa inicial no puede ser vacia.")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ApiModelProperty(position = 2)
    private EtapaInicial etapaInicial;

    @Column(name="calificacion_psicologica")
    @Min(value = 0, message = "Calificacion psicologica debe ser mayor a cero y menor a uno. (Representa un %)")
    @Max(value = 1, message = "Calificacion psicologica debe ser mayor a cero y menor a uno. (Representa un %)")
    @NotNull(message = "Calificacion examen psicologica no puede ser vacio.")
    @ApiModelProperty(position = 3, dataType = "Float", example = "0.91", value = "Valor del resultado de la prueba psicologica. (Representa un %)<br>")
    private Float calPsicologica;

    @Column(name="calificacion_medica")
    @Min(value = 0, message = "Calificacion medica debe ser mayor a cero y menor a uno. (Representa un %)")
    @Max(value = 1, message = "Calificacion medica debe ser mayor a cero y menor a uno. (Representa un %)")
    @NotNull(message = "Calificacion examen medica no puede ser vacio.")
    @ApiModelProperty(position = 4, dataType = "Float", example = "0.91", value = "Valor del resultado de la prueba medica. (Representa un %)<br>")
    private Float calMedica;

    @Column(name="comentarios")
    @ApiModelProperty(position = 5, dataType = "String", example = "Persona con hipertencion arterial.", value = "Comentarios realizador por el evaluador de la etapa 2.")
    private String comentarios;

}
