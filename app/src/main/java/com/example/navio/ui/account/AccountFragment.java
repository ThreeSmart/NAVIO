package com.example.navio.ui.account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.auth0.android.jwt.JWT;
import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.backend.service.AuthenticationService;
import com.example.navio.databinding.FragmentAccountBinding;
import com.example.navio.ui.ScreenSizeBalancer;
import com.example.navio.ui.account.pages.AboutActivity;
import com.example.navio.ui.account.pages.AccountActivity;
import com.example.navio.ui.account.pages.ProfileActivity;
import com.example.navio.ui.account.pages.SettingsActivity;
import com.example.navio.ui.splash.SplashActivity;

import java.util.Objects;
import java.util.Set;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        final ImageButton logoutButton = root.findViewById(R.id.logout_button);
        final TextView nameAndSurname = root.findViewById(R.id.name_surname);
        final ConstraintLayout profileItemLayout = root.findViewById(R.id.profile_item_layout);
        final ConstraintLayout accountItemLayout = root.findViewById(R.id.account_item_layout);
        final ConstraintLayout settingsItemLayout = root.findViewById(R.id.settings_item_layout);
        final ConstraintLayout aboutItemLayout = root.findViewById(R.id.about_item_layout);

        final ScreenSizeBalancer screenSizeBalancer = new ScreenSizeBalancer(getResources());
        nameAndSurname.setMaxWidth(screenSizeBalancer.getWidthFor(200));

        nameAndSurname.setText(makeNameAndSurnameLine(AuthenticationService.getAuthenticatedUser().getName(), AuthenticationService.getAuthenticatedUser().getSurname()));


        profileItemLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(root.getContext(), ProfileActivity.class);
            startActivity(intent);
        });

        accountItemLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(root.getContext(), AccountActivity.class);
            startActivity(intent);
        });

        settingsItemLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(root.getContext(), SettingsActivity.class);
            startActivity(intent);
        });

        aboutItemLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(root.getContext(), AboutActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            LocalStorage.getInstance()
                    .setContext(root.getContext())
                    .setKey(getString(R.string.local_authentication))
                    .removeString(getString(R.string.local_jwt));

            final Intent intent = new Intent(root.getContext(), SplashActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return root;
    }

    private String makeNameAndSurnameLine(final String name, final String surname) {
        return String.format("%s %s", name, surname);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
