package servicios.mongo;

import api.PublicacionService;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PublicacionServiceMDB implements PublicacionService {
    private static final String PUBLICACIONES_COLLECTION_NAME = "publicaciones";
    private int dbPort;
    private String dbName;

    public PublicacionServiceMDB(int dbPort, String dbName) {
        this.dbName = dbName;
        this.dbPort = dbPort;
    }

    @Override
    public String ultimasPublicaciones() {
        try (MongoClient mongoClient = new MongoClient("localhost", dbPort)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            MongoCollection<Document> publicacionCollection = db.getCollection(PUBLICACIONES_COLLECTION_NAME);
            FindIterable<Document> publicaciones = publicacionCollection.find()
                    .sort(Sorts.descending("fecha")).limit(4);
            String publicacionesJSON = StreamSupport.stream(publicaciones.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]"));

            return publicacionesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String publicacion(String publicacionId) {
        try (MongoClient mongoClient = new MongoClient("localhost", dbPort)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            MongoCollection<Document> publicacionesCollection = db.getCollection(PUBLICACIONES_COLLECTION_NAME);
            FindIterable<Document> publicaciones = publicacionesCollection.find(Filters.eq("_id", new ObjectId(publicacionId)));
            String publicacionesJSON = StreamSupport.stream(publicaciones.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]"));

            return publicacionesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String publicaciones(String nombre) {
        try (MongoClient mongoClient = new MongoClient("localhost", dbPort)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            MongoCollection<Document> publicacionCollection = db.getCollection(PUBLICACIONES_COLLECTION_NAME);
            FindIterable<Document> publicaciones = publicacionCollection.find(Filters.eq("autor", nombre));
            String publicacionesJSON = StreamSupport.stream(publicaciones.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]"));

            return publicacionesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String autoresPublicaciones() {
        try (MongoClient mongoClient = new MongoClient("localhost", dbPort)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            MongoCollection<Document> publicacionCollection = db.getCollection(PUBLICACIONES_COLLECTION_NAME);

            AggregateIterable<Document> publicaciones = publicacionCollection.aggregate(Arrays.asList(
                    Aggregates.group("$autor", Accumulators.sum("count", 1))));

            String publicacionesJSON = StreamSupport.stream(publicaciones.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]"));

            return publicacionesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String publicacionesPorTexto(String texto) {
        try (MongoClient mongoClient = new MongoClient("localhost", dbPort)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);

            MongoCollection<Document> publicacionCollection = db.getCollection(PUBLICACIONES_COLLECTION_NAME);
            FindIterable<Document> publicaciones = publicacionCollection.find(Filters.text(texto));
            String publicacionesJSON = StreamSupport.stream(publicaciones.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]"));

            return publicacionesJSON;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
