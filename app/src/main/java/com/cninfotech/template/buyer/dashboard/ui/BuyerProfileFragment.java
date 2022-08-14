package com.cninfotech.template.buyer.dashboard.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.CountryModel;
import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.ui.BuyerCartActivity;
import com.cninfotech.template.buyer.dashboard.LanguageAdapter;
import com.cninfotech.template.buyer.user.account.AccountActivity;
import com.cninfotech.template.buyer.user.device.DeviceActivity;
import com.cninfotech.template.buyer.user.password.ui.ChangePasswordActivity;
import com.cninfotech.template.startscreen.WelcomeActivity;

public class BuyerProfileFragment extends Fragment implements View.OnClickListener {
    LinearLayout ibCart;
    LinearLayout llPassword;
    LinearLayout llLogout;
    LinearLayout llDevices;
    LinearLayout llAccount;
    LinearLayout llLanguage;

    public static String defaultLanguage = "Nepal";

    public BuyerProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_profile, container, false);

        llLogout = view.findViewById(R.id.llLogout);
        llPassword = view.findViewById(R.id.linear_password);
        llDevices = view.findViewById(R.id.linear_devices);
        llAccount = view.findViewById(R.id.linear_account);
        llLanguage = view.findViewById(R.id.linear_language);


        llLogout.setOnClickListener(this);
        llPassword.setOnClickListener(this);
        llDevices.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llLanguage.setOnClickListener(this);

        ibCart = view.findViewById(R.id.linear_cart);
        ibCart.setOnClickListener(view12 -> startActivity(new Intent(getActivity(), BuyerCartActivity.class)));
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llLogout:
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
                requireActivity().finishAffinity();
                break;
            case R.id.linear_password:
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                break;
            case R.id.linear_account:
                startActivity(new Intent(getActivity(), AccountActivity.class));
                break;
            case R.id.linear_devices:
                startActivity(new Intent(getActivity(), DeviceActivity.class));
                break;
            case R.id.linear_language:
                showDialog();
                break;
        }
    }

    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_language_selector, null);
        dialogBuilder.setView(dialogView);

        CountryModel countryModel = new CountryModel();

        RecyclerView s = dialogView.findViewById(R.id.spinner_language);
        LanguageAdapter languageAdapter = new LanguageAdapter(getActivity(),countryModel.makeCountry());
        s.setAdapter(languageAdapter);
        s.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        AlertDialog alertDialog = dialogBuilder.create();

        Button btnConfirm =dialogView.findViewById(R.id.button_confirm);

        btnConfirm.setOnClickListener(view ->{
            alertDialog.dismiss();
        });

        alertDialog.show();

    }

}
