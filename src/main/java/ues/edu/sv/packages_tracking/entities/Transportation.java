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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transportation", catalog = "packs_tracking_db", schema = "")
public class Transportation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transportation_id", nullable = false)
    private Integer transportationId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "state")
    private boolean state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transportationId", fetch = FetchType.LAZY)
    private List<TrackingHist> trackingHistList;

    public Transportation(Integer transportationId) {
        this.transportationId = transportationId;
    }
}
