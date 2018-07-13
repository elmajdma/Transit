package elmajdma.transit.remote;

import elmajdma.transit.model.GooglePlaceAutoCompletePredictions;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceAutoCompleteApiClient {
  //https://maps.googleapis.com
  //maps.googleapis.com/maps/api/place/autocomplete/json
  // ?input=haram&components=country:eg&language=ar&key=AIzaSyBFdCpQoOeex71vHb1orvDCG5Uq4y3iqcE

  @GET("/maps/api/place/autocomplete/json")
  Observable<List<GooglePlaceAutoCompletePredictions>> getAutocompletePredictions(
      @Query("input") String input,
      @Query("components") String country,
      @Query("language") String language,
      @Query("key") String key);

  @GET("/maps/api/place/autocomplete/json?input=haram&components=country:eg&language=ar&key=AIzaSyBFdCpQoOeex71vHb1orvDCG5Uq4y3iqcE")
  Observable<List<GooglePlaceAutoCompletePredictions>> getAutocompletePredictionsTest(
      );


  //https://maps.googleapis.com/maps/api/place/autocomplete/json?input=haram&key=AIzaSyBFdCpQoOeex71vHb1orvDCG5Uq4y3iqcE
  @GET("/maps/api/place/autocomplete/json")
  Observable<List<GooglePlaceAutoCompletePredictions>> getAutocompletePredictionsTest2(
      @Query("input") String input,
      @Query("key") String key);
}
