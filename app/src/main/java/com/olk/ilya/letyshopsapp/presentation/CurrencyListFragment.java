package com.olk.ilya.letyshopsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.olk.ilya.letyshopsapp.R;
import com.olk.ilya.letyshopsapp.data.Alarm;
import com.olk.ilya.letyshopsapp.data.dagger.App;
import com.olk.ilya.letyshopsapp.domain.Currency;
import com.olk.ilya.letyshopsapp.domain.CurrencyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class CurrencyListFragment extends Fragment {
    @Inject
    Alarm mAlarm;

    private RecyclerView mRecyclerView;
    private CurrencyAdapter mCurrencyAdapter;
    private Button mButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.currency_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        App.getComponent().inject(this);
        mAlarm.setAlarm(view.getContext());
        mButton = (Button) view.findViewById(R.id.open_button);
        openHistory();
        return view;
    }

    private void updateUI() {
        CurrencyMap currencyMap  = CurrencyMap.getInstance();
        Map<String, Currency> map = currencyMap.getCurrencyMap();
        List<Currency> list = new ArrayList<Currency>(map.values());
        mCurrencyAdapter = new CurrencyAdapter(list);
        mRecyclerView.setAdapter(mCurrencyAdapter);
    }

    private void openHistory(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoadFileActivity.class);
                startActivity(intent);
            }
        });
    }



    private class CurrencyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitle;
        public TextView mBuy;
        public TextView mSale;
        private Currency mCurrency;

        public CurrencyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.list_item_title);
            mBuy  = (TextView) itemView.findViewById(R.id.list_item_buy);
            mSale = (TextView) itemView.findViewById(R.id.list_item_sale);
        }

        public void bindCurrency(Currency currency){
            mCurrency = currency;
            mTitle.setText(mCurrency.getCcy());
            mBuy.setText("Buy " + mCurrency.getBuy() + " UAH");
            mSale.setText(" Sale " + mCurrency.getSale()+ " UAH");
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    mCurrency.getCcy() + "\n" + "Buy " + mCurrency.getBuy() + "\n"
                    + "Sale " + mCurrency.getSale(), Toast.LENGTH_SHORT)
                    .show();

        }
    }

    private class CurrencyAdapter extends RecyclerView.Adapter<CurrencyHolder>{

        private List<Currency> mCurrencyList;

        public CurrencyAdapter(List<Currency> currencyList) {
            mCurrencyList = currencyList;
        }

        @Override
        public CurrencyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_currency, parent, false);
            return new CurrencyHolder(view);
        }

        @Override
        public void onBindViewHolder(CurrencyHolder holder, int position) {
            Currency currency = mCurrencyList.get(position);
            holder.bindCurrency(currency);
        }

        @Override
        public int getItemCount() {
            return mCurrencyList.size();
        }
    }
}
