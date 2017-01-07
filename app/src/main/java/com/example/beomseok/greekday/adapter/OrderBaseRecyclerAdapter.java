package com.example.beomseok.greekday.adapter;


import android.animation.ValueAnimator;

import android.app.ActivityOptions;
import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.beomseok.greekday.BaseDetailActivity;
import com.example.beomseok.greekday.CartActivity;
import com.example.beomseok.greekday.MainActivity;
import com.example.beomseok.greekday.OrderActivity;
import com.example.beomseok.greekday.OrderToppingActivity;
import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.BaseItem;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.model.Yogurt;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by beomseok on 2016. 12. 7..
 */

public class OrderBaseRecyclerAdapter extends RecyclerView.Adapter<OrderBaseRecyclerAdapter.ViewHolder> {


    private ArrayList<BaseItem> baseItems;

    private static Fragment context;
    private int dummyImg[] = {R.mipmap.sample_base,R.mipmap.sample_banana,R.mipmap.sample_blueberry,R.mipmap.sample_mango
            ,R.mipmap.sample_peaneut,R.mipmap.sample_insam,R.mipmap.sameple_base2,R.mipmap.sample_base3};

    ArrayList<Topping> selectedToppings;
    public static final int DEFAULT_POSTION = -1;
    public static int openedPositon = DEFAULT_POSTION;
    public static ViewHolder opendVh;

    public OrderBaseRecyclerAdapter(ArrayList<BaseItem> baseItems, Fragment context) {
        if (baseItems == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }
        selectedToppings = new ArrayList<Topping>();
        this.context = context;
        this.baseItems = baseItems;

    }


    private class BaseOnclickListener implements View.OnClickListener{
        final int position;
        final OrderBaseRecyclerAdapter.ViewHolder viewHolder;
        BaseOnclickListener(final int position, final OrderBaseRecyclerAdapter.ViewHolder viewHolder){
            this.position= position;
            this.viewHolder = viewHolder;
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_base_main:
                    Intent intent = new Intent(context.getContext(), BaseDetailActivity.class);
                    intent.putExtra("base_object",baseItems.get(position));
                    ActivityOptions transitionOptions = ActivityOptions.makeSceneTransitionAnimation(context.getActivity(),view,"asp");
                    context.startActivity(intent,transitionOptions.toBundle());
                    break;

                case R.id.tv_add_topping:
                    context.startActivityForResult(new Intent(context.getContext(), OrderToppingActivity.class), OrderActivity.REQUEST_CODE);
                    context.getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                    break;

                case R.id.tv_base_honney:
                    CheckedTextView ctv1 = (CheckedTextView) view;
                    ctv1.setChecked(!ctv1.isChecked());
                    break;

                case R.id.tv_base_add_yogurt:
                    CheckedTextView ctv2 = (CheckedTextView) view;
                    ctv2.setChecked(!ctv2.isChecked());
                    break;
                case R.id.btn_base_buy:
                    Yogurt yogurt1 = makeYougurt(position,viewHolder);
                    MainActivity.CART.addYogurt(yogurt1);
                    context.startActivity(new Intent(context.getContext(), CartActivity.class));
                    break;
                case R.id.btn_base_addcart:
                    Yogurt yogurt2 = makeYougurt(position,viewHolder);
                    MainActivity.CART.addYogurt(yogurt2);
                    Snackbar.make(view,"\'"+yogurt2.title.toString()+"\' 장바구니에 담겼습니다",1500).setAction("Action", null).show();
                    break;

            }
        }
    }

    //


    /**
     * Interface for receiving click events from cells.
     */

    /**
     * Custom viewholder for our planet views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvTitle;
        public final TextView tvPrice;
        public final ImageView ivMain;
        public final LinearLayout detailLayout;

        public final CheckedTextView tvHonney;
        public final CheckedTextView tvAddYogurt;
        public final TextView tvAddToping;
        public final Button btnBuy;
        public final Button btnAddCart;
        public final ArrayList<Topping> toppings;
        int toppingPrice=0;
        int yogurtPrice;

        public ValueAnimator valueAnimator;
        public int originalHeight;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setTag(this);
            tvHonney = (CheckedTextView) itemView.findViewById(R.id.tv_base_honney);
            tvAddYogurt=(CheckedTextView) itemView.findViewById(R.id.tv_base_add_yogurt);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_base_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_base_price);
            ivMain = (ImageView) itemView.findViewById(R.id.iv_base_main);
            btnAddCart = (Button) itemView.findViewById(R.id.btn_base_addcart);
            btnBuy = (Button) itemView.findViewById(R.id.btn_base_buy);
            toppings = new ArrayList<Topping>();
            detailLayout = (LinearLayout) itemView.findViewById(R.id.relative_base);
            tvAddToping = (TextView) itemView.findViewById(R.id.tv_add_topping);
            detailLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            originalHeight = detailLayout.getMeasuredHeight();
            detailLayout.getLayoutParams().height=0;


        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recycler_base_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder bHoder = holder;

        bHoder.tvTitle.setText(baseItems.get(position).title);
        //bHoder.ivMain.setImageResource(dummyImg[position % 8]);
        Glide.with(context).load(dummyImg[position % 8]).into(bHoder.ivMain);
        bHoder.tvPrice.setText(baseItems.get(position).price + " 원");
        bHoder.yogurtPrice = baseItems.get(position).price;

        bHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start expand
                if (position != openedPositon) {
                    //close anothorLayout
                    if (openedPositon != DEFAULT_POSTION) {
                        opendVh.detailLayout.getLayoutParams().height = 0;
                        opendVh.tvPrice.setTextSize(14f);
                        opendVh.tvTitle.setTextSize(14f);
                        opendVh.tvPrice.setText(Integer.toString(baseItems.get(position).price));
                        opendVh.detailLayout.requestLayout();
                        opendVh.ivMain.setClickable(false);
                    }

                    bHoder.toppings.clear();
                    opendVh = (ViewHolder) view.getTag();
                    //set opened value
                    bHoder.ivMain.setClickable(true);
                    openedPositon = position;
                    opendVh.tvAddToping.setText("토핑 추가");
                    //set open animation
                    bHoder.valueAnimator = ValueAnimator.ofInt(0, bHoder.originalHeight); // These values in this method can be changed to expand however much you like

                }
                //start collapse
                else {

                    bHoder.ivMain.setClickable(false);
                    openedPositon = DEFAULT_POSTION;
                    opendVh = null;
                    bHoder.tvPrice.setText(Integer.toString(baseItems.get(position).price)+" 원");
                    bHoder.valueAnimator = ValueAnimator.ofInt(bHoder.originalHeight, 0);
                    bHoder.ivMain.setClickable(false);
                }
                bHoder.valueAnimator.setDuration(300);
                bHoder.valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                bHoder.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        bHoder.detailLayout.getLayoutParams().height = value.intValue();
                        bHoder.detailLayout.requestLayout();
                        bHoder.tvTitle.setTextSize(14 + value.intValue() / 100);
                        bHoder.tvPrice.setTextSize(14 + value.intValue() / 100);

                    }
                });

                bHoder.valueAnimator.start();

            }
        });
        BaseOnclickListener baseOnclickListener = new BaseOnclickListener(position,bHoder);
        bHoder.ivMain.setOnClickListener(baseOnclickListener);
        bHoder.ivMain.setClickable(false);
        bHoder.tvAddToping.setOnClickListener(baseOnclickListener);
        bHoder.tvHonney.setOnClickListener(baseOnclickListener);
        bHoder.tvAddYogurt.setOnClickListener(baseOnclickListener);
        bHoder.btnBuy.setOnClickListener(baseOnclickListener);
        bHoder.btnAddCart.setOnClickListener(baseOnclickListener);
    }
    private Yogurt makeYougurt(int position,ViewHolder vh){
        char size = baseItems.get(position).size;
        String title = (String) vh.tvTitle.getText();
        int price = baseItems.get(position).price;
        boolean honey = vh.tvHonney.isChecked();
        boolean isSizeUp = vh.tvAddYogurt.isChecked();
        Log.d("ddd",Integer.toString(vh.toppings.size()));
        for(int i=0;i<vh.toppings.size();i++)
            price+=vh.toppings.get(i).price;

        return new Yogurt(title, size, price, honey,isSizeUp ,(ArrayList<Topping>) vh.toppings.clone());
    }

    public void setSelectedToppings(ArrayList<Topping> toppings) {
        TextView textView = opendVh.tvAddToping;
        StringBuilder result = new StringBuilder();
        int totalPrice=0;
        opendVh.toppings.clear();
        if (toppings == null ||toppings.size()<1) {
            result.append("토핑추가");
            Log.d("dddy",Integer.toString(selectedToppings.size()));
        } else {
            opendVh.toppings.addAll(toppings);
            for (Topping tp : toppings) {
                result.append(tp.name + " ");
                totalPrice+=tp.price;
            }

        }
        opendVh.toppingPrice = totalPrice;
        opendVh.tvPrice.setText(Integer.toString(opendVh.toppingPrice+opendVh.yogurtPrice)+" 원");
        textView.setText(result);

    }
    public char sizeUp(char size){
        switch (size){
            case BaseItem.SIZE_SMALL:
                return BaseItem.SIZE_MEDIUM;
            case BaseItem.SIZE_MEDIUM:
                return BaseItem.SIZE_LARGE;
            default:
                return BaseItem.SIZE_LARGE;
        }
    }

    @Override
    public int getItemCount() {
        return baseItems.size();
    }
}