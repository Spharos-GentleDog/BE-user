package egenius.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDogBreedList is a Querydsl query type for DogBreedList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDogBreedList extends EntityPathBase<DogBreedList> {

    private static final long serialVersionUID = 2001979792L;

    public static final QDogBreedList dogBreedList = new QDogBreedList("dogBreedList");

    public final StringPath dogBreedName = createString("dogBreedName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDogBreedList(String variable) {
        super(DogBreedList.class, forVariable(variable));
    }

    public QDogBreedList(Path<? extends DogBreedList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDogBreedList(PathMetadata metadata) {
        super(DogBreedList.class, metadata);
    }

}

