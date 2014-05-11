/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author develop
 */
public class LabelValueBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3629077799108569942L;
    private Long id;
    private String value;
    private String nombre;

    public LabelValueBean() {
    }

    public LabelValueBean(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public LabelValueBean(Long id, String value, String nombre) {
        this.id = id;
        this.value = value;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return value;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LabelValueBean other = (LabelValueBean) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public String toString() {
        return "LabelValueBean{" + "id=" + id + ", value=" + value + '}';
    }

}
