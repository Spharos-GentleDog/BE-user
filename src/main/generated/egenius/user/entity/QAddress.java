package egenius.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 1781799034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final egenius.global.base.QBaseTimeEntity _super = new egenius.global.base.QBaseTimeEntity(this);

    public final StringPath addressName = createString("addressName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath entrancePassword = createString("entrancePassword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath requestMessage = createString("requestMessage");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userAddress = createString("userAddress");

    public final QUser userId;

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddress(PathMetadata metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId")) : null;
    }

}

