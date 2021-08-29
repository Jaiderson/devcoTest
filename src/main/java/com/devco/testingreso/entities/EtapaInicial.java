package com.devco.testingreso.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="etapa_inicial")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Data
public class EtapaInicial {

    @Id
    @Column(name="idetapa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position=1, dataType="Long", value="Identificador unico de la etapa 1.<br>", example="100", required=true)
    private Long idEtapa;

    @OneToOne
    @JoinColumn(name = "idcandidato")
    @NotNull(message = "Candidato no puede ser vacio.")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ApiModelProperty(position = 2)
    private Candidato candidato;

    @Column(name="fecha_postulacion")
    @Temporal(TemporalType.DATE)
    @NotNull(message="Fecha de postulacion no puede ser vacia.")
    @ApiModelProperty(position = 3, dataType = "Date", value = "Fecha postulacion del candidato. (YYYY-MM-DD)<br>", example = "2021-08-28")
    private Date fecPostulacion;

    @Column(name="aspiracion_salarial")
    @Positive(message = "Aspiracion salarial debe ser mayor a cero.")
    @NotNull(message = "Aspiracion salarial no puede ser vacia.")
    @ApiModelProperty(position = 4, dataType = "Doble", value = "Valor de la aspiracion salarial.<br>", example = "3800000.00")
    private Double aspiracionSalarial;

    @Column(name="calificacion_teorica")
    @Min(value = 0, message = "Calificacion teorica debe ser mayor a cero y menor a uno. (Representa un %)")
    @Max(value = 1, message = "Calificacion teorica debe ser mayor a cero y menor a uno. (Representa un %)")
    @NotNull(message = "Calificacion examen teorico no puede ser vacio.")
    @ApiModelProperty(position = 5, dataType = "Float", example = "0.75", value = "Valor del resultado de la prueba teorica. (Representa un %)<br>")
    private Float calificacionTeoria;

    @Column(name="calificacion_tecnica")
    @Min(value = 0, message = "Calificacion tecnica debe ser mayor a cero y menor a uno. (Representa un %)")
    @Max(value = 1, message = "Calificacion tecnica debe ser mayor a cero y menor a uno. (Representa un %)")
    @NotNull(message = "Calificacion examen tecnico no puede ser vacio.")
    @ApiModelProperty(position = 6, dataType = "Float", example = "0.85", value = "Valor del resultado de la prueba tecnica. (Representa un %)<br>")
    private Float calificacionTecnica;

    @Column(name="nombre_evaluador")
    @Size(min = 6, max = 100, message = "Nombre evaluador debe tener entre 6 y 100 caracteres")
    @NotNull(message = "Aspiracion salarial no puede ser vacia.")
    @ApiModelProperty(position = 7, dataType = "String", example = "ALVARO MENDEZ", value = "Nombre del evaluador de la etapa 1.<br>")
    private String nombreEvaluador;

    @Column(name="comentarios")
    @ApiModelProperty(position = 8, dataType = "String", example = "Muy buena expariencia en Spring Boot Java 8.", value = "Comentarios realizador por el evaluador de la etapa 1.")
    private String comentarios;

    public static boolean isOkCalificacion(Float calificacion) {
    	return null != calificacion && calificacion.floatValue() > 0 && calificacion.floatValue() <= 1;
    }
}
