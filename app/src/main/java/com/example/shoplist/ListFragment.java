package com.example.shoplist;

import static androidx.core.content.ContextCompat.getDrawable;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private LinearLayout mainLn;
    private List<TextView> productList = new ArrayList<>();
    private List<CheckBox> cbList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mainLn = v.findViewById(R.id.mainLn);

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSharedData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String data) {
                LinearLayout.LayoutParams lnParamV = new LinearLayout.LayoutParams(-1, ViewGroup.LayoutParams.WRAP_CONTENT);
                lnParamV.setMargins(25,0,25,0);
                LinearLayout newLn = new LinearLayout(getActivity());
                newLn.setOrientation(LinearLayout.HORIZONTAL);
                newLn.setBackgroundColor(Color.WHITE);
                newLn.setBackground(getDrawable(getActivity(),R.drawable.black_outline));
                newLn.setLayoutParams(lnParamV);

                CheckBox ch = new CheckBox(getActivity());
                ch.setGravity(Gravity.CENTER);
                ch.setOnClickListener(ListFragment.this :: onClick);
                cbList.add(ch);
                newLn.addView(ch);


                TextView newText = new TextView(getActivity());
                newText.setText(data);
                newText.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams tvParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT/2, ViewGroup.LayoutParams.WRAP_CONTENT);
                newText.setTextSize(20);
                newText.setLayoutParams(tvParam);
                productList.add(newText);
                newLn.addView(newText);

                mainLn.addView(newLn);

            }
        });

        return  v;
    }


    @Override
    public void onClick(View v) {
        for (int i = 0; i < cbList.size(); i++) {
            if(cbList.get(i).isChecked() == true){
                productList.get(i).setTextColor(Color.BLUE);
            }
            else{
                productList.get(i).setTextColor(Color.BLACK);
            }
        }
    }
}