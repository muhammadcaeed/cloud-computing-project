package com.onlinekaufen.springframework.database.mappers;

import org.skife.jdbi.v2.BuiltInArgumentFactory;
import org.skife.jdbi.v2.ResultSetMapperFactory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class BeanMapperSnakeCaseFactory implements ResultSetMapperFactory {

    @Override
    public boolean accepts(Class type, StatementContext ctx) {
        if (BuiltInArgumentFactory.canAccept(type)) {
            return false;
        }
        return true;
    }

    @Override
    public ResultSetMapper mapperFor(Class type, StatementContext ctx) {
        return new BeanMapperSnakeCase(type);
    }
}