/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.model;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.IndexColumn;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author develop
 */
@Entity
@Table(name = "roles")
@Cacheable
public class Rol implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotNull
    @Column(unique = true, nullable = false, length = 128)
    @IndexColumn(name = "rol_authority_idx")
    private String authority;
    private Integer prioridad;

    public Rol() {
    }

    public Rol(String authority) {
        this.authority = authority;
    }

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", version=" + version + ", authority=" + authority + ", prioridad=" + prioridad + '}';
    }

}
