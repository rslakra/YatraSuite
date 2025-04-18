//package com.rslakra.microservice.rideservice.hibernate;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.devamatre.framework.core.BeanUtils;
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.usertype.UserType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.io.StringWriter;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//
///**
// * @author Rohtash Lakra
// * @created 7/31/23 8:28 PM
// */
//public final class JsonUserType implements UserType {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUserType.class);
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    /**
//     * @return
//     */
//    @Override
//    public int[] sqlTypes() {
//        return new int[]{Types.JAVA_OBJECT};
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public Class returnedClass() {
//        return JsonUserType.class;
//    }
//
//    /**
//     * @param object
//     * @param other
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public boolean equals(Object object, Object other) throws HibernateException {
//        if (BeanUtils.isNull(object)) {
//            return BeanUtils.isNull(object);
//        }
//
//        return object.equals(object);
//    }
//
//    /**
//     * @param object
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public int hashCode(Object object) throws HibernateException {
//        return object.hashCode();
//    }
//
//    /**
//     * @param resultSet
//     * @param strings
//     * @param implementor
//     * @param object
//     * @return
//     * @throws HibernateException
//     * @throws SQLException
//     */
//    @Override
//    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor implementor,
//                              Object object)
//        throws HibernateException, SQLException {
//        LOGGER.debug("+nullSafeGet({}, {}, {}, {})", resultSet, strings, implementor, object);
//        Object result = null;
//        final String cellContent = resultSet.getString(strings[0]);
//        if (BeanUtils.isNotNull(cellContent)) {
//            try {
//                result = OBJECT_MAPPER.readValue(cellContent.getBytes("UTF-8"), returnedClass());
//            } catch (final Exception ex) {
//                LOGGER.error(ex.getMessage(), ex);
//                throw new RuntimeException("Error while converting object!, message: " + ex.getMessage(), ex);
//            }
//        }
//
//        LOGGER.debug("-nullSafeGet(), result:{}", result);
//        return result;
//    }
//
//    /**
//     * @param preparedStatement
//     * @param value
//     * @param index
//     * @param implementor
//     * @throws HibernateException
//     * @throws SQLException
//     */
//    @Override
//    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
//                            SharedSessionContractImplementor implementor)
//        throws HibernateException, SQLException {
//        LOGGER.debug("+nullSafeGet({}, {}, {}, {})", preparedStatement, value, index, implementor);
//        if (BeanUtils.isNull(value)) {
//            preparedStatement.setNull(index, Types.OTHER);
//            return;
//        }
//
//        try {
//            final StringWriter stringWriter = new StringWriter();
//            OBJECT_MAPPER.writeValue(stringWriter, value);
//            stringWriter.flush();
//            preparedStatement.setObject(index, stringWriter.toString(), Types.OTHER);
//        } catch (final Exception ex) {
//            LOGGER.error(ex.getMessage(), ex);
//            throw new RuntimeException("Error while converting object!, message: " + ex.getMessage(), ex);
//        }
//
//        LOGGER.debug("-nullSafeGet()");
//    }
//
//    /**
//     * @param object
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Object deepCopy(Object object) throws HibernateException {
//        LOGGER.debug("+deepCopy({})", object);
//        Object result = null;
//        try {
//            // use serialization to create a deep copy
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject(object);
//            objectOutputStream.flush();
//            objectOutputStream.close();
//            outputStream.close();
//            result = new ObjectInputStream(new ByteArrayInputStream(outputStream.toByteArray())).readObject();
//        } catch (ClassNotFoundException | IOException ex) {
//            throw new HibernateException(ex);
//        }
//
//        LOGGER.debug("-deepCopy(), result:{}", result);
//        return result;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public boolean isMutable() {
//        return true;
//    }
//
//    /**
//     * @param object
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Serializable disassemble(Object object) throws HibernateException {
//        return (Serializable) this.deepCopy(object);
//    }
//
//    /**
//     * @param cached
//     * @param owner
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Object assemble(Serializable cached, Object owner) throws HibernateException {
//        return this.deepCopy(cached);
//    }
//
//    /**
//     * @param original
//     * @param target
//     * @param owner
//     * @return
//     * @throws HibernateException
//     */
//    @Override
//    public Object replace(Object original, Object target, Object owner) throws HibernateException {
//        return this.deepCopy(original);
//    }
//}
