package com.devco.testingreso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="roles")
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter @ToString @Data
@ApiModel(description = "Roles disponibles para la venta.")
public class Rol {

    @Id
    @Column(name="idrol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position = 1, dataType = "Long", value = "Identificador unico del rol.<br>", example = "100", required = true)
    private Long idRol;
    
    @Column(name="nombre", unique = true)
    @NotNull(message = "El nombre del rol no puede ser vacio.")
    @Size(min=5, max=50, message = "Cantidad de carateres del rol minima es de 6 y de maxima de 50.")
    @ApiModelProperty(position = 2, dataType = "String", value = "Nombre del rol. <br>", example = "Desarrollador", required = true)
    private String nombre;

    @Transient
    @ApiModelProperty(position = 3, dataType = "Boolean", value = "Campo calculado el cual indica si el rol esta registrado.", example = "true", required = false)
    private boolean existente;

}
