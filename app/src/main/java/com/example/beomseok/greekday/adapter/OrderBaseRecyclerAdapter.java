package com.example.beomseok.greekday.adapter;


import android.animation.ValueAnimator;

import android.app.ActivityOptions;
import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

/**
 * Created by beomseok on 2016. 12. 7..
 */

public class OrderBaseRecyclerAdapter extends RecyclerView.Adapter<OrderBaseRecyclerAdapter.ViewHolder> {


    private ArrayList<BaseItem> baseItems;

    private static Fragment context;
    private int dummyImg[] = {R.mipmap.sample_1, R.mipmap.sample_2, R.mipmap.sample_3, R.mipmap.sample_4, R.mipmap.sample_5
            , R.mipmap.sample_6, R.mipmap.sample_7, R.mipmap.sample_8};

    private ArrayList<Topping> selectedToppings;
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
        //bHoder.ivMain.setImageResource((dummyImg[position%8]));

        bHoder.tvTitle.setText(baseItems.get(position).title);
        bHoder.ivMain.setImageResource(dummyImg[position % 8]);
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
                    }
                    opendVh = (ViewHolder) view.getTag();
                    //set opened value
                    selectedToppings.clear();
                    openedPositon = position;
                    opendVh.tvAddToping.setText("토핑 추가");
                    //set open animation
                    bHoder.valueAnimator = ValueAnimator.ofInt(0, bHoder.originalHeight); // These values in this method can be changed to expand however much you like

                }
                //start collapse
                else {
                    openedPositon = DEFAULT_POSTION;
                    opendVh = null;
                    bHoder.tvPrice.setText(Integer.toString(baseItems.get(position).price));
                    bHoder.valueAnimator = ValueAnimator.ofInt(bHoder.originalHeight, 0);
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
        }); ;
        bHoder.ivMain.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getContext(), BaseDetailActivity.class);
                ActivityOptions transitionOptions = ActivityOptions.makeSceneTransitionAnimation(context.getActivity(),view,"asp");
                context.startActivity(i,transitionOptions.toBundle());

            }
        });
        bHoder.tvAddToping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivityForResult(new Intent(context.getContext(), OrderToppingActivity.class), OrderActivity.REQUEST_CODE);
                context.getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

            }
        });
        bHoder.tvHonney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bHoder.tvHonney.setChecked(!bHoder.tvHonney.isChecked());
            }
        });
        bHoder.tvAddYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bHoder.tvAddYogurt.setChecked(!bHoder.tvAddYogurt.isChecked());
            }
        });

        bHoder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Yogurt yogurt = makeYougurt(position,bHoder);
                MainActivity.CART.addCart(yogurt);
                context.startActivity(new Intent(context.getContext(), CartActivity.class));
            }
        });
        bHoder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Yogurt yogurt = makeYougurt(position,bHoder);
                MainActivity.CART.addCart(yogurt);
                Snackbar.make(view,"\'"+yogurt.title.toString()+"\' 장바구니에 담겼습니다",1500).setAction("Action", null).show();
            }
        });
    }
    private Yogurt makeYougurt(int position,ViewHolder vh){
        char size = baseItems.get(position).size;
        String title = (String) vh.tvTitle.getText();
        boolean honey = vh.tvHonney.isChecked();
        boolean isSizeUp = vh.tvAddYogurt.isChecked();
        int price = baseItems.get(position).price;
        return new Yogurt(title, size, price, honey,isSizeUp ,selectedToppings);
    }

    public void setSelectedToppongs(ArrayList<Topping> toppings) {
        TextView textView = opendVh.tvAddToping;
        StringBuilder result = new StringBuilder();
        int totalPrice=0;
        if (toppings == null ||toppings.size()<1) {
            result.append("토핑추가");
            selectedToppings.clear();
        } else {
            selectedToppings = toppings;
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