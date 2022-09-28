package servicios;

import api.PaginaService;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PaginaServiceMDB implements PaginaService {
    private static final String PAGINAS_COLLECTION_NAME = "paginas";
    private int dbPort;
    private String dbName;
    public PaginaServiceMDB(int dbPort, String dbName) {
        this.dbPort = dbPort;
        this.dbName = dbName;
    }

    @Override
    public String pagina(String paginaId) {
        try (MongoClient mongoClient = new MongoClient( "localhost" , dbPort)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            MongoCollection<Document> paginasCollection = db.getCollection(PAGINAS_COLLECTION_NAME);
            FindIterable<Document> paginas = paginasCollection.find(Filters.eq("_id", new ObjectId(paginaId)));
            String publicacionesJSON = StreamSupport.stream(paginas.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]"));

            return publicacionesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
