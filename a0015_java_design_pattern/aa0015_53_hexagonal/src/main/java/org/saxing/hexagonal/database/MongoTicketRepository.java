package org.saxing.hexagonal.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.saxing.hexagonal.domain.LotteryTicket;
import org.saxing.hexagonal.domain.LotteryTicketId;

import java.util.Map;
import java.util.Optional;

/**
 * Mongo lottery ticket database
 *
 * @author saxing 2019/1/29 23:06
 */
public class MongoTicketRepository implements LotteryTicketRepository {

    private static final String DEFAULT_DB = "lotteryDB";
    private static final String DEFAULT_TICKETS_COLLECTION = "lotteryTickets";
    private static final String DEFAULT_COUNTERS_COLLECTION = "counters";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> ticketsCollection;
    private MongoCollection<Document> countersCollection;

    public MongoTicketRepository() {
        connect();
    }

    public MongoTicketRepository(String dbName, String ticketsCollectionName,
                                 String countersCollectionName){
        connect(dbName, ticketsCollectionName, countersCollectionName);
    }

    public void connect(){
        connect(DEFAULT_DB, DEFAULT_TICKETS_COLLECTION, DEFAULT_COUNTERS_COLLECTION);
    }

    public void connect(String dbName, String ticketsCollectionName,
                        String countersCollectionName){
        if (mongoClient != null) {
            mongoClient.close();
        }
        mongoClient = new MongoClient(System.getProperty("mongo-host"),
                Integer.parseInt(System.getProperty("mongo-port")));
        database = mongoClient.getDatabase(dbName);
        ticketsCollection = database.getCollection(ticketsCollectionName);
        countersCollection = database.getCollection(countersCollectionName);
        if (countersCollection.count() <= 0) {
            initCounters();
        }
    }

    @Override
    public Optional<LotteryTicket> findById(LotteryTicketId id) {
        return Optional.empty();
    }

    @Override
    public Optional<LotteryTicketId> save(LotteryTicket ticket) {
        return Optional.empty();
    }

    @Override
    public Map<LotteryTicketId, LotteryTicket> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
