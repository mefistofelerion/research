import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<DataEntity, String> {

    public DataEntity findById(String firstName);
}