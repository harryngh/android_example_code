package com.highstarapp.fragmentexample;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    public interface MessageSendListener {
        public void onMessage(String message);
    }
    EditText msgET;
    Button displayMsgBtn;
    MessageSendListener messageSendListener;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        msgET = view.findViewById(R.id.messageET);

        displayMsgBtn = view.findViewById(R.id.displayBtn);
        displayMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                View view = getActivity().getCurrentFocus();
                if(view==null)
                    view = new View(getActivity());
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                String message = msgET.getText().toString();
                messageSendListener.onMessage(message);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try{
            messageSendListener = (MessageSendListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must override onMessage method");
        }
    }
}
