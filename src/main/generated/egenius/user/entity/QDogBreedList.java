package egenius.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDogBreedList is a Querydsl query type for DogBreedList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDogBreedList extends EntityPathBase<DogBreedList> {

    private static final long serialVersionUID = 2001979792L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDogBreedList dogBreedList = new QDogBreedList("dogBreedList");

    public final BooleanPath defaultDog = createBoolean("defaultDog");

    public final QDogBreed dogBreed;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final QUser user;

    public QDogBreedList(String variable) {
        this(DogBreedList.class, forVariable(variable), INITS);
    }

    public QDogBreedList(Path<? extends DogBreedList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDogBreedList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDogBreedList(PathMetadata metadata, PathInits inits) {
        this(DogBreedList.class, metadata, inits);
    }

    public QDogBreedList(Class<? extends DogBreedList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dogBreed = inits.isInitialized("dogBreed") ? new QDogBreed(forProperty("dogBreed")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

