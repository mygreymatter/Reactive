package com.mayo.reactive;

/**
 * Created by Mahayogi Lakshmipathi on 25/10/16.
 *
 * @author <a href="mailto:mygreymatter@gmail.com">Mahayogi Lakshmipathi</a>
 * @version 1.0
 */

class Quote {
    String id;
    String quote;
    String author;
    String image_url;

    @Override
    public String toString(){
        return quote + " by " + author;
    }
}
