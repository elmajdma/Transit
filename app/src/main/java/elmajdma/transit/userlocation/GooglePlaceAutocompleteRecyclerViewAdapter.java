package elmajdma.transit.userlocation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import elmajdma.transit.R;
import elmajdma.transit.model.GooglePlaceAutoCompletePredictions;
import java.util.ArrayList;
import java.util.List;

public class GooglePlaceAutocompleteRecyclerViewAdapter
extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<GooglePlaceAutoCompletePredictions>
      googlePlaceAutoCompletePredictionsList = new ArrayList<>();
  private Context context;
  private  LocationSelectAdapterOnClickHandler mClickHandler;

  /**
   * The interface that receives onClick messages.
   */
  public interface LocationSelectAdapterOnClickHandler {
    void onLocationSelectClick(int position, View v);

  }

  public GooglePlaceAutocompleteRecyclerViewAdapter
      (List<GooglePlaceAutoCompletePredictions>
          googlePlaceAutoCompletePredictionsList, Context context,
          LocationSelectAdapterOnClickHandler mClickHandler) {
    this.googlePlaceAutoCompletePredictionsList = googlePlaceAutoCompletePredictionsList;
    this.context = context;
    this.mClickHandler=mClickHandler;

  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View googlePlaceAutoCompleteView = inflater
        .inflate(R.layout.google_place_auto_complete_item, parent, false);
    return new GooglePlaceAutoCompleteViewHolder(googlePlaceAutoCompleteView,mClickHandler);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    GooglePlaceAutoCompleteViewHolder googlePlaceAutoCompleteViewHolder = (GooglePlaceAutoCompleteViewHolder) holder;
    configuregooglePlaceAutoCompleteViewHolder(googlePlaceAutoCompleteViewHolder, position);
  }

  @Override
  public int getItemCount() {
    // condition ? passed : failed
    return (googlePlaceAutoCompletePredictionsList != null) ? googlePlaceAutoCompletePredictionsList
        .size() : 0;
  }

  private void configuregooglePlaceAutoCompleteViewHolder
      (GooglePlaceAutoCompleteViewHolder googlePlaceAutoCompleteViewHolder,
          int position) {
    GooglePlaceAutoCompletePredictions googlePlaceAutoCompletePredictions
        = googlePlaceAutoCompletePredictionsList.get(position);
    if (googlePlaceAutoCompletePredictions != null) {
      if (googlePlaceAutoCompletePredictions.getDescription().contains(",")) {
        // Split it.
        String[] parts = googlePlaceAutoCompletePredictions.getDescription().split(",");
        String mainAdressPart = parts[0];
        googlePlaceAutoCompleteViewHolder.tvGoogleAutoCompleteAdressMainPart
            .setText(mainAdressPart);
        String secondryAdressPart = parts[1];
        googlePlaceAutoCompleteViewHolder.tvGoogleAutoCompleteAdressSecondaryPart
            .setText(secondryAdressPart);
      }else if(googlePlaceAutoCompletePredictions.getDescription().contains("،")){
        String[] parts = googlePlaceAutoCompletePredictions.getDescription().split("،");
        String mainAdressPart = parts[0];
        googlePlaceAutoCompleteViewHolder.tvGoogleAutoCompleteAdressMainPart
            .setText(mainAdressPart);
        String secondryAdressPart = parts[1];
        googlePlaceAutoCompleteViewHolder.tvGoogleAutoCompleteAdressSecondaryPart
            .setText(secondryAdressPart);

      } else {
        googlePlaceAutoCompleteViewHolder.tvGoogleAutoCompleteAdressMainPart.setText(googlePlaceAutoCompletePredictions.getDescription());
        //throw new IllegalArgumentException("String " + googlePlaceAutoCompletePredictions.getDescription()+ " does not contain ,");
      }

    }
  }

  public class GooglePlaceAutoCompleteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private LocationSelectAdapterOnClickHandler mListener;
    @BindView(R.id.tv_location_adress_main_part)
    TextView tvGoogleAutoCompleteAdressMainPart;
    @BindView(R.id.tv_location_adress_secondary_part)
    TextView tvGoogleAutoCompleteAdressSecondaryPart;

    public GooglePlaceAutoCompleteViewHolder(View itemView, LocationSelectAdapterOnClickHandler listener) {
      super(itemView);
      mListener = listener;
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      mListener.onLocationSelectClick(getAdapterPosition(), v);
    }
  }
}
