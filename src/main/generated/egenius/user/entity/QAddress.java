package egenius.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 1781799034L;

    public static final QAddress address = new QAddress("address");

    public final egenius.global.base.QBaseTimeEntity _super = new egenius.global.base.QBaseTimeEntity(this);

    public final StringPath addressName = createString("addressName");

    public final StringPath addressRequestMessage = createString("addressRequestMessage");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath entrancePassword = createString("entrancePassword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath recipientName = createString("recipientName");

    public final StringPath recipientPhoneNumber = createString("recipientPhoneNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userAddress = createString("userAddress");

    public QAddress(String variable) {
        super(Address.class, forVariable(variable));
    }

    public QAddress(Path<? extends Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddress(PathMetadata metadata) {
        super(Address.class, metadata);
    }

}

