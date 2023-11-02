package egenius.dog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDogBreed is a Querydsl query type for DogBreed
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDogBreed extends EntityPathBase<DogBreed> {

    private static final long serialVersionUID = 348069343L;

    public static final QDogBreed dogBreed = new QDogBreed("dogBreed");

    public final StringPath dogBreedEngName = createString("dogBreedEngName");

    public final StringPath dogBreedKorName = createString("dogBreedKorName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDogBreed(String variable) {
        super(DogBreed.class, forVariable(variable));
    }

    public QDogBreed(Path<? extends DogBreed> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDogBreed(PathMetadata metadata) {
        super(DogBreed.class, metadata);
    }

}

