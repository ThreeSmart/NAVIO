package com.example.navio.ui.map;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navio.R;
import com.example.navio.databinding.FragmentMapBinding;
import com.example.navio.ui.map.web_builder.WebBuilder;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        webView = root.findViewById(R.id.web_view);

        WebBuilder webBuilder = new WebBuilder();
        webBuilder.build(webView, "file:///android_asset/anel_indoor_map_web/index.html");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private class CallBack extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event){
            return false;
        }
    }

}
