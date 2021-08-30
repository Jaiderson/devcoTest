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

@Entity(name="etapa_final")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Data
public class EtapaFinal {

    @Id
    @Column(name="idetapaF")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position=1, dataType="Long", value="Identificador unico de la etapa 3.<br>", example="200", required=true)
	private Long idEtapaF;

    @OneToOne
    @JoinColumn(name = "idetapaM")
    @NotNull(message = "Etapa intermedia no puede ser vacia.")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ApiModelProperty(position = 2)
    private EtapaIntermedia etapaIntermedia;

    @Column(name="puntaje_promedio")
    @Min(value = 0, message = "Puntaje promedio debe ser mayor a cero y menor a uno. (Representa un %)")
    @Max(value = 1, message = "Puntaje promedio debe ser mayor a cero y menor a uno. (Representa un %)")
    @NotNull(message = "Puntaje promedio no puede ser vacio.")
    @ApiModelProperty(position = 3, dataType = "Float", example = "0.83", value = "Valor del resultado promedio de las etapas de seleccion. (Representa un %)<br>")
    private Float promedio;

    @Column(name="salario_acordado")
    @Min(value = 0, message = "Salario acordado debe ser mayor a cero.")
    @NotNull(message = "Salario acordado medica no puede ser vacio.")
    @ApiModelProperty(position = 4, dataType = "Float", example = "5400000", value = "Valor del salario acordado con el candidato.<br>")
    private Double salarioAcordado;

    @Column(name="comentarios")
    @ApiModelProperty(position = 5, dataType = "String", example = "Aprobado gran espectativa.", value = "Comentarios realizador por el evaluador de la etapa 3.")
    private String comentarios;

    @Column(name="estado_mail")
    @ApiModelProperty(position = 9, dataType = "String", example = "Cuando se actualiza este campo a NO se puede consumir el servicio de notificacion mail.", value = "Campo usado para notificar mail al candidato.")
    private String mailEnviado;

}
