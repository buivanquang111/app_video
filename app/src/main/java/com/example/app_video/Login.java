package com.example.app_video;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.app_video.databinding.LoginFragmentBinding;

public class Login extends Fragment {

    LoginFragmentBinding binding;
    public static Login newInstance(){
        Bundle args=new Bundle();
        Login fragment=new Login();
        fragment.setArguments(args);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater,R.layout.login_fragment,container,false);

        binding.btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.etname.getText().toString();
                String pass=binding.etpass.getText().toString();

                Toast.makeText(getContext(),"bạn đã đang nhập thanh công",Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }
}
