package com.example.beomseok.greekday.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.adapter.OrderBaseRecyclerAdapter;
import com.example.beomseok.greekday.model.BaseItem;
import com.example.beomseok.greekday.model.Topping;

import java.io.Serializable;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int REQUEST_CODE=3312;
    RecyclerView recyclerView;
    ArrayList<BaseItem> baseItems = new ArrayList<BaseItem>();
    OrderBaseRecyclerAdapter adapter;


    private OnFragmentInteractionListener mListener;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        baseItems.add(new BaseItem("1","플레인",3000,BaseItem.SIZE_SMALL));
        baseItems.add(new BaseItem("2","그래놀라 바나나",3000,BaseItem.SIZE_SMALL));
        baseItems.add(new BaseItem("3","그래놀라 블루베리",3000,BaseItem.SIZE_SMALL));
        baseItems.add(new BaseItem("4","그래놀라 딸기",3000,BaseItem.SIZE_MEDIUM));
        baseItems.add(new BaseItem("5","생과일믹스",3000,BaseItem.SIZE_MEDIUM));
        baseItems.add(new BaseItem("6","그래놀라 제철과일",3000,BaseItem.SIZE_LARGE));
        baseItems.add(new BaseItem("7","블루베리 호두",3000,BaseItem.SIZE_LARGE));
        baseItems.add(new BaseItem("8","무화과 청포도",3000,BaseItem.SIZE_LARGE));

        adapter = new OrderBaseRecyclerAdapter(baseItems,this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_base);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){

            Serializable list=data.getSerializableExtra("result");

            //경고 메세지를 없에주는 어노테이션
            @SuppressWarnings("unchecked")
            ArrayList<Topping> l = (ArrayList<Topping>)list;
            adapter.setSelectedToppings(l);
        }
    }
}
