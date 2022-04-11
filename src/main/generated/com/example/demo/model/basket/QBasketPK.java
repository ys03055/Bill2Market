package com.example.demo.model.basket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasketPK is a Querydsl query type for BasketPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBasketPK extends BeanPath<BasketPK> {

    private static final long serialVersionUID = -1813655158L;

    public static final QBasketPK basketPK = new QBasketPK("basketPK");

    public final NumberPath<Integer> clientIndex = createNumber("clientIndex", Integer.class);

    public final NumberPath<Integer> itemId = createNumber("itemId", Integer.class);

    public QBasketPK(String variable) {
        super(BasketPK.class, forVariable(variable));
    }

    public QBasketPK(Path<? extends BasketPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasketPK(PathMetadata metadata) {
        super(BasketPK.class, metadata);
    }

}

