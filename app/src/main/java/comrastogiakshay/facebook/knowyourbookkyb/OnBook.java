package comrastogiakshay.facebook.knowyourbookkyb;

import android.graphics.drawable.Drawable;

public class OnBook {
    private String mTitle = "";
    private String mAuthors= "";
    private String mPublisher="";
    private String mWebReaderLink="";
    private String mDescription="";
    private Drawable mSmallThumbnail;

    public OnBook(String title, String authors, String publisher, String webReaderLink, String description, Drawable smallThumbnail) {
        mTitle = title;
        mAuthors = authors;
        mPublisher = publisher;
        mWebReaderLink = webReaderLink;
        mDescription = description;
        mSmallThumbnail = smallThumbnail;

    }

    public String gettitle() {
        return mTitle;
    }

    public String getauthors() {
        return mAuthors;
    }

    public String getpublisher() {
        return mPublisher;
    }

    public String getwebReaderLink() {
        return mWebReaderLink;
    }

    public String getdescription() {
        return mDescription;
    }

    public Drawable getsmallThumbnail() {
        return mSmallThumbnail;
    }

}