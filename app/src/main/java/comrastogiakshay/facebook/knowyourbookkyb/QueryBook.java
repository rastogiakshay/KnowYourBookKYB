package comrastogiakshay.facebook.knowyourbookkyb;


import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class QueryBook  {
    private static URL createURL(String url){
        URL Book_Api = null;
        try {
            Book_Api = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Book_Api;
    }

    private static String QueryBook(URL url) throws IOException {
        InputStream stream = null;
        String result = "";
        HttpsURLConnection connection = null;
        if (url == null){
            return result;
        }

        try {
            // opening the URL.
            connection = (HttpsURLConnection) url.openConnection();
            // providing time out period of 3000s.
            connection.setReadTimeout(10000);
            // setting time out for reading the site.
            connection.setConnectTimeout(15000);
            //setting the request method.
            connection.setRequestMethod("GET");
            // stting the Input method to be true.
            // connection.setDoInput(true);
            // Opening the connection.
            connection.connect();
            // adding fetched json to stream
            stream = connection.getInputStream();
            //converting stream to string via method.
            result = convertStreamToString(stream);

        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (stream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                stream.close();
            }
        }
        return result;
    }
    private static Drawable fetchBookCoverImage(String ImageURL){
        try {
            InputStream is = (InputStream) new URL(ImageURL).getContent();
            Drawable d = Drawable.createFromStream(is,"CoverImage");
            return d;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    //Adding a string stream to string converter.
    public static String convertStreamToString(InputStream is){
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = br.readLine()) != null){
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * Return a list of {@link OnBook} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<OnBook> extractFeatureBooks(String BookJSON){
        //Return Null early is String is empty.
        if (TextUtils.isEmpty(BookJSON)){

            return null;
        }
          String publisher = null;
//        String placeA;
//        String placeB= null;
//        String placeC =null;
//        String decimalFormat=null;
//        String date=null;
//        String simpleTime=null;


        List<OnBook> onBooks = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject root = new JSONObject(BookJSON);
            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray jsonArray = root.optJSONArray("items");
            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i=0;i < jsonArray.length();i++){
                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentBook = jsonArray.getJSONObject(i);
                // For a given earthquake, extract the JSONObject associated with the
                // key called "volumeInfo", which represents a list of all volumeInfo
                // for that earthquake.
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                // Extract the value for the key called "mag"
                String title = volumeInfo.getString("title");
                // Extract the value for the key called "place"
                String authors = volumeInfo.getString("authors");
                // Extract the value for the key called "time"
                if ((volumeInfo.opt("publisher")) == null ){
                //if (publisher == null){
                     publisher = "empty" ;
                }
                else {
                    publisher = volumeInfo.getString("publisher");
                }

                // Extract the value for the key called "url"
                String webReaderLink = volumeInfo.getString("infoLink");
                // Extract the value for the key called "description"
                String description;
                if ((volumeInfo.opt("description")) == null){
                    //if (publisher == null){
                    description = "empty" ;

                }
                else {

                    description = volumeInfo.getString("description");
                }
                //Extracting the ImageResource URL from the key "smallThumbnail"
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String smallThumbnail = imageLinks.getString("smallThumbnail");


                //Fetching of Image from String.
                Drawable fetchedImage;
                fetchedImage = fetchBookCoverImage(smallThumbnail);

                //Conversion of time.
//                Date inMilisec = new Date(time);
//                String convertDate = date.format(inMilisec);
//                String convertTime = simpleTime.format(inMilisec);

//                if(place.contains("of")){
//                    String[] parts =  place.split("of");
//                    placeA = parts[0];
//                    placeB = parts[1];
//                    placeC = placeA.concat(" of");
//                }
//                else {
//                    String[] parts = place.split("the");
//                    placeA = parts[0];
//                    placeB = parts[1];
//                    placeC = placeA.concat(" the");
//                }
                OnBook onBook = new OnBook(title,authors,publisher,webReaderLink,description,fetchedImage);

                onBooks.add(onBook);
            }

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryBook", "Problem parsing the earthquake JSON results", e);
        }


        // Return the list of earthquakes
        return onBooks;
    }
    public static List<OnBook> fetchBookData(String reqURL){
        // creating the URL object.
        URL url = createURL(reqURL);
        String result = null;

        //make HTTP request.
        try {
            result = QueryBook(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //extract relevant fields from the json response and create a list @ earthquake
        List<OnBook> onBooks = extractFeatureBooks(result);
        //return the list @earthquake
        return onBooks;
    }


}



