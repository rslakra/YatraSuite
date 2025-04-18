//package com.rslakra.microservice.rideservice.hibernate;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.dialect.H2Dialect;
//
//import java.sql.Types;
//
///**
// * register the type by extending the Database dialect and using that extended dialect as the dialect instead of the
// * original dialect:
// *
// * @author Rohtash Lakra
// * @created 7/31/23 2:07 PM
// */
//@Getter
//@Setter
//public class JsonH2Dialect extends H2Dialect {
//
//    public JsonH2Dialect() {
//        super();
//        registerColumnType(Types.JAVA_OBJECT, "text");
//        registerColumnType(Types.JAVA_OBJECT, "json");
//        registerColumnType(Types.JAVA_OBJECT, "jsonb");
//    }
//
//}
