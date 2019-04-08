package comrastogiakshay.facebook.knowyourbookkyb;

public class OnBook {
    private String mTitle;
    private String mAuthors;
    private String mPublisher;
    private String mWebReaderLink;
    private String mDescription;
    //private String mURL;

    public OnBook(String title, String authors, String publisher, String webReaderLink, String description) {
        mTitle = title;
        mAuthors = authors;
        mPublisher = publisher;
        mWebReaderLink = webReaderLink;
        mDescription = description;
       // mURL = URL;

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

//    //public String getURL() {
//        return mURL;
//    }

}