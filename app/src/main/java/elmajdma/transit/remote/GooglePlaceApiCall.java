package elmajdma.transit.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import elmajdma.transit.R;
import elmajdma.transit.model.GooglePlaceAutoCompletePredictions;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GooglePlaceApiCall {
  private static final String GOOGLE_MAPS_API_BASE_URL = "https://maps.googleapis.com";
  private static final String GOOGLE_MAPS_API_KEY = "AIzaSyBFdCpQoOeex71vHb1orvDCG5Uq4y3iqcE";


  public static Observable<List<GooglePlaceAutoCompletePredictions>>
  getGooglePlaceAutoCompletePredictionsList(String input,String country,String language) {
    final Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new GooglePlaceAutoCompleteTypeAdapterFactory())
            .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(GOOGLE_MAPS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    GooglePlaceAutoCompleteApiClient googlePlaceAutoCompleteApiClient =
        retrofit.create(GooglePlaceAutoCompleteApiClient.class);
    return googlePlaceAutoCompleteApiClient.
        getAutocompletePredictions(input,country,language,GOOGLE_MAPS_API_KEY);
    //getAutocompletePredictions(GOOGLE_MAPS_API_KEY,input,country,language);
  }

  public static Observable<List<GooglePlaceAutoCompletePredictions>>
  getGooglePlaceAutoCompletePredictionsListTest() {
    final Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new GooglePlaceAutoCompleteTypeAdapterFactory())
            .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(GOOGLE_MAPS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    GooglePlaceAutoCompleteApiClient googlePlaceAutoCompleteApiClient =
        retrofit.create(GooglePlaceAutoCompleteApiClient.class);
    return googlePlaceAutoCompleteApiClient.
        getAutocompletePredictionsTest();
  }
  public static Observable<List<GooglePlaceAutoCompletePredictions>>
  getGooglePlaceAutoCompletePredictionsListTest2(String input) {
    final Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new GooglePlaceAutoCompleteTypeAdapterFactory())
            .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(GOOGLE_MAPS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    GooglePlaceAutoCompleteApiClient googlePlaceAutoCompleteApiClient =
        retrofit.create(GooglePlaceAutoCompleteApiClient.class);
    return googlePlaceAutoCompleteApiClient.
        getAutocompletePredictionsTest2(input,GOOGLE_MAPS_API_KEY);
  }

}
