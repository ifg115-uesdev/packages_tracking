/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ues.edu.sv.packages_tracking.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tracking_hist", catalog = "packs_tracking_db", schema = "")
public class TrackingHist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hist_id", nullable = false)
    private Integer histId;
    @Basic(optional = false)
    @Column(name = "modify_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Column(name = "observation", length = 500)
    private String observation;
    @JoinColumn(name = "agency_id", referencedColumnName = "agency_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agency agencyId;
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Package packageId;
    @JoinColumn(name = "state_package_id", referencedColumnName = "state_package_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StatePackage statePackageId;
    
}
