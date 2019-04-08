package comrastogiakshay.facebook.knowyourbookkyb;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import android.content.AsyncTaskLoader;
        import android.content.Context;

        import java.util.List;

public class bookLoader extends AsyncTaskLoader<List<OnBook>> {
    private String urls;
// Adding asynctaskloader to safe load.
    public bookLoader(Context context, String url){
        super(context);
        urls = url;

    }
    //This is the execution of Loader.
    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    //Performing the loading job in background.
    @Override
    public List<OnBook> loadInBackground() {
        // Don't perform the request if the URL IS EMPTY.
        if (urls == null){
            return null;

        }
        List<OnBook> Books = QueryBook.fetchBookData(urls);
        return Books;

    }
}


