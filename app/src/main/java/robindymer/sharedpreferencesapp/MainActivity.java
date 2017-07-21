package robindymer.sharedpreferencesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "robindymer.sharedpreferencesapp.preferences";
    private static final String KEY_EDITTEXT = "KEY_EDITTEXT";
    private SharedPreferences mSharedPreferences; // Store data with key-value pairs
    private SharedPreferences.Editor mEditor;

    // Views with unique id's will have their instance state saved and restored automatically by android
    // Does not apply to a new instance of the app though
    @BindView(R.id.editText) EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // String name parameter = the name of our shared preferences file
        // Int mode parameter = int that represents the mode. Private - shared preferences file can only be accessed by our own application
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit(); // All SharedPreferences objects has an editor where we can save data

        String editTextString = mSharedPreferences.getString(KEY_EDITTEXT, ""); // "" - default value
        mEditText.setText(editTextString);

        // You can use the clear method on a SharedPreferences.edit() object, and use remove(KEY_NAME) to remove just on value
        // Then you need to use the apply() method to save your changes
    }

    @Override // Where to save any unsaved changes
    protected void onPause() {
        super.onPause();

        // Save string to the editor
        mEditor.putString(KEY_EDITTEXT, mEditText.getText().toString());
        // Save changes to our SharedPreferences object
        mEditor.apply();
    }
}
