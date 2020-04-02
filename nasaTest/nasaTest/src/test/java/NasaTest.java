import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * @author jklouda
 * @since 27.03.2020
 */
public class NasaTest {

    public static void main (String[] args) {}
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Test
    public void getPhotos() throws Exception {
        URL nasaURL = new URL("https://images-api.nasa.gov/search?");

        Map<String,Object> urlParameters = new LinkedHashMap<>();
        urlParameters.put("keywords", "Mars");
        urlParameters.put("media_type", "image");
        urlParameters.put("year_start", "2018");
        urlParameters.put("year_end", "2018");

        NasaTest obj = new NasaTest();

        try {
            System.out.println("Send GET request");
            String url =  obj.createURL(nasaURL, urlParameters);
            obj.getResponse(url);
        } finally {
            obj.close();
        }
    }

    @Test
    public void getVideos() throws Exception {
        URL nasaURL = new URL("https://images-api.nasa.gov/search?");

        Map<String,Object> urlParameters = new LinkedHashMap<>();
        urlParameters.put("keywords", "Mars");
        urlParameters.put("media_type", "video");
        urlParameters.put("year_start", "2018");
        urlParameters.put("year_end", "2018");

        NasaTest obj = new NasaTest();

        try {
            System.out.println("Send GET request");
            String url =  obj.createURL(nasaURL, urlParameters);
            obj.getResponse(url);
        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private String createURL(URL nasaURL, Map<String,Object> urlParameters) throws Exception {
        StringBuilder getData = new StringBuilder();
        for (Map.Entry<String, Object> param : urlParameters.entrySet()) {
            if (getData.length() != 0) getData.append('&');
            getData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            getData.append('=');
            getData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
            String url = nasaURL + getData.toString();
            return url;
    }

    private void getResponse(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(result);
            System.out.println(jsonObject);
            JSONArray jsonArray = jsonObject.getJSONObject("collection").getJSONArray("items");
            System.out.println(jsonArray);

            for (int i = 0; i < 5; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String imageURL = String.valueOf(obj.getJSONArray("links"));
                System.out.println(imageURL);
            }
        }
    }
}