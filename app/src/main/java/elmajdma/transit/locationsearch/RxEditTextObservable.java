package elmajdma.transit.locationsearch;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxEditTextObservable {

  private RxEditTextObservable() {
    // no instance
  }

  public static Observable<String> fromView(EditText editText) {

    final PublishSubject<String> subject = PublishSubject.create();
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        subject.onNext(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    return subject;
  }
}


