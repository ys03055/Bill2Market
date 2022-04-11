package com.example.demo.model.contract;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -80635313L;

    public static final QContract contract = new QContract("contract");

    public final NumberPath<Integer> chat_id = createNumber("chat_id", Integer.class);

    public final DatePath<java.sql.Date> contract_date = createDate("contract_date", java.sql.Date.class);

    public final NumberPath<Integer> contract_id = createNumber("contract_id", Integer.class);

    public final NumberPath<Integer> contract_status = createNumber("contract_status", Integer.class);

    public final DatePath<java.sql.Date> end_date = createDate("end_date", java.sql.Date.class);

    public final DatePath<java.sql.Date> start_date = createDate("start_date", java.sql.Date.class);

    public QContract(String variable) {
        super(Contract.class, forVariable(variable));
    }

    public QContract(Path<? extends Contract> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContract(PathMetadata metadata) {
        super(Contract.class, metadata);
    }

}

