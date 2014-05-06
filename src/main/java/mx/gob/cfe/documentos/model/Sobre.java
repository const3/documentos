/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author develop
 */
@Entity
@Table(name = "sobre")
public class Sobre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    private Integer diezmos;
    private Integer ofrendas;
    private Integer primicias;
    private Integer otro;
    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDiezmos() {
        return diezmos;
    }

    public void setDiezmos(Integer diezmos) {
        this.diezmos = diezmos;
    }

    public Integer getOfrendas() {
        return ofrendas;
    }

    public void setOfrendas(Integer ofrendas) {
        this.ofrendas = ofrendas;
    }

    public Integer getPrimicias() {
        return primicias;
    }

    public void setPrimicias(Integer primicias) {
        this.primicias = primicias;
    }

    public Integer getOtro() {
        return otro;
    }

    public void setOtro(Integer otro) {
        this.otro = otro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Sobre{" + "id=" + id + ", version=" + version + ", diezmos=" + diezmos + ", ofrendas=" + ofrendas
                + ", primicias=" + primicias + ", otro=" + otro + ", nombre=" + nombre + ", fecha=" + fecha + '}';
    }

}
