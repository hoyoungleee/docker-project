package com.playdata.productservice.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -957965400L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.playdata.productservice.common.entity.QBaseTimeEntity _super = new com.playdata.productservice.common.entity.QBaseTimeEntity(this);

    public final QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath description = createString("description");

    public final StringPath mainImagePath = createString("mainImagePath");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final ListPath<ProductImages, QProductImages> productImages = this.<ProductImages, QProductImages>createList("productImages", ProductImages.class, QProductImages.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public final StringPath thumbnailPath = createString("thumbnailPath");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

