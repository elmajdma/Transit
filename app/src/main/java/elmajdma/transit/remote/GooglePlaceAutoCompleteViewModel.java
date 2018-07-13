package elmajdma.transit.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import elmajdma.transit.R;
import elmajdma.transit.model.GooglePlaceAutoCompletePredictions;
import java.util.List;

public class GooglePlaceAutoCompleteViewModel extends ViewModel {

  LiveData<List<GooglePlaceAutoCompletePredictions>>
      googlexPlaceAutoCompletePredcitionList
      = new MutableLiveData<List<GooglePlaceAutoCompletePredictions>>();


  public LiveData<List<GooglePlaceAutoCompletePredictions>> getGooglePlaceAutoComletePrediction(){
    return googlexPlaceAutoCompletePredcitionList;
  }

  public GooglePlaceAutoCompleteViewModel() {

  }


}
