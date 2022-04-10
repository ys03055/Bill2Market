package com.example.demo.model.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = 2121172239L;

    public static final QChat chat = new QChat("chat");

    public final NumberPath<Integer> chat_id = createNumber("chat_id", Integer.class);

    public final DatePath<java.sql.Date> create_date = createDate("create_date", java.sql.Date.class);

    public final NumberPath<Integer> item_id = createNumber("item_id", Integer.class);

    public final NumberPath<Integer> lenter_index = createNumber("lenter_index", Integer.class);

    public final DatePath<java.sql.Date> update_date = createDate("update_date", java.sql.Date.class);

    public QChat(String variable) {
        super(Chat.class, forVariable(variable));
    }

    public QChat(Path<? extends Chat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChat(PathMetadata metadata) {
        super(Chat.class, metadata);
    }

}

