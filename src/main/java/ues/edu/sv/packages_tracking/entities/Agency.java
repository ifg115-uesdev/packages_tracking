/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ues.edu.sv.packages_tracking.entities;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "agency", catalog = "packs_tracking_db", schema = "")
public class Agency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "agency_id", nullable = false)
    private Integer agencyId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "address", length = 200)
    private String address;
    @Basic(optional = false)
    @Column(name = "state", nullable = false)
    private boolean state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingAgencyId", fetch = FetchType.LAZY)
    private List<Package> packageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationAgencyId", fetch = FetchType.LAZY)
    private List<Package> packageList1;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department departmentId;
    @JoinColumn(name = "agency_type_id", referencedColumnName = "agency_type_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AgencyType agencyTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agencyId", fetch = FetchType.LAZY)
    private List<Users> userList;
    
}
