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
        name = "bookingApi",
        version = "v1",
        resource = "booking",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Helena.example.com",
                ownerName = "backend.myapplication.Helena.example.com",
                packagePath = ""
        )
)
public class BookingEndpoint {

    private static final Logger logger = Logger.getLogger(BookingEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Booking.class);
    }

    /**
     * Returns the {@link Booking} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Booking} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "booking/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Booking get(@Named("_id") Long _id) throws NotFoundException {
        logger.info("Getting Booking with ID: " + _id);
        Booking booking = ofy().load().type(Booking.class).id(_id).now();
        if (booking == null) {
            throw new NotFoundException("Could not find Booking with ID: " + _id);
        }
        return booking;
    }

    /**
     * Inserts a new {@code Booking}.
     */
    @ApiMethod(
            name = "insert",
            path = "booking",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Booking insert(Booking booking) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that booking._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(booking).now();
        logger.info("Created Booking with ID :" + booking.getId());

        return ofy().load().entity(booking).now();
    }

    /**
     * Updates an existing {@code Booking}.
     *
     * @param _id     the ID of the entity to be updated
     * @param booking the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Booking}
     */
    @ApiMethod(
            name = "update",
            path = "booking/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Booking update(@Named("_id") Long _id, Booking booking) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(booking).now();
        logger.info("Updated Booking with ID : " + booking.getId() + booking);
        return ofy().load().entity(booking).now();
    }

    /**
     * Deletes the specified {@code Booking}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Booking}
     */
    @ApiMethod(
            name = "remove",
            path = "booking/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") Long _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Booking.class).id(_id).now();
        logger.info("Deleted Booking with ID: " + _id);
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
            path = "booking",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Booking> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Booking> query = ofy().load().type(Booking.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Booking> queryIterator = query.iterator();
        List<Booking> bookingList = new ArrayList<Booking>(limit);
        while (queryIterator.hasNext()) {
            bookingList.add(queryIterator.next());
        }
        return CollectionResponse.<Booking>builder().setItems(bookingList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long _id) throws NotFoundException {
        try {
            ofy().load().type(Booking.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Booking with ID: " + _id);
        }
    }
}