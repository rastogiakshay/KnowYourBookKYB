package comrastogiakshay.facebook.knowyourbookkyb;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

    public class bookAdapter extends ArrayAdapter<OnBook> {
    public bookAdapter(Activity context, ArrayList<OnBook> bookList){
        //this.bookList = bookList;
        super(context,0,bookList);
        }
        private static final String LOG_TAG = bookAdapter.class.getName();
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OnBook currentBook = getItem(position);
        View ListItem = convertView;


        if (ListItem == null) {
        ListItem = LayoutInflater.from(getContext()).inflate(
        R.layout.layout, parent, false);}



        ImageView book_cover =  ListItem.findViewById(R.id.book_Cover);

        assert currentBook != null;
        book_cover.setImageDrawable(currentBook.getsmallThumbnail());

        TextView title =  ListItem.findViewById(R.id.Title);

        title.setText(currentBook.gettitle());

        TextView publisher = ListItem.findViewById(R.id.publisher);

        publisher.setText(currentBook.getpublisher());
        //Log.i(LOG_TAG,currentBook.getpublisher());

        TextView author = ListItem.findViewById(R.id.author);

        author.setText(currentBook.getauthors());

        TextView description = ListItem.findViewById(R.id.description);

        description.setText(currentBook.getdescription());

        TextView time = ListItem.findViewById(R.id.time);

        //time.setText(currentBook.getwebReaderLink());



        return ListItem;
        }

}
