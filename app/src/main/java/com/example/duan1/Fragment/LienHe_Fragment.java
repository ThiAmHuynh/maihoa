package com.example.duan1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duan1.R;


public class LienHe_Fragment extends Fragment {
    TextView txtHotLine, txtLinkFB, txtWebsite, txtGmail;

    public LienHe_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lien_he_, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtHotLine = view.findViewById(R.id.txtHotLine);
        txtLinkFB = view.findViewById(R.id.txtLinkFB);
        txtWebsite = view.findViewById(R.id.txtWebsite);
        txtGmail = view.findViewById(R.id.txtGmail);
        txtWebsite.setText("http://www.google.com");
        txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linkify.addLinks(txtWebsite, Linkify.WEB_URLS);
            }
        });

        txtHotLine.setText("0969157141");
        txtHotLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linkify.addLinks(txtHotLine, Linkify.PHONE_NUMBERS);
            }
        });


        txtLinkFB.setText("https://www.facebook.com/hoa.trinhthai.1/");
        txtLinkFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linkify.addLinks(txtLinkFB, Linkify.WEB_URLS);
            }
        });
        txtGmail.setText("hoatrinh14020@gmail.com");
        txtGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linkify.addLinks(txtGmail, Linkify.EMAIL_ADDRESSES);
            }
        });


    }
}