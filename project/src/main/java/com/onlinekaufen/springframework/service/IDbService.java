package com.onlinekaufen.springframework.service;

import com.onlinekaufen.springframework.database.DbHandle;
import org.skife.jdbi.v2.DBI;

public interface IDbService {


    /**
     * returns the DAO class of class
     *
     * @param daoClass Class com.wpis.dao class
     * @return <T> generic response
     */

    // Currently not in use can be used in future
    //<T> T getDao(String userId, Class<T> daoClass);

    <T> T getDao(Class<T> daoClass);

    /**
     * Returns the DBI object for given client
     *
     * @param userId String userId
     * @return DBI
     */
    DBI getDB(String userId);


    /**
     * Returns handle to be used mostly in transactions
     *
     * @param startTransaction to start transaction
     * @return DBHandle
     */
    DbHandle getDbHandle(boolean startTransaction);
}
