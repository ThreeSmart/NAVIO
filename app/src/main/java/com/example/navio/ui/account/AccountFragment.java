package com.example.navio.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auth0.android.jwt.JWT;
import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.databinding.FragmentAccountBinding;
import com.example.navio.ui.splash.SplashActivity;

import java.util.Objects;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        final Button logoutButton = root.findViewById(R.id.logout_button);
        final TextView name = root.findViewById(R.id.name);
        final TextView surname = root.findViewById(R.id.surname);
        final TextView username = root.findViewById(R.id.username);
        final TextView departmentId = root.findViewById(R.id.department_id);
        final TextView email = root.findViewById(R.id.email);

        final String jwtToken = LocalStorage.getInstance()
                .setContext(root.getContext())
                .setKey(getString(R.string.local_authentication))
                .getString(getString(R.string.local_jwt));

        if (jwtToken != null) {
            final JWT jwt = new JWT(jwtToken);
            name.setText(jwt.getClaim("name").asString());
            surname.setText(jwt.getClaim("surname").asString());
            username.setText(jwt.getClaim("username").asString());
            departmentId.setText(String.format("Department ID:  %s", jwt.getClaim("department_id").asString()));
            email.setText(jwt.getClaim("email").asString());
        }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
