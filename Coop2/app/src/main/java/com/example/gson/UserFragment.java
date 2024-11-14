package com.example.gson;

import static android.content.ContentValues.TAG;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserFragment extends Fragment {

    private TextView userTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Get the TextView from the fragment layout
        userTextView = view.findViewById(R.id.userTextView);

        // Load JSON from assets and parse it
        String json = loadJSONFromAsset();

        // Gson
        Gson gson;

        // Check if JSON is not null
        if (json != null) {
            // Convert JSON to List of User objects using Gson (Deserialization)

            // Create a new Gson instance
            gson = new Gson();

            // Type Token to specify the type of the List<User> object
            Type userListType = new TypeToken<List<User>>(){}.getType();

            // Parse JSON to List of User objects using Gson
            List<User> userList = gson.fromJson(json, userListType);

            // Format the data and display it on the TextView
            StringBuilder stringBuilder = new StringBuilder();
            for (User user : userList) {
                stringBuilder.append("Name: ").append(user.getName()).append("\n")
                        .append("Age: ").append(user.getAge()).append("\n\n");
            }
            userTextView.setText(stringBuilder.toString());
        }

        // Convert new users to JSON (Serialization)

        // Create a new Gson instance with pretty printing enabled
        gson = new GsonBuilder().setPrettyPrinting().create();

        // Create a list of new User objects
        List<User> newUserList = new ArrayList<>();
        newUserList.add(new User("Samuel Lee", 28));
        newUserList.add(new User("Emily Clark", 22));
        newUserList.add(new User("James Brown", 34));

        // Convert the list of new Users to JSON
        String newUserListJson = gson.toJson(newUserList);

        writeJsonToFile(newUserListJson);

        return view;
    }

    // Method to load JSON from assets
    private String loadJSONFromAsset() {
        // Initialize a StringBuilder to store the JSON content
        StringBuilder stringBuilder = new StringBuilder();

        // Read JSON content from assets
        try (InputStream is = requireActivity().getAssets().open("data.json");

             // Create a BufferedReader to read the content line by line
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            // Read each line and append it to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            Log.e("TAG", "Error message", e);
            return null;
        }
        return stringBuilder.toString();
    }

    // Method to write JSON to a file in the external storage directory
    private void writeJsonToFile(String jsonContent) {
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        try {
            // Get the external storage directory
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            // Create a new file named "users.json" in the downloads directory
            File file = new File(path, "users.json");

            // Write the JSON content to the file
            fos = new FileOutputStream(file);
            writer = new OutputStreamWriter(fos);
            writer.write(jsonContent);
            writer.close();
            Log.d(TAG, "JSON written to external storage: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("TAG", "Error message", e);
        }
    }
}