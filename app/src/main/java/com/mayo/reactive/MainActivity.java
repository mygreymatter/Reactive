package com.mayo.reactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "Retrofit";

    TextView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (TextView) findViewById(R.id.click);

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://demo7398861.mockable.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            QuotesService service = retrofit.create(QuotesService.class);

            service.getQuote().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(quote -> quote.author)
                    .subscribe(s -> {
                        Log.d(LOG, "Quote Response: " + s);
                    });

            service.getQuotes().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(quotes -> Observable.from(quotes))
                    .flatMap(quote -> getAuthor(quote))
                    .subscribe(author -> {
                        Log.d(LOG, "Author: " + author);
                        /*for (Quote q : quotes) {
                            Log.d(LOG, "Quotes Response: " + q.toString());
                        }*/

                        //The foreach loop can be replaced as below
                        //from creates an Observable from a list
                        /*Observable.from(quotes)
                                .subscribe(quote -> {
                                    Log.d(LOG, "Quotes Response: " + quote.toString());
                                });*/
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

        RxView.clicks(click)
                .skip(2)
                .subscribe(aVoid -> {
                    click.setText(R.string.click);
                });
    }

    /**
     * Creates an Observable
     * @param quote
     * @return an Observable
     */
    private Observable<String> getAuthor(Quote quote){
        return  Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(quote.author);
                subscriber.onCompleted();
            }
        });
    }
}
