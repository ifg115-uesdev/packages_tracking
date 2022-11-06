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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "package", catalog = "packs_tracking_db", schema = "")
public class Package implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "package_id", nullable = false, length = 50)
    private String packageId;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Basic(optional = false)
    @Column(name = "sender_customer_name", nullable = false, length = 100)
    private String senderCustomerName;
    @Basic(optional = false)
    @Column(name = "recipient_customer_name", nullable = false, length = 100)
    private String recipientCustomerName;
    @Basic(optional = false)
    @Column(name = "package_weight", nullable = false)
    private double packageWeight;
    @Basic(optional = false)
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private float price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId", fetch = FetchType.LAZY)
    private List<TrackingHist> trackingHistList;
    @JoinColumn(name = "shipping_agency_id", referencedColumnName = "agency_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agency shippingAgencyId;
    @JoinColumn(name = "destination_agency_id", referencedColumnName = "agency_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agency destinationAgencyId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userId;    
}
