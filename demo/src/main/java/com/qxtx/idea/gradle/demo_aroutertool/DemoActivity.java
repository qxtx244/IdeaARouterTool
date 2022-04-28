package com.qxtx.idea.gradle.demo_aroutertool;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qxtx.idea.gradle.demo_aroutertool.databinding.ActivityDemoBinding;

@Route(group = "demo", path = "/demo/activity", extras = 124)
public class DemoActivity extends AppCompatActivity {

    private ActivityDemoBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.icon.setOnLongClickListener(v -> {
            Toast.makeText(this, "Navigation to lib ACT with something extras.", Toast.LENGTH_SHORT).show();
            ARouter.getInstance()
                    .build("/lib/activity")
                    .withString("msg", "AROUTER-TOOL'S TEST")
                    .navigation();

            return true;
        });
    }
}