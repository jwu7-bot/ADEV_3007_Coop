package com.example.gson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserFragment extends Fragment {

    private TextView userTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userTextView = view.findViewById(R.id.userTextView);

        // Load JSON from assets and parse it
        String json = loadJSONFromAsset();

        if (json != null) {
            // Convert JSON to List of User objects using Gson
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>(){}.getType();
            List<User> userList = gson.fromJson(json, userListType);

            // Display the data
            StringBuilder stringBuilder = new StringBuilder();
            for (User user : userList) {
                stringBuilder.append("Name: ").append(user.getName()).append("\n")
                        .append("Age: ").append(user.getAge()).append("\n\n");
            }

            userTextView.setText(stringBuilder.toString());
        }

        return view;
    }

    // Method to load JSON from assets
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}