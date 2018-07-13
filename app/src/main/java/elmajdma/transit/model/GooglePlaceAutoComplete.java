package elmajdma.transit.model;

import android.gesture.Prediction;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GooglePlaceAutoComplete {
  @SerializedName("predictions")
  @Expose
  private List<GooglePlaceAutoCompletePredictions> predictions = null;
  @SerializedName("status")
  @Expose
  private String status;

  public GooglePlaceAutoComplete(
      List<GooglePlaceAutoCompletePredictions> predictions, String status) {
    this.predictions = predictions;
    this.status = status;
  }

  public List<GooglePlaceAutoCompletePredictions> getPredictions() {
    return predictions;
  }

  public void setPredictions(List<GooglePlaceAutoCompletePredictions> predictions) {
    this.predictions = predictions;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "GooglePlaceAutoComplete{" +
        "predictions=" + predictions +
        ", status='" + status + '\'' +
        '}';
  }
}
