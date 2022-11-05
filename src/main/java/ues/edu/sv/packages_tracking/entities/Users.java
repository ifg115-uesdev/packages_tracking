/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ues.edu.sv.packages_tracking.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", catalog = "packs_tracking_db", schema = "")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "lastname", length = 100)
    private String lastname;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 25)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "gender", nullable = false)
    private Character gender;
    @Basic(optional = false)
    @Column(name = "date_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBirth;
    @JoinTable(name = "rol_user", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "rol_id", referencedColumnName = "rol_id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Rol> roles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Package> packageList;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department departmentId;
    @JoinColumn(name = "agency_id", referencedColumnName = "agency_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agency agencyId;
}
