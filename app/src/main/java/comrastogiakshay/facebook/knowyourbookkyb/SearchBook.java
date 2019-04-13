package comrastogiakshay.facebook.knowyourbookkyb;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SearchBook extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<OnBook>> {

    private TextView mEmptyView;
    // assigning USGS HTTPS site to USGS_Url.
    private static final String USGS_Url = "https://www.googleapis.com/books/v1/volumes?q=";
    private String SearchedText = "";
    // Adapter for the list of earthquakes.
    private bookAdapter mAdapter;
    private static final String LOG_TAG = SearchBook.class.getName();
    private static final int Book_Loader_ID = 0;
    private static final int Search_Loader_ID = 0;
    private String QueryString;
    private String appendURL ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "TEST: Oncreate is runned");
        //Throwing the content on the XML Layout.
        setContentView(R.layout.activity_search_results);
        //ArrayList<earthquake> quakeList = QueryUtils.extractFeatureEarthquakes();

        // Create a new adapter that takes an empty list of Earthquakes as an input.
        mAdapter = new bookAdapter(this, new ArrayList<OnBook>());
        // Find the reference to the listView in the layout
        ListView listView = findViewById(R.id.list2);
        // set the adapter on the {@link ListView}
        // so the lst can be populated on the screen.
        listView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                OnBook bookItems = mAdapter.getItem(i);

                Uri bookUri = null;
                if (bookItems != null) {
                    bookUri = Uri.parse(bookItems.getwebReaderLink());
                }
                Intent bookIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                startActivity(bookIntent);


            }


        });
        //Getting the reference of the loader manager, So as to interact with the loader.
        //LoaderManager loaderManager = getLoaderManager();
        //Added LoaderManager from restraining the excessive memory usage.

        //mEmptyView = (TextView)findViewById(R.id.emptyState);
        //listView.setEmptyView(mEmptyView);
        Intent intent = getIntent();

            QueryString = formatSearchText(intent.getStringExtra("Search_String"));


            appendURL = USGS_Url + QueryString;

        getLoaderManager().initLoader(Book_Loader_ID, null, this);
    }

    //Calling of the Loader
    @Override
    public Loader<List<OnBook>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, appendURL);
        return new bookLoader(this, appendURL);


    }

    //Setting up the UI
    @Override
    public void onLoadFinished(Loader<List<OnBook>> loader, List<OnBook> books) {
        Log.i(LOG_TAG, appendURL);
        //
           View progressBar = (View) findViewById(R.id.Loading);
           progressBar.setVisibility(View.GONE);

        //
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        } else {
            //mEmptyView.setText(R.string.Em_pty);
        }

    }

    //Resetting the Loader
    @Override
    public void onLoaderReset(Loader<List<OnBook>> loader) {
        Log.i(LOG_TAG, "TEST: onLoadReset is executed");
        mAdapter.clear();

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        handleIntent(intent);
//    }

    //    private void handleIntent(Intent intent) {
//        // Special processing of the incoming intent only occurs if the if the action specified
//        // by the intent is ACTION_SEARCH.
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            // SearchManager.QUERY is the key that a SearchManager will use to send a query string
//            // to an Activity.
//            String query = intent.getStringExtra(SearchManager.QUERY);
//
//            // We need to create a bundle containing the query string to send along to the
//            // LoaderManager, which will be handling querying the database and returning results.
//            Bundle bundle = new Bundle();
//            bundle.putString(QUERY_KEY, query);
//
//            SearchBook loaderCallbacks = new SearchBook(this);
//
//            // Start the loader with the new query, and an object that will handle all callbacks.
//            getLoaderManager().restartLoader(Search_Loader_ID, bundle, loaderCallbacks);
//        }
//    }
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            SearchedText = USGS_Url + query;
//            //process Cursor and display results
//            Log.i(LOG_TAG,SearchedText);
//        }


//    }
    private String formatSearchText(String string) {
        String trimmedString = string.trim();
    do {
        trimmedString = trimmedString.replace(" ", "+");
    } while (trimmedString.contains(" "));
    return trimmedString;
    }
}
