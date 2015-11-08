package com.hackathonproject.YelpApi;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.hackathonproject.Search.SearchResult;

/**
 * Code sample for accessing the Yelp API V2.
 *
 * This program demonstrates the capability of the Yelp API version 2.0 by using
 * the Search API to query for businesses by a search term and location, and the
 * Business API to query additional information about the top result from the
 * search query.
 *
 * <p>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp
 * Documentation</a> for more info.
 *
 */
public class YelpAPI {

    public static final String API_HOST = "api.yelp.com";
    public static final String DEFAULT_TERM = "dinner";
    public static final String DEFAULT_LOCATION = "San Francisco, CA";
    public static final int SEARCH_LIMIT = 6;
    public static final int RADIUS_LIMIT = 50000;
    public static final String SEARCH_PATH = "/v2/search";
    public static final String BUSINESS_PATH = "/v2/business";
    public static final String SORT = "1";
    public static final String ACTION_LINKS = "true";


    /*
     * Update OAuth credentials below from the Yelp Developers API site:
     * http://www.yelp.com/developers/getting_started/api_access
     */
    public static final String CONSUMER_KEY = "oJ0-HIrdcf0EQsEApnDKwQ";
    public static final String CONSUMER_SECRET = "4ycgEy3wZmH8zk9dc4TR_5l4dP4";
    public static final String TOKEN = "WvbFiCS_wDQNfVtEKAPSXLlpoNh9z9qc";
    public static final String TOKEN_SECRET = "aT2ydYDO_hNE4Nwn7hIl-_VbO9s";

    OAuthService service;
    Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * @param consumerKey
     *            Consumer key
     * @param consumerSecret
     *            Consumer secret
     * @param token
     *            Token
     * @param tokenSecret
     *            Token secret
     */
    public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey).apiSecret(consumerSecret)
                .build();
        this.accessToken = new Token(token, tokenSecret);
    }

    /**
     * Creates and sends a request to the Search API by term and location.
     * <p>
     * See
     * <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp
     * Search API V2</a> for more info.
     *
     * @param term
     *            <tt>String</tt> of the search term to be queried
     * @param location
     *            <tt>String</tt> of the location
     * @return <tt>String</tt> JSON Response
     */
    public String searchForBusinessesByLocation(String term, String location) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
        return sendRequestAndGetResponse(request);
    }
    //?term=dinner&location=San Francisco, CA&limit=6&radius_filter=10000&actionlinks=true&category_filter=pizza
    public String searchRelevanceByLocation(String term, String location) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);//RADIUS_LIMIT
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
        request.addQuerystringParameter("radius_filter", String.valueOf(RADIUS_LIMIT));
        request.addQuerystringParameter("sort", SORT);
        request.addQuerystringParameter("actionlinks", ACTION_LINKS);
//		request.addQuerystringParameter("licategory_filtermit", category);
        return sendRequestAndGetResponse(request);
    }

    /**
     * Creates and sends a request to the Business API by business ID.
     * <p>
     * See
     * <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp
     * Business API V2</a> for more info.
     *
     * @param businessID
     *            <tt>String</tt> business ID of the requested business
     * @return <tt>String</tt> JSON Response
     */
    public String searchByBusinessId(String businessID) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
        return sendRequestAndGetResponse(request);
    }

    /**
     * Creates and returns an {@link OAuthRequest} based on the API endpoint
     * specified.
     *
     * @param path
     *            API endpoint to be queried
     * @return <tt>OAuthRequest</tt>
     */
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
        return request;
    }

    /**
     * Sends an {@link OAuthRequest} and returns the {@link Response} body.
     *
     * @param request
     *            {@link OAuthRequest} corresponding to the API request
     * @return <tt>String</tt> body of API response
     */
    private String sendRequestAndGetResponse(OAuthRequest request) {
        System.out.println("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    /**
     * Queries the Search API based on the command line arguments and takes the
     * first result to query the Business API.
     *
     * @param yelpApi
     *            <tt>YelpAPI</tt> service instance
     * @param yelpApiCli
     *            <tt>YelpAPICLI</tt> command line arguments
     */
    public String queryAPI(YelpAPI yelpApi, YelpAPICLI yelpApiCli, String term, String location) throws JSONException {
        //String searchResponseJSON = yelpApi.searchForBusinessesByLocation(yelpApiCli.term, yelpApiCli.location);
        String searchResponseJSON = yelpApi.searchRelevanceByLocation(term, location);
//		JSONParser parser = new JSONParser();
        JSONObject response = null;
        try {
//			response = (JSONObject) parser.parse(searchResponseJSON);
            response = new JSONObject(searchResponseJSON);
        } catch (Exception pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(searchResponseJSON);
            System.exit(1);
        }

        JSONArray suggests = (JSONArray) response.get("businesses");
        int count = suggests.length();

        for (int i=0 ; i<count; i++) {
            JSONObject firstBusiness = (JSONObject) suggests.get(i);
            String contactInfo = firstBusiness.get("display_phone") == null? null : firstBusiness.get("display_phone").toString();
            String isClosed = firstBusiness.get("is_closed") == null? null : firstBusiness.get("is_closed").toString();
            String img = firstBusiness.get("image_url") == null? null : firstBusiness.get("image_url").toString();
            String rate = firstBusiness.get("rating") == null? null : firstBusiness.get("rating").toString();
            String displayAddress = firstBusiness.getJSONObject("location") == null? null : firstBusiness.getJSONObject("location").get("display_address").toString();
            SearchResult searchResult = new SearchResult();
        }



        return suggests.toString();
    }

    /**
     * Command-line interface for the sample Yelp API runner.
     */
    public static class YelpAPICLI {
        @Parameter(names = { "-q", "--term" }, description = "Search Query Term")
        public String term = DEFAULT_TERM;

        @Parameter(names = { "-l", "--location" }, description = "Location to be Queried")
        public String location = DEFAULT_LOCATION;
    }
}
