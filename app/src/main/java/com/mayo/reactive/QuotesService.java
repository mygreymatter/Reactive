package com.mayo.reactive;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by Mahayogi Lakshmipathi on 25/10/16.
 *
 * @author <a href="mailto:mygreymatter@gmail.com">Mahayogi Lakshmipathi</a>
 * @version 1.0
 */

interface QuotesService {

    @GET("/getquotes")
    Observable<List<Quote>> getQuotes();

    @GET("/quote")
    Observable<Quote> getQuote();
}
