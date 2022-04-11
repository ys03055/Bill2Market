package com.example.demo.model.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1639898641L;

    public static final QItem item = new QItem("item");

    public final StringPath categoryBig = createString("categoryBig");

    public final StringPath categoryMiddle = createString("categoryMiddle");

    public final StringPath categorySmall = createString("categorySmall");

    public final EnumPath<ContractStatus> contractStatus = createEnum("contractStatus", ContractStatus.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> deposit = createNumber("deposit", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath itemAddress = createString("itemAddress");

    public final StringPath itemContent = createString("itemContent");

    public final NumberPath<Integer> itemId = createNumber("itemId", Integer.class);

    public final NumberPath<Double> itemLatitude = createNumber("itemLatitude", Double.class);

    public final NumberPath<Double> itemLongitude = createNumber("itemLongitude", Double.class);

    public final StringPath itemQuality = createString("itemQuality");

    public final StringPath itemTitle = createString("itemTitle");

    public final NumberPath<Integer> ownerId = createNumber("ownerId", Integer.class);

    public final ListPath<ItemPhoto, QItemPhoto> photos = this.<ItemPhoto, QItemPhoto>createList("photos", ItemPhoto.class, QItemPhoto.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

