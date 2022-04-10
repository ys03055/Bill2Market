package com.example.demo.model.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat_Message is a Querydsl query type for Chat_Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat_Message extends EntityPathBase<Chat_Message> {

    private static final long serialVersionUID = -1764801353L;

    public static final QChat_Message chat_Message = new QChat_Message("chat_Message");

    public final StringPath chat_content = createString("chat_content");

    public final NumberPath<Integer> chat_id = createNumber("chat_id", Integer.class);

    public final DatePath<java.sql.Date> create_date = createDate("create_date", java.sql.Date.class);

    public final NumberPath<Integer> from_index = createNumber("from_index", Integer.class);

    public final NumberPath<Integer> message_id = createNumber("message_id", Integer.class);

    public final BooleanPath read_status = createBoolean("read_status");

    public final NumberPath<Integer> to_index = createNumber("to_index", Integer.class);

    public final DatePath<java.sql.Date> update_date = createDate("update_date", java.sql.Date.class);

    public QChat_Message(String variable) {
        super(Chat_Message.class, forVariable(variable));
    }

    public QChat_Message(Path<? extends Chat_Message> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChat_Message(PathMetadata metadata) {
        super(Chat_Message.class, metadata);
    }

}

