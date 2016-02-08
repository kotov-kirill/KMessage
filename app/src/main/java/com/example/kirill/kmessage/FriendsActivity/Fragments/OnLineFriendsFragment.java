package com.example.kirill.kmessage.FriendsActivity.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kirill.kmessage.FriendsActivity.Fragments.ListView.Friend;
import com.example.kirill.kmessage.FriendsActivity.Fragments.ListView.ListViewAdapter;
import com.example.kirill.kmessage.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 05.02.2016.
 * @author Rakov Kirill
 */

public class OnLineFriendsFragment extends AbstractFragment{
    private View view;
    private ListView listView;
    private List<Friend> friends;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_friends_layout, container, false);
        this.initComponents();
        return this.view;
    }

    private void initComponents() {
        this.friends = new LinkedList<Friend>(){
            {
                this.add(new Friend(getColor(), "Надежда", true));
                this.add(new Friend(getColor(), "Аркадий", true));
                this.add(new Friend(getColor(), "Александр", true));
                this.add(new Friend(getColor(), "Петр", true));
                this.add(new Friend(getColor(), "Георгий", true));
                this.add(new Friend(getColor(), "Наталья", false));
                this.add(new Friend(getColor(), "Евгений", true));
            }
        };
        this.listView = (ListView) this.view.findViewById(R.id.list_view_friends);
        this.listView.setAdapter(new ListViewAdapter(this.getActivity(),
                R.layout.layout_list_view_friends, this.friends));
    }

    private int getRandomNumber(){
        return (int) (Math.random()*256);
    }
    private int getColor(){
        return Color.argb(80, getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    public static OnLineFriendsFragment getInstance(Context context){
        OnLineFriendsFragment fragment = new OnLineFriendsFragment();
        fragment.setTitle(context.getString(R.string.tab_item_on_line_friends));
        return fragment;
    }
}
