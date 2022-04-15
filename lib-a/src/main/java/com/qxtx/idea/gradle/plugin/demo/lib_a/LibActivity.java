package com.qxtx.idea.gradle.plugin.demo.lib_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qxtx.idea.gradle.plugin.demo.lib_a.databinding.ActivityLibBinding;

@Route(path = "/liba/activity")
public class LibActivity extends AppCompatActivity {

    @Autowired
    public String msg;

    private ActivityLibBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLibBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ARouter.getInstance().inject(this);

        binding.tvInfo.setText(getString(R.string.msg, msg));
    }
}