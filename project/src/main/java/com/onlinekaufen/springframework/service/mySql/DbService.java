package com.onlinekaufen.springframework.service.mySql;

import com.onlinekaufen.springframework.database.DbHandle;
import com.onlinekaufen.springframework.service.IDbService;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Prashant on 3/13/17.
 *
 * Implementation of DBService used to get the required DAO class for JDBI.
 */
@Service
public class DbService implements IDbService {

    private final DBI dbi;

    @Autowired
    public DbService(DBI dbi) {
        this.dbi = dbi;
    }

    @Override
    public <T> T getDao(Class<T> daoClass) {
        if(null != dbi){
            try {
                if (null != daoClass) {
                    return dbi.onDemand(daoClass);
                } else {
                    throw new InternalError("Dao can't be returned!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new InternalError("Dao can't be returned!");
    }

    @Override
    public DBI getDB(String userId) {
        if (null != dbi) {
            return dbi;
        }
        throw new InternalError("DBI can't be returned!");
    }

    @Override
    public DbHandle getDbHandle(boolean startTransaction) {
        if (null != dbi) {
            return new DbHandle(dbi.open(), startTransaction);
        }
        throw new InternalError("DbHandler can't be returned!");
    }
}
