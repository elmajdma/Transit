package elmajdma.transit.locationsearch;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;


import com.jakewharton.rxbinding.widget.RxTextView;
import elmajdma.transit.R;
import elmajdma.transit.model.GooglePlaceAutoCompletePredictions;
import elmajdma.transit.remote.GooglePlaceApiCall;
import elmajdma.transit.remote.GooglePlaceAutoCompleteViewModel;


import elmajdma.transit.userlocation.GooglePlaceAutocompleteRecyclerViewAdapter;
import elmajdma.transit.userlocation.GooglePlaceAutocompleteRecyclerViewAdapter.LocationSelectAdapterOnClickHandler;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;




/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLocationFragment extends Fragment {

  @BindView(R.id.recycler_view_result_locations_list)
  RecyclerView locationListRecyclerView;
  @BindView(R.id.et_edit_location_from)
  EditText mEditTextLocationFrom;

  @BindView(R.id.et_edit_location_to)
  EditText mEditTextLocationTo;
  @BindView(R.id.cardView_to_location)
  CardView cardViewToLocation;



  public SearchLocationFragment() {
    // Required empty public constructor
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view= inflater.inflate(R.layout.fragment_search_location, container, false);
    ButterKnife.bind(this, view);
    initViews();

    return view;
  }
  private void initViews() {

    locationListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    locationListRecyclerView.setHasFixedSize(true);
    locationListRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
  }


  private Observable<List<GooglePlaceAutoCompletePredictions>>
  getSearchLocation(String searchCritria){
    return GooglePlaceApiCall
        .getGooglePlaceAutoCompletePredictionsList(searchCritria,"country:eg","en");

  }

  @SuppressLint("CheckResult")
  private void setUpRxTextBindingObservable (EditText mEditText) {
    RxEditTextObservable.fromView(mEditTextLocationFrom)
        .debounce(300, TimeUnit.MILLISECONDS)
        .filter(new Predicate<String>() {
          @Override
          public boolean test(String text) throws Exception {
            if (text.isEmpty()) {
              //textViewResult.setText("");
              Log.d("Listxxx_Location",text);
              return false;
            } else {

              return true;
            }
          }
        })
        .distinctUntilChanged()
        .switchMap(new Function<String, ObservableSource<List<GooglePlaceAutoCompletePredictions>>>() {
          @Override
          public ObservableSource<List<GooglePlaceAutoCompletePredictions>> apply(String query) throws Exception {
            Log.d("Listx_Location",query);
            return getSearchLocation(query);
            //return getSearchLocationTest(query);
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<GooglePlaceAutoCompletePredictions>>() {
          @Override
          public void accept(List<GooglePlaceAutoCompletePredictions> googlePlaceAutoCompletePredictions)
              throws Exception {
            Log.d("List_Location",googlePlaceAutoCompletePredictions.toString());
            setSearchMatchedLocationList(googlePlaceAutoCompletePredictions,mEditText);

          }
        });



  }
  private void setSearchMatchedLocationList(List<GooglePlaceAutoCompletePredictions> googlePlaceAutoCompletePredictions, EditText mEditText){
      GooglePlaceAutocompleteRecyclerViewAdapter.LocationSelectAdapterOnClickHandler
          mLocationSelectAdapterOnClickHandler=new LocationSelectAdapterOnClickHandler() {
        @Override
        public void onLocationSelectClick(int position, View v) {
          mEditText.setText(googlePlaceAutoCompletePredictions.get(position).getDescription());

        }
      };

    GooglePlaceAutocompleteRecyclerViewAdapter mGooglePlaceAutocompleteRecyclerViewAdapter=
        new GooglePlaceAutocompleteRecyclerViewAdapter(googlePlaceAutoCompletePredictions,getContext(),mLocationSelectAdapterOnClickHandler);
    locationListRecyclerView.setAdapter(mGooglePlaceAutocompleteRecyclerViewAdapter);

  }
  private void setFromToLocations(){
    cardViewToLocation.setVisibility(View.GONE);
    if(mEditTextLocationFrom.getText()==null){
      setUpRxTextBindingObservable (mEditTextLocationFrom);
      cardViewToLocation.setVisibility(View.VISIBLE);

    }

  }


}
