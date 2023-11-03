package egenius.address.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddressList is a Querydsl query type for AddressList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddressList extends EntityPathBase<AddressList> {

    private static final long serialVersionUID = 1999502931L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddressList addressList = new QAddressList("addressList");

    public final QAddress address;

    public final BooleanPath defaultAddress = createBoolean("defaultAddress");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final egenius.user.entity.QUser user;

    public QAddressList(String variable) {
        this(AddressList.class, forVariable(variable), INITS);
    }

    public QAddressList(Path<? extends AddressList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddressList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddressList(PathMetadata metadata, PathInits inits) {
        this(AddressList.class, metadata, inits);
    }

    public QAddressList(Class<? extends AddressList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.user = inits.isInitialized("user") ? new egenius.user.entity.QUser(forProperty("user")) : null;
    }

}

