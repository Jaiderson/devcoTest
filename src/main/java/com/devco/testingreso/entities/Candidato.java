package com.devco.testingreso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="candidatos")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Data
public class Candidato {

    @Id
    @Column(name="idcandidato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position=1, dataType="Long", value="Identificador unico del candidato.", example="10", required=true)
    private long idCandidato;

    @Column(name="dni", nullable = false, unique = true)
    @NotNull(message = "DNI del candidato no puede ser vacio.")
    @Size(min=6, max=20, message="Cantidad de carateres del DNI minimo es de 6 y de maxima de 20")
    @ApiModelProperty(position=2, dataType="String", value="Numero de documento del candidato.", example="C5420460", required=true)
    private String dni;

    @Column(name="nombre", nullable = false)
    @NotNull(message = "Nombre del candidato no puede ser vacio.")
    @Size(min=6, max=100, message = "Cantidad de carateres del nombre minimo es de 6 y de maximo de 100")
    @ApiModelProperty(position=3, dataType="String", value="Nombre del candidato.", example="JOSE LUIS VILLA MERA", required=true)
    private String nombre;

    @Column(name="email", nullable = false)
    @NotNull(message = "Email del candidato no puede ser vacio.")
    @Size(min=6, max=100, message = "Cantidad de carateres del email minimo es de 6 y de maximo de 100")
    @ApiModelProperty(position=4, dataType="String", value="Nombre del candidato.", example="jose.mera@hotmail.com", required=true)
    @Email(message = "Formato de email no valido.")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol")
    private Rol rol;
}
