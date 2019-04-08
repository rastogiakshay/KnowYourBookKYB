package comrastogiakshay.facebook.knowyourbookkyb;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class BookShow extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<OnBook>> {
    private TextView mEmptyView;
    // assigning USGS HTTPS site to USGS_Url.
    private static final String google_books_url = "https://www.googleapis.com/books/v1/volumes?q=knowledge";
    // Adapter for the list of Books.
    private bookAdapter mAdapter;
    private static final String LOG_TAG = BookShow.class.getName();
    private static final int Earthquake_Loader_ID =1;

    @Override
    public Loader<List<OnBook>> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<OnBook>> loader, List<OnBook> onBooks) {

    }

    @Override
    public void onLoaderReset(Loader<List<OnBook>> loader) {

    }
}
