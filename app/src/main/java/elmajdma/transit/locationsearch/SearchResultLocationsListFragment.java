package elmajdma.transit.locationsearch;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import elmajdma.transit.R;
import elmajdma.transit.model.GooglePlaceAutoCompletePredictions;
import elmajdma.transit.remote.GooglePlaceAutoCompleteViewModel;
import elmajdma.transit.userlocation.GooglePlaceAutocompleteRecyclerViewAdapter;
import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultLocationsListFragment extends Fragment {
  @BindView(R.id.recycler_view_result_locations_list)
  RecyclerView locationListRecyclerView;
  GooglePlaceAutoCompleteViewModel viewModel;


  public SearchResultLocationsListFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view=inflater.inflate(R.layout.fragment_search_result_locations_list, container, false);
    ButterKnife.bind(this, view);
    //viewModel = ViewModelProviders.of(getActivity()).get(GooglePlaceAutoCompleteViewModel.class);
    return view;

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(GooglePlaceAutoCompleteViewModel.class);
    viewModel.getGooglePlaceAutoComletePrediction()
        .observe(getActivity(), new Observer<List<GooglePlaceAutoCompletePredictions>>() {
          @Override
          public void onChanged(
              @Nullable List<GooglePlaceAutoCompletePredictions> googlePlaceAutoCompletePredictions) {
            setDataforLocationsRecyclerView(googlePlaceAutoCompletePredictions);
          }
        });
  }

  private void setDataforLocationsRecyclerView(List<GooglePlaceAutoCompletePredictions> locationList ) {

    GooglePlaceAutocompleteRecyclerViewAdapter  adapter=
        new GooglePlaceAutocompleteRecyclerViewAdapter(locationList,getContext());
    locationListRecyclerView.setAdapter(adapter);

    Toast.makeText(getContext(), locationList.toString(), Toast.LENGTH_SHORT).show();
  }

}
