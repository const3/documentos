/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author develop
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean enabled = true;
    @Column(nullable = false, name = "account_expired")
    private Boolean accountExpired = false;
    @Column(nullable = false, name = "account_locked")
    private Boolean accountLocked = false;
    @Column(nullable = false, name = "credentials_expired")
    private Boolean credentialsExpired = false;
    @NotEmpty
    @Column(nullable = false)
    private String nombre;
    @NotEmpty
    @Column(nullable = false)
    private String apPaterno;
    @NotEmpty
    @Column(nullable = false)
    private String apMaterno;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = {
        @JoinColumn(name = "usuario_id")}, inverseJoinColumns
            = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    @Email
    @NotEmpty
    @Column(nullable = false, name = "correo")
    private String correo;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", version=" + version + ", username=" + username + ", password=" + password
                + ", enabled=" + enabled + ", accountExpired=" + accountExpired + ", accountLocked=" + accountLocked
                + ", credentialsExpired=" + credentialsExpired + ", nombre=" + nombre + ", apPaterno=" + apPaterno
                + ", apMaterno=" + apMaterno + ", roles=" + roles + ", correo=" + correo + '}';
    }

}
