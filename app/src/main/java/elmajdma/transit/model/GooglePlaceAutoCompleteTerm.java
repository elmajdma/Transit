package elmajdma.transit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GooglePlaceAutoCompleteTerm {
  @SerializedName("offset")
  @Expose
  private Integer offset;
  @SerializedName("value")
  @Expose
  private String value;

  public GooglePlaceAutoCompleteTerm(Integer offset, String value) {
    this.offset = offset;
    this.value = value;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "GooglePlaceAutoCompleteTerm{" +
        "offset=" + offset +
        ", value='" + value + '\'' +
        '}';
  }
}
