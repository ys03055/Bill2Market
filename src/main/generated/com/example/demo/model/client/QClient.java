package com.example.demo.model.client;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClient is a Querydsl query type for Client
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClient extends EntityPathBase<Client> {

    private static final long serialVersionUID = 598802351L;

    public static final QClient client = new QClient("client");

    public final DatePath<java.sql.Date> birthdate = createDate("birthdate", java.sql.Date.class);

    public final StringPath clientId = createString("clientId");

    public final NumberPath<Integer> clientIndex = createNumber("clientIndex", Integer.class);

    public final StringPath clientName = createString("clientName");

    public final StringPath email = createString("email");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final NumberPath<Integer> snsType = createNumber("snsType", Integer.class);

    public final NumberPath<Integer> subscribe = createNumber("subscribe", Integer.class);

    public QClient(String variable) {
        super(Client.class, forVariable(variable));
    }

    public QClient(Path<? extends Client> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClient(PathMetadata metadata) {
        super(Client.class, metadata);
    }

}

