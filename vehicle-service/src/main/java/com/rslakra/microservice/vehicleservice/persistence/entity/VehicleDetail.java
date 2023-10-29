//package com.rslakra.microservice.vehicleservice.persistence.entity;
//
//import com.devamatre.framework.core.ToString;
//import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
///**
// * The <code>VehicleDetail</code> stores the vehicle extra details information.
// *
// * @author Rohtash Lakra
// * @created 7/25/23 8:50 PM
// */
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "vehicle_details")
//public class VehicleDetail extends AbstractEntity<Long> {
//
//    //    @Id
////    @Column(name = "vehicle_id")
//////    private UUID vehicleId;
////    private Long vehicleId;
////
////    @OneToOne
////    @MapsId
////    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
//    private Vehicle vehicle;
//
//    @Column(name = "vin")
//    private String vin;
//
//    @Column(name = "type")
//    private String type;
//
//    @Column(name = "color")
//    private String color;
//
//    @Column(name = "wear")
//    private String wear;
//
//    @Column(name = "manufacturer")
//    private String manufacturer;
//
//    @Column(name = "purchasedOn")
//    private Date purchasedOn;
//
//    /**
//     * Returns the string representation of this object.
//     *
//     * @return
//     */
//    @Override
//    public String toString() {
//        return ToString.of(Vehicle.class)
//            .add("id", getId())
////            .add("vehicleId", getVehicle().getId())
//            .add("vin", getVin())
//            .add(super.toString())
//            .toString();
//    }
//
//}
