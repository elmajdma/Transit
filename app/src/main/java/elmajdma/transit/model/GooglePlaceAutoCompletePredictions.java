package elmajdma.transit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GooglePlaceAutoCompletePredictions {
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("matched_substrings")
  @Expose
  private List<GooglePlaceAutoCompleteMatchedSubstring> matchedSubstrings = null;
  @SerializedName("terms")
  @Expose
  private List<GooglePlaceAutoCompleteTerm> terms = null;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("place_id")
  @Expose
  private String placeId;
  @SerializedName("reference")
  @Expose
  private String reference;
  @SerializedName("types")
  @Expose
  private List<String> types = null;

  public GooglePlaceAutoCompletePredictions(String description,
      List<GooglePlaceAutoCompleteMatchedSubstring> matchedSubstrings,
      List<GooglePlaceAutoCompleteTerm> terms, String id, String placeId, String reference,
      List<String> types) {
    this.description = description;
    this.matchedSubstrings = matchedSubstrings;
    this.terms = terms;
    this.id = id;
    this.placeId = placeId;
    this.reference = reference;
    this.types = types;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<GooglePlaceAutoCompleteMatchedSubstring> getMatchedSubstrings() {
    return matchedSubstrings;
  }

  public void setMatchedSubstrings(List<GooglePlaceAutoCompleteMatchedSubstring> matchedSubstrings) {
    this.matchedSubstrings = matchedSubstrings;
  }

  public List<GooglePlaceAutoCompleteTerm> getTerms() {
    return terms;
  }

  public void setTerms(List<GooglePlaceAutoCompleteTerm> terms) {
    this.terms = terms;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  @Override
  public String toString() {
    return "GooglePlaceAutoCompletePredictions{" +
        "description='" + description + '\'' +
        ", matchedSubstrings=" + matchedSubstrings +
        ", terms=" + terms +
        ", id='" + id + '\'' +
        ", placeId='" + placeId + '\'' +
        ", reference='" + reference + '\'' +
        ", types=" + types +
        '}';
  }
}
