package egenius.dog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDogList is a Querydsl query type for DogList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDogList extends EntityPathBase<DogList> {

    private static final long serialVersionUID = 981349075L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDogList dogList = new QDogList("dogList");

    public final BooleanPath defaultDog = createBoolean("defaultDog");

    public final QDog dog;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final egenius.user.entity.QUser user;

    public QDogList(String variable) {
        this(DogList.class, forVariable(variable), INITS);
    }

    public QDogList(Path<? extends DogList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDogList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDogList(PathMetadata metadata, PathInits inits) {
        this(DogList.class, metadata, inits);
    }

    public QDogList(Class<? extends DogList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dog = inits.isInitialized("dog") ? new QDog(forProperty("dog"), inits.get("dog")) : null;
        this.user = inits.isInitialized("user") ? new egenius.user.entity.QUser(forProperty("user")) : null;
    }

}

