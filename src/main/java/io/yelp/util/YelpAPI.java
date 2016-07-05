package io.yelp.util;
import io.yelp.domain.Business;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
/**
 * Created by mikailaakeredolu on 6/20/16.
 */
public class YelpAPI {

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "pizza";
    private static final String DEFAULT_LOCATION = "Philadelphia, PA";
    private static final int SEARCH_LIMIT = 20;
    private static final String SEARCH_PATH = "/v2/search";
    private static final String BUSINESS_PATH = "/v2/business";
    private static final String CONSUMER_KEY = "DTIAMGukH_VE_Zqjveb23Q";
    private static final String CONSUMER_SECRET = "lgE5dlOTYdosCjDfb42zkzpZ_Lg";
    private static final String TOKEN = "xQvQWx1jw6h0t7joQcQTsqRHgUuT_25t";
    private static final String TOKEN_SECRET = "_3VVZ2aX7-hZazaROpLS_NazZEE";
    OAuthService service;
    Token accessToken;
    public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service =
                new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                        .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }
    public String searchForBusinessesByLocation(String term, String location) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
        return sendRequestAndGetResponse(request);
    }
    public String searchByBusinessId(String businessID) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
        return sendRequestAndGetResponse(request);
    }
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
        return request;
    }
    private String sendRequestAndGetResponse(OAuthRequest request) {
        System.out.println("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
    private static void queryAPI(YelpAPI yelpApi, YelpAPICLI yelpApiCli) {
        String searchResponseJSON =
                yelpApi.searchForBusinessesByLocation(yelpApiCli.term, yelpApiCli.location);
        ObjectMapper mapper = new ObjectMapper();
        String prettyBusiness1="";
        JSONParser parser = new JSONParser();
        JSONObject response = null;
        try {
            response = (JSONObject) parser.parse(searchResponseJSON);
        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(searchResponseJSON);
            System.exit(1);
        }
        JSONArray businesses = (JSONArray) response.get("businesses");
        String firstBusinessID;
        for(int x = 0; x < businesses.size(); x++){
            JSONObject firstBusiness = (JSONObject) businesses.get(x);
            firstBusinessID = firstBusiness.get("id").toString();
            System.out.println(String.format(
                    "%s businesses found, querying business info for the top result \"%s\" ...",
                    businesses.size(), firstBusinessID));
            // Select the first business and display business details
            String businessResponseJSON = yelpApi.searchByBusinessId(firstBusinessID.toString());
            try {
                Business preetyb = mapper.readValue(businessResponseJSON, Business.class);
                System.out.println(preetyb.getName());
                System.out.println(preetyb.getRating());
                System.out.println(preetyb.getReview_count());
                //System.out.println(preetyb.getMobile_url());
                System.out.println(preetyb.getPhone());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static class YelpAPICLI {
        @Parameter(names = {"-q", "--term"}, description = "Search Query Term")
        public String term = DEFAULT_TERM;
        @Parameter(names = {"-l", "--location"}, description = "Location to be Queried")
        public String location = DEFAULT_LOCATION;
    }
    public static void main(String[] args) {
        YelpAPICLI yelpApiCli = new YelpAPICLI();
        new JCommander(yelpApiCli, args);
        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        queryAPI(yelpApi, yelpApiCli);
        //   yelpApi.run();
    }
}
