package elmajdma.transit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GooglePlaceAutoCompleteMatchedSubstring {
  @SerializedName("length")
  @Expose
  private Integer length;
  @SerializedName("offset")
  @Expose
  private Integer offset;

  public GooglePlaceAutoCompleteMatchedSubstring(Integer length, Integer offset) {
    this.length = length;
    this.offset = offset;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  @Override
  public String toString() {
    return "GooglePlaceAutoCompleteMatchedSubstring{" +
        "length=" + length +
        ", offset=" + offset +
        '}';
  }
}
