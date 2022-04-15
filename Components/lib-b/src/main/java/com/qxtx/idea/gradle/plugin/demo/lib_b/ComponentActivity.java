package com.qxtx.idea.gradle.plugin.demo.lib_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qxtx.idea.gradle.plugin.demo.lib_b.databinding.ActivityComponenetBinding;

@Route(path = "/components/activity", extras = 125)
public class ComponentActivity extends AppCompatActivity {

    private ActivityComponenetBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityComponenetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}