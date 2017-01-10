package com.example.beomseok.greekday.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.adapter.OrderBaseRecyclerAdapter;
import com.example.beomseok.greekday.model.Yogurt;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.util.NetWorkListener;
import com.example.beomseok.greekday.util.NetworkTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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


    public static final int REQUEST_CODE=3312;
    RecyclerView recyclerView;
    ArrayList<Yogurt> yogurts = new ArrayList<Yogurt>();
    OrderBaseRecyclerAdapter adapter;
    String type;

    private OnFragmentInteractionListener mListener;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */

    public static OrderFragment newInstance(String type) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString("type",type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString("type");
            NetworkTask networkTask = new NetworkTask(new NetWorkListener() {
                @Override
                public void postSucceed(Object object) {

                    Set<Yogurt> set= ((Map)object).entrySet();
                    Iterator<Yogurt> it = set.iterator();
                    while(it.hasNext()){
                     yogurts.add(it.next());
                    }
                }
            });
            networkTask.execute(type);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);


        adapter = new OrderBaseRecyclerAdapter(yogurts,this);
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
