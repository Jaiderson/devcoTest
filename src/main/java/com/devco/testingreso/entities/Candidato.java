package com.devco.testingreso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="candidatos")
@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Data
public class Candidato {

	@Id
    @Column(name="idcandidato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position=1, dataType="Long", value="Identificador unico del candidato. <br>", example="10", required=true)
    private long idCandidato;

    @Column(name="dni", nullable = false, unique = true)
    @NotNull(message = "DNI del candidato no puede ser vacio.")
    @Size(min=6, max=20, message="Cantidad de carateres del DNI minimo es de 6 y de maxima de 20.")
    @ApiModelProperty(position=2, dataType="String", value="Numero de documento de identidad del candidato. <br>", example="C5420460", required=true)
    private String dni;

    @Column(name="nombre", nullable = false)
    @NotNull(message = "Nombre del candidato no puede ser vacio.")
    @Size(min=6, max=100, message = "Cantidad de carateres del nombre minimo es de 6 y de maximo de 100")
    @ApiModelProperty(position=3, dataType="String", value="Nombre del candidato. <br>", example="JOSE LUIS VILLA MERA", required=true)
    private String nombre;

    @Column(name="email", nullable = false)
    @NotNull(message = "Email del candidato no puede ser vacio.")
    @Size(min=6, max=100, message = "Cantidad de carateres del email minimo es de 6 y de maximo de 100")
    @ApiModelProperty(position=4, dataType="String", value="Correo electronico del candidato. <br>", example="jose.mera@hotmail.com", required=true)
    @Email(message = "Formato de email no valido.")
    private String email;

    @Transient
    @ApiModelProperty(position=5, dataType="Rol", value="Campo calculado el cual indica si el candidato esta registrado.<br>", required=false)
    private boolean existente;

    @OneToOne
    @JoinColumn(name = "idrol")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ApiModelProperty(position=6, dataType="Rol", required=true)
    private Rol rol;

    @PrePersist
    public void prePersist() {
    	this.nombre = this.nombre.toUpperCase();
    	this.email = this.email.toLowerCase();
    }

}
