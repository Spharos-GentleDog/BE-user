package egenius.dog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDog is a Querydsl query type for Dog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDog extends EntityPathBase<Dog> {

    private static final long serialVersionUID = 72769685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDog dog = new QDog("dog");

    public final egenius.global.base.QBaseTimeEntity _super = new egenius.global.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> dogAge = createNumber("dogAge", Integer.class);

    public final NumberPath<Integer> dogBodyLength = createNumber("dogBodyLength", Integer.class);

    public final NumberPath<Integer> dogBreastGirth = createNumber("dogBreastGirth", Integer.class);

    public final QDogBreed dogBreed;

    public final StringPath dogFurColor = createString("dogFurColor");

    public final NumberPath<Integer> dogGender = createNumber("dogGender", Integer.class);

    public final StringPath dogImageUrl = createString("dogImageUrl");

    public final NumberPath<Integer> dogLegLength = createNumber("dogLegLength", Integer.class);

    public final StringPath dogName = createString("dogName");

    public final NumberPath<Integer> dogNeckGirth = createNumber("dogNeckGirth", Integer.class);

    public final NumberPath<Integer> dogWeight = createNumber("dogWeight", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDog(String variable) {
        this(Dog.class, forVariable(variable), INITS);
    }

    public QDog(Path<? extends Dog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDog(PathMetadata metadata, PathInits inits) {
        this(Dog.class, metadata, inits);
    }

    public QDog(Class<? extends Dog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dogBreed = inits.isInitialized("dogBreed") ? new QDogBreed(forProperty("dogBreed")) : null;
    }

}

