package com.harkka.livegreen.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.harkka.livegreen.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Button login_button;
    private CardView card;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        login_button = root.findViewById(R.id.buttonProfileViewLogout);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle_profileview_loginbutton();
            }
        });
        card = root.findViewById(R.id.profileCardView);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void handle_profileview_loginbutton(){
        // TODO toggle current login position
        handle_profileview_login_state(false);
    }

    // Change profileview according to loginstate
    public void handle_profileview_login_state(boolean is_logged){
        //login_button = v.findViewById(R.id.buttonProfileViewLogout);
        //card = v.findViewById(R.id.profileCardView);

        if(is_logged){
            login_button.setText(getText(R.string.log_out));
            login_button.setBackgroundColor(login_button.getContext().getResources().getColor(R.color.red));

            card.setVisibility(View.VISIBLE);

            //TODO change rank, profile picture and profile name with stockvalues

        } else {
            login_button.setText(getText(R.string.log_in));
            login_button.setBackgroundColor(login_button.getContext().getResources().getColor(R.color.green_dark));

            card.setVisibility(View.GONE);

            //TODO change rank, profile picture and profile name with stockvalues
        }
    }
}