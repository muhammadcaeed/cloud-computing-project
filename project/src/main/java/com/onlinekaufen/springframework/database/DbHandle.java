package com.onlinekaufen.springframework.database;

import org.skife.jdbi.v2.*;
import org.skife.jdbi.v2.exceptions.TransactionFailedException;
import org.skife.jdbi.v2.tweak.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class DbHandle implements Handle, AutoCloseable {
    private final Handle jdbiHandle;

    public DbHandle(Handle jdbiHandle, boolean startTransaction) {
        this.jdbiHandle = jdbiHandle;
        if(startTransaction){
            jdbiHandle.begin();
        }
    }

    @Override
    public Connection getConnection() {
        return jdbiHandle.getConnection();
    }

    @Override
    public void close() {
        if (jdbiHandle.isInTransaction()) {
            jdbiHandle.rollback();
        }
        jdbiHandle.close();
    }

    @Override
    public void define(String s, Object o) {
        jdbiHandle.define(s, o);
    }

    @Override
    public Handle begin() {
        return jdbiHandle.begin();
    }

    @Override
    public Handle commit() {
        return jdbiHandle.commit();
    }

    @Override
    public Handle rollback() {
        return jdbiHandle.rollback();
    }

    @Override
    public Handle rollback(String s) {
        return jdbiHandle.rollback(s);
    }

    @Override
    public boolean isInTransaction() {
        return jdbiHandle.isInTransaction();
    }

    @Override
    public Query<Map<String, Object>> createQuery(String s) {
        return jdbiHandle.createQuery(s);
    }

    @Override
    public Update createStatement(String s) {
        return jdbiHandle.createStatement(s);
    }

    @Override
    public Call createCall(String s) {
        return jdbiHandle.createCall(s);
    }

    @Override
    public int insert(String s, Object... objects) {
        return jdbiHandle.insert(s, objects);
    }

    @Override
    public int update(String s, Object... objects) {
        return jdbiHandle.update(s, objects);
    }

    @Override
    public PreparedBatch prepareBatch(String s) {
        return jdbiHandle.prepareBatch(s);
    }

    @Override
    public Batch createBatch() {
        return jdbiHandle.createBatch();
    }

    @Override
    public <ReturnType> ReturnType inTransaction(TransactionCallback<ReturnType> transactionCallback) throws TransactionFailedException {
        return jdbiHandle.inTransaction(transactionCallback);
    }

    @Override
    public void useTransaction(TransactionConsumer transactionConsumer) throws TransactionFailedException {
        jdbiHandle.useTransaction(transactionConsumer);
    }

    @Override
    public <ReturnType> ReturnType inTransaction(TransactionIsolationLevel transactionIsolationLevel, TransactionCallback<ReturnType> transactionCallback) throws TransactionFailedException {
        return jdbiHandle.inTransaction(transactionIsolationLevel, transactionCallback);
    }

    @Override
    public void useTransaction(TransactionIsolationLevel transactionIsolationLevel, TransactionConsumer transactionConsumer) throws TransactionFailedException {
    }

    @Override
    public List<Map<String, Object>> select(String s, Object... objects) {
        return jdbiHandle.select(s, objects);
    }

    @Override
    public void setStatementLocator(StatementLocator statementLocator) {
        jdbiHandle.setStatementLocator(statementLocator);
    }

    @Override
    public void setStatementRewriter(StatementRewriter statementRewriter) {
        jdbiHandle.setStatementRewriter(statementRewriter);
    }

    @Override
    public Script createScript(String s) {
        return jdbiHandle.createScript(s);
    }

    @Override
    public void execute(String s, Object... objects) {
        jdbiHandle.execute(s, objects);
    }

    @Override
    public Handle checkpoint(String s) {
        return jdbiHandle.checkpoint(s);
    }

    @Override
    public Handle release(String s) {
        return jdbiHandle.release(s);
    }

    @Override
    public void setStatementBuilder(StatementBuilder statementBuilder) {
        jdbiHandle.setStatementBuilder(statementBuilder);
    }

    @Override
    public void setSQLLog(SQLLog sqlLog) {
        jdbiHandle.setSQLLog(sqlLog);
    }

    @Override
    public void setTimingCollector(TimingCollector timingCollector) {
        jdbiHandle.setTimingCollector(timingCollector);
    }

    @Override
    public void registerMapper(ResultSetMapper resultSetMapper) {
        jdbiHandle.registerMapper(resultSetMapper);
    }

    @Override
    public void registerMapper(ResultSetMapperFactory resultSetMapperFactory) {
        jdbiHandle.registerMapper(resultSetMapperFactory);
    }

    @Override
    public void registerColumnMapper(ResultColumnMapper resultColumnMapper) {
        jdbiHandle.registerColumnMapper(resultColumnMapper);
    }

    @Override
    public void registerColumnMapper(ResultColumnMapperFactory resultColumnMapperFactory) {
        jdbiHandle.registerColumnMapper(resultColumnMapperFactory);
    }

    @Override
    public <SqlObjectType> SqlObjectType attach(Class<SqlObjectType> aClass) {
        return jdbiHandle.attach(aClass);
    }

    @Override
    public void setTransactionIsolation(TransactionIsolationLevel transactionIsolationLevel) {
        jdbiHandle.setTransactionIsolation(transactionIsolationLevel);
    }

    @Override
    public void setTransactionIsolation(int i) {
        jdbiHandle.setTransactionIsolation(i);
    }

    @Override
    public TransactionIsolationLevel getTransactionIsolationLevel() {
        return jdbiHandle.getTransactionIsolationLevel();
    }

    @Override
    public void registerArgumentFactory(ArgumentFactory argumentFactory) {
        jdbiHandle.registerArgumentFactory(argumentFactory);
    }

    @Override
    public void registerContainerFactory(ContainerFactory<?> containerFactory) {
        jdbiHandle.registerContainerFactory(containerFactory);
    }

    @Override
    public boolean isClosed() {
        return jdbiHandle.isClosed();
    }

    @Override
    public void setSqlObjectContext(SqlObjectContext sqlObjectContext) {
        jdbiHandle.setSqlObjectContext(sqlObjectContext);
    }

    @Override
    public SqlObjectContext getSqlObjectContext() {
        return jdbiHandle.getSqlObjectContext();
    }
}

