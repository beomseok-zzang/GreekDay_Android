package com.example.beomseok.greekday.adapter;


import android.animation.ValueAnimator;
import android.app.Activity;

import android.app.ActivityOptions;
import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.Space;
import android.widget.TextView;

import com.example.beomseok.greekday.BaseDetailActivity;
import com.example.beomseok.greekday.OrderBaseActivity;
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

    private static Activity context;
    private int dummyImg[] = {R.mipmap.sample_1, R.mipmap.sample_2, R.mipmap.sample_3, R.mipmap.sample_4, R.mipmap.sample_5
            , R.mipmap.sample_6, R.mipmap.sample_7, R.mipmap.sample_8};

    private static ArrayList<Topping> selectedToppings;
    public static final int DEFAULT_POSTION = -1;
    public static int openedPositon = DEFAULT_POSTION;
    public static ViewHolder opendVh;


    public static final String[] SIZES = {"Small", "Medium", "Large"};

    public static final int MAX_BUTTONS = 3;


    public OrderBaseRecyclerAdapter(ArrayList<BaseItem> baseItems, Activity context) {
        if (baseItems == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }
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
        public final ViewGroup buttonsContainer;
        public final CheckedTextView tvHonney;
        public final TextView tvAddToping;
        public final Button btnBuy;
        public final Button btnAddCart;


        int position;
        int toppingPrice=0;
        int yogurtPrice;

        public ValueAnimator valueAnimator;
        public int originalHeight;
        public float originalTextSize;
        private Button activeButton = null;
        private int activeValue = -1;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setTag(this);
            tvHonney = (CheckedTextView) itemView.findViewById(R.id.tv_base_honney);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_base_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_base_price);
            ivMain = (ImageView) itemView.findViewById(R.id.iv_base_main);
            btnAddCart = (Button) itemView.findViewById(R.id.btn_base_addcart);
            btnBuy = (Button) itemView.findViewById(R.id.btn_base_buy);

            detailLayout = (LinearLayout) itemView.findViewById(R.id.relative_base);
            tvAddToping = (TextView) itemView.findViewById(R.id.tv_add_topping);

            //button selector
            buttonsContainer = (ViewGroup) itemView.findViewById(R.id.buttonsContainer);
            int buttonsSpacing = (int) itemView.getResources().getDimension(R.dimen.activity_horizontal_margin);
            int buttonSize = (int) itemView.getResources().getDimension(R.dimen.button_size);
            for (int i = 0; i < MAX_BUTTONS; i++) {
                final int value = i + 1;
                final Button button = (Button) LayoutInflater.
                        from(itemView.getContext()).inflate(R.layout.circular_button_layout, buttonsContainer, false);
                button.setText(SIZES[i]);
                buttonsContainer.addView(button);

                //Add margin between buttons manually
                if (i != MAX_BUTTONS - 1) {
                    buttonsContainer.addView(new Space(itemView.getContext()), new ViewGroup.LayoutParams(buttonsSpacing, buttonSize));
                }
            }
            detailLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            originalHeight = detailLayout.getMeasuredHeight();
            originalTextSize = tvTitle.getTextScaleX();
            detailLayout.getLayoutParams().height = 0;


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
        bHoder.tvPrice.setText(baseItems.get(position).priceSmall + " 원");
        bHoder.yogurtPrice = baseItems.get(position).priceSmall;
        selectButton((Button) bHoder.buttonsContainer.getChildAt(0),bHoder,BaseItem.SIZE_MEDIUM);
        bHoder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
                        opendVh.tvPrice.setText(Integer.toString(baseItems.get(position).priceSmall));
                        opendVh.detailLayout.requestLayout();
                    }
                    opendVh = (ViewHolder) view.getTag();
                    //set opened value
                    selectedToppings = null;
                    openedPositon = position;
                    opendVh.tvAddToping.setText("토핑 추가");
                    //set open animation
                    bHoder.valueAnimator = ValueAnimator.ofInt(0, bHoder.originalHeight); // These values in this method can be changed to expand however much you like

                }
                //start collapse
                else {
                    openedPositon = DEFAULT_POSTION;
                    opendVh = null;
                    bHoder.tvPrice.setText(Integer.toString(baseItems.get(position).priceSmall));
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
                Intent i = new Intent(context, BaseDetailActivity.class);
                ActivityOptions transitionOptions = ActivityOptions.makeSceneTransitionAnimation(context,view,"asp");
                context.startActivity(i,transitionOptions.toBundle());

            }
        });
        bHoder.tvAddToping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivityForResult(new Intent(context, OrderToppingActivity.class), OrderBaseActivity.REQUEST_CODE);
                context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });
        bHoder.tvHonney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bHoder.tvHonney.setChecked(!bHoder.tvHonney.isChecked());
            }
        });

        bHoder.buttonsContainer.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton((Button)view,bHoder,BaseItem.SIZE_SMALL);
                bHoder.yogurtPrice = baseItems.get(position).priceSmall;
                bHoder.tvPrice.setText(Integer.toString(bHoder.yogurtPrice+bHoder.toppingPrice)+" 원");

            }
        });
        bHoder.buttonsContainer.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton((Button)view,bHoder,BaseItem.SIZE_MEDIUM);
                bHoder.yogurtPrice = baseItems.get(position).priceMedium;
                bHoder.tvPrice.setText(Integer.toString(bHoder.yogurtPrice+bHoder.toppingPrice)+" 원");
            }
        });
        bHoder.buttonsContainer.getChildAt(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton((Button)view,bHoder,BaseItem.SIZE_LARGE);
                bHoder.yogurtPrice = baseItems.get(position).priceLarge;
                bHoder.tvPrice.setText(Integer.toString(bHoder.yogurtPrice+bHoder.toppingPrice)+" 원");
            }
        });
        bHoder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int size = bHoder.activeValue;
                String title = (String) bHoder.tvTitle.getText();
                boolean honey = bHoder.tvHonney.isChecked();
                int price = baseItems.get(position).getPrice(bHoder.activeValue);
                Yogurt yogurt = new Yogurt(title, size, price, honey, selectedToppings);

            }
        });
    }
    private void selectButton(Button button,ViewHolder viewHolder,int value ) {
        if (viewHolder.activeButton != null) {
            viewHolder.activeButton.setSelected(false);
        }
        viewHolder.activeButton = button;
        button.setSelected(true);
        viewHolder.activeValue = value;

    }

    public void setSelectedToppongs(ArrayList<Topping> toppings) {
        TextView textView = opendVh.tvAddToping;
        StringBuilder result = new StringBuilder();
        int totalPrice=0;
        if (toppings == null ||toppings.size()<1) {
            result.append("토핑추가");
            selectedToppings = null;
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

    @Override
    public int getItemCount() {
        return baseItems.size();
    }
}