package com.example.demo.model.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemPhoto is a Querydsl query type for ItemPhoto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemPhoto extends EntityPathBase<ItemPhoto> {

    private static final long serialVersionUID = 247073155L;

    public static final QItemPhoto itemPhoto1 = new QItemPhoto("itemPhoto1");

    public final BooleanPath isMain = createBoolean("isMain");

    public final NumberPath<Integer> itemId = createNumber("itemId", Integer.class);

    public final StringPath itemPhoto = createString("itemPhoto");

    public final NumberPath<Integer> itemPhotoIndex = createNumber("itemPhotoIndex", Integer.class);

    public QItemPhoto(String variable) {
        super(ItemPhoto.class, forVariable(variable));
    }

    public QItemPhoto(Path<? extends ItemPhoto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemPhoto(PathMetadata metadata) {
        super(ItemPhoto.class, metadata);
    }

}

