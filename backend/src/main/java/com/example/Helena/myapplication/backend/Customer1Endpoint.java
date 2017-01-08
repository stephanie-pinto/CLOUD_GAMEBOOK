package com.example.Helena.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "customer1Api",
        version = "v1",
        resource = "customer1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Helena.example.com",
                ownerName = "backend.myapplication.Helena.example.com",
                packagePath = ""
        )
)
public class Customer1Endpoint {

    private static final Logger logger = Logger.getLogger(Customer1Endpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Customer1.class);
    }

    /**
     * Returns the {@link Customer1} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Customer1} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "customer1/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Customer1 get(@Named("_id") Long _id) throws NotFoundException {
        logger.info("Getting Customer1 with ID: " + _id);
        Customer1 customer1 = ofy().load().type(Customer1.class).id(_id).now();
        if (customer1 == null) {
            throw new NotFoundException("Could not find Customer1 with ID: " + _id);
        }
        return customer1;
    }

    /**
     * Inserts a new {@code Customer1}.
     */
    @ApiMethod(
            name = "insert",
            path = "customer1",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Customer1 insert(Customer1 customer1) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that customer1._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(customer1).now();
        logger.info("Created Customer1.");

        return ofy().load().entity(customer1).now();
    }

    /**
     * Updates an existing {@code Customer1}.
     *
     * @param _id       the ID of the entity to be updated
     * @param customer1 the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Customer1}
     */
    @ApiMethod(
            name = "update",
            path = "customer1/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Customer1 update(@Named("_id") Long _id, Customer1 customer1) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(customer1).now();
        logger.info("Updated Customer1: " + customer1);
        return ofy().load().entity(customer1).now();
    }

    /**
     * Deletes the specified {@code Customer1}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Customer1}
     */
    @ApiMethod(
            name = "remove",
            path = "customer1/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") Long _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Customer1.class).id(_id).now();
        logger.info("Deleted Customer1 with ID: " + _id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "customer1",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Customer1> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Customer1> query = ofy().load().type(Customer1.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Customer1> queryIterator = query.iterator();
        List<Customer1> customer1List = new ArrayList<Customer1>(limit);
        while (queryIterator.hasNext()) {
            customer1List.add(queryIterator.next());
        }
        return CollectionResponse.<Customer1>builder().setItems(customer1List).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long _id) throws NotFoundException {
        try {
            ofy().load().type(Customer1.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Customer1 with ID: " + _id);
        }
    }
}