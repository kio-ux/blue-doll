package com.example.bluedoll.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bluedoll.R;
import com.example.bluedoll.adapters.ViewDollsAdapter;
import com.example.bluedoll.database.DollsHelper;


public class ViewDollsFragment extends Fragment {

    ListView dollsListView;
    DollsHelper db;
    TextView txtEmpty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_dolls, container, false);

        dollsListView = view.findViewById(R.id.dollsListView);
        txtEmpty = view.findViewById(R.id.txtEmpty);

        String userName = getArguments().getString("userName");
        String userRole = getArguments().getString("userRole");
        Toast.makeText(getActivity(),userRole,Toast.LENGTH_SHORT).show();

        db = new DollsHelper(getActivity().getApplicationContext());

        ViewDollsAdapter viewDollsAdapter = new ViewDollsAdapter(getContext(), R.layout.list_dolls,db.getDollsData());
        viewDollsAdapter.notifyDataSetChanged();

        if(db.getDollsData() == null){
            dollsListView.setEmptyView(txtEmpty);
        }else{
            dollsListView.setAdapter(viewDollsAdapter);
            txtEmpty.setText("");
        }


        dollsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                long getId = view.getId();

                Integer dollID = db.getDollsData().get(position).getId();
                String dollCreator = db.getDollsData().get(position).getDollCreator();

                if(getId == R.id.view_btn){
                    Intent intent = new Intent(getActivity(),DollDetailsActivity.class);
                    intent.putExtra("dollID",dollID);
                    startActivity(intent);
                }
                if(getId == R.id.edit_btn){
                    Intent intent = new Intent(getActivity(),InsertDollsActivity.class);
                    intent.putExtra("dollID",dollID);
                    startActivity(intent);
                }
                if(getId == R.id.delete_btn){
                    if(userRole.equals("Admin")||userName.equals(dollCreator)){
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        db.deleteDolls(dollID);
                        viewDollsAdapter.remove(viewDollsAdapter.getItem(position));
                    }
                    else{
                        Toast.makeText(getActivity(), "You are not eligible to remove this doll", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });










        return view;
    }
}