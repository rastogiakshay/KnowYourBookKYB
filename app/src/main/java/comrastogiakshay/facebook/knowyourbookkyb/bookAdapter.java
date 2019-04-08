package comrastogiakshay.facebook.knowyourbookkyb;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

    public class bookAdapter extends ArrayAdapter<OnBook> {
    public bookAdapter(Activity context, ArrayList<OnBook> bookList){
        //this.bookList = bookList;
        super(context,0,bookList);
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OnBook currentBook = getItem(position);
        View ListItem = convertView;


        if (ListItem == null) {
        ListItem = LayoutInflater.from(getContext()).inflate(
        R.layout.layout, parent, false);}



        ImageView magnitude = (ImageView) ListItem.findViewById(R.id.book_Cover);

//        magnitude.setImageDrawable(fetchBookCoverImage().gettitle());

        TextView placeA = (TextView) ListItem.findViewById(R.id.location_offset);

        placeA.setText(currentBook.getauthors());

        TextView placeB = (TextView) ListItem.findViewById(R.id.primary_location);

        placeB.setText(currentBook.getpublisher());

        TextView date = (TextView) ListItem.findViewById(R.id.date);

        date.setText(currentBook.getdescription());

        TextView time = (TextView) ListItem.findViewById(R.id.time);

        time.setText(currentBook.getwebReaderLink());



        return ListItem;
        }
        public Drawable fetchBookCoverImage(String ImageURL){
        try {
            InputStream is = (InputStream) new URL(ImageURL).getContent();
            Drawable d = Drawable.createFromStream(is,"CoverImage");
            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
