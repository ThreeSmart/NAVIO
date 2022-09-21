package com.example.navio.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.auth0.android.jwt.JWT;
import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nameTextView = root.findViewById(R.id.name);

        final String jwtToken = LocalStorage.getInstance()
                .setContext(root.getContext())
                .setKey(getString(R.string.local_authentication))
                .getString(getString(R.string.local_jwt));

        if (jwtToken != null) {
            final JWT jwt = new JWT(jwtToken);
            final String name = jwt.getClaim("name").asString();
            nameTextView.setText(name);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}