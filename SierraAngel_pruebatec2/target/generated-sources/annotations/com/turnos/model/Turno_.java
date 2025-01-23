package com.turnos.model;

import com.turnos.model.Ciudadano;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-01-23T04:24:07")
@StaticMetamodel(Turno.class)
public class Turno_ { 

    public static volatile SingularAttribute<Turno, String> descripcion;
    public static volatile SingularAttribute<Turno, LocalDate> fecha;
    public static volatile SingularAttribute<Turno, String> tema;
    public static volatile SingularAttribute<Turno, Long> id;
    public static volatile SingularAttribute<Turno, String> status;
    public static volatile SingularAttribute<Turno, Boolean> activo;
    public static volatile SingularAttribute<Turno, Ciudadano> ciudadano;

}