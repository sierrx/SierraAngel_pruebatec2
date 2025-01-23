package com.turnos.model;

import com.turnos.model.Turno;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-01-23T15:15:43")
@StaticMetamodel(Ciudadano.class)
public class Ciudadano_ { 

    public static volatile SingularAttribute<Ciudadano, String> apellido;
    public static volatile SingularAttribute<Ciudadano, Long> id;
    public static volatile SingularAttribute<Ciudadano, String> telefono;
    public static volatile ListAttribute<Ciudadano, Turno> listaTurno;
    public static volatile SingularAttribute<Ciudadano, String> nombre;

}