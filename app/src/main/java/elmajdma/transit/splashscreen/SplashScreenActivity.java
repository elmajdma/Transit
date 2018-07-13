package elmajdma.transit.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaeger.library.StatusBarUtil;
import elmajdma.transit.R;
import elmajdma.transit.userlocation.UserCurrentLocation;
import java.util.Arrays;

public class SplashScreenActivity extends AppCompatActivity {
  private FirebaseDatabase mFirebaseDatabase;
  private DatabaseReference mDatabaseReference;
  private FirebaseAuth mFirebaseAuth;
  private FirebaseAuth.AuthStateListener mAuthStateListener;
  private static final String EXTRA_IDP_RESPONSE= "EXTRA_IDP_RESPONSE";

  private static final int RC_SIGN_IN=123;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    StatusBarUtil.setTransparent(SplashScreenActivity.this);
    //Initialiize firbase components

    mFirebaseDatabase=FirebaseDatabase.getInstance();
    mFirebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //FirebaseAuth auth = FirebaseAuth.getInstance();
    if (user != null) {
      // already signed in
      Intent in = new Intent(this, UserCurrentLocation.class);
      in.putExtra(EXTRA_IDP_RESPONSE, user);
      startActivity(in);
      finish();
    } else {
      startActivityForResult(
          AuthUI.getInstance()
              .createSignInIntentBuilder()
              .setIsSmartLockEnabled(false)
              .setAvailableProviders(Arrays.asList(
                  new AuthUI.IdpConfig.GoogleBuilder().build(),
                  new AuthUI.IdpConfig.FacebookBuilder().build()))
              .build(),
          RC_SIGN_IN);
    }
  }
 /* @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
    if (requestCode == RC_SIGN_IN) {
      IdpResponse response = IdpResponse.fromResultIntent(data);

      // Successfully signed in
      if (resultCode == RESULT_OK) {
        Intent in = new Intent(this, MainScreen.class);
        in.putExtra(EXTRA_IDP_RESPONSE, response);
        startActivity(in);
        finish();

      } else {
        // Sign in failed
        if (response == null) {
          // User pressed back button
         // showSnackbar(R.string.sign_in_cancelled);
          return;
        }

        if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
         // showSnackbar(R.string.no_internet_connection);
          return;
        }
        //showSnackbar(R.string.unknown_error);
        //Log.e(TAG, "Sign-in error: ", response.getError());
      }
    }
  }*/
}
