package com.github.axzae.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.axzae.bottomsheet.pojo.Item;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class StatisticFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View rootView = View.inflate(getContext(), R.layout.sheet_main, null);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        if (viewPager != null && tabLayout != null) {
            initViewPager();
        }

        dialog.setContentView(rootView);
        mBehavior = BottomSheetBehavior.from((View) rootView.getParent());
        return dialog;
    }

    private void initViewPager() {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(10);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        //mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public class ServiceVideHolder extends RecyclerView.ViewHolder {
        protected ViewGroup mItemView;
        protected TextView mNameView;
        protected TextView mCodeView;

        public ServiceVideHolder(View v) {
            super(v);
            //rootView = v;
            mItemView = (ViewGroup) v.findViewById(R.id.item);
            mNameView = (TextView) v.findViewById(R.id.main_text);
            mCodeView = (TextView) v.findViewById(R.id.sub_text);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView mMainText;
        protected TextView mSubText;

        public ItemViewHolder(View v) {
            super(v);
            mMainText = (TextView) v.findViewById(R.id.main_text);
            mSubText = (TextView) v.findViewById(R.id.sub_text);
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private List<Item> items;

        public ItemAdapter(List<Item> items) {
            this.items = items;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder viewHolder, final int position) {
            final Item item = items.get(position);
            viewHolder.mMainText.setText(item.name);
            viewHolder.mSubText.setText(item.value);
            viewHolder.mMainText.setTextColor(ResourcesCompat.getColor(getResources(), position % 2 == 0 ? R.color.md_red_500 : R.color.md_blue_500, null));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            //CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.adapter, collection, false);

            //View rootView = View.inflate(getContext(), R.layout.adapter, null);
            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            ArrayList<Item> items = new ArrayList<>();

            if (position == 0) {
                items.add(new Item("Coffee 1", "1"));
                items.add(new Item("Coffee 2", "2"));
                items.add(new Item("Coffee 3", "3"));
                items.add(new Item("Coffee 4", "4"));
                items.add(new Item("Coffee 5", "5"));
                items.add(new Item("Coffee 6", "6"));
                items.add(new Item("Coffee 7", "7"));
                items.add(new Item("Coffee 8", "8"));
                items.add(new Item("Coffee 9", "9"));
                items.add(new Item("Coffee 10", "10"));
            } else {
                items.add(new Item("Milk 1", "1"));
                items.add(new Item("Milk 2", "2"));
                items.add(new Item("Milk 3", "3"));
                items.add(new Item("Milk 4", "4"));
                items.add(new Item("Milk 5", "5"));
                items.add(new Item("Milk 6", "6"));
                items.add(new Item("Milk 7", "7"));
                items.add(new Item("Milk 8", "8"));
                items.add(new Item("Milk 9", "9"));
                items.add(new Item("Milk 10", "10"));
            }

            ItemAdapter mAdapter = new ItemAdapter(items);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setAdapter(mAdapter);
            Paint paint = new Paint();
            paint.setStrokeWidth(1);
            paint.setColor(ResourcesCompat.getColor(getResources(), R.color.md_grey_500, null));
            paint.setAntiAlias(true);
            paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).showLastDivider().paint(paint).build()); //.marginResId(R.dimen.leftmargin, R.dimen.rightmargin)

            collection.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
            return position == 0 ? "Coffee" : "Milk";
        }

    }
}
