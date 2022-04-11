package com.example.demo.model.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1656575759L;

    public static final QReview review = new QReview("review");

    public final NumberPath<Integer> contractId = createNumber("contractId", Integer.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath reviewContent = createString("reviewContent");

    public final NumberPath<Integer> reviewId = createNumber("reviewId", Integer.class);

    public final NumberPath<Integer> reviewItem = createNumber("reviewItem", Integer.class);

    public final NumberPath<Integer> reviewScore = createNumber("reviewScore", Integer.class);

    public final NumberPath<Integer> reviewStatus = createNumber("reviewStatus", Integer.class);

    public final NumberPath<Integer> reviewTarget = createNumber("reviewTarget", Integer.class);

    public final StringPath reviewTitle = createString("reviewTitle");

    public final EnumPath<ReviewType> reviewType = createEnum("reviewType", ReviewType.class);

    public final NumberPath<Integer> reviewWriter = createNumber("reviewWriter", Integer.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

