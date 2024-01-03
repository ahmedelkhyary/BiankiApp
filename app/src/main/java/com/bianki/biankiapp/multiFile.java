package com.bianki.biankiapp;

import android.net.Uri;

public class multiFile {
    Uri uri ;

    public multiFile(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
