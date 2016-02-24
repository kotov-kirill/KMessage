package com.example.kirill.kmessage.Activities.MessagesActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.Special.ApplicationThemeSetter;
import com.example.kirill.kmessage.Special.MenuReceiver;
import com.example.kirill.kmessage.Special.NavigationMenuReceiver;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ListView listView;
    private List<Message> messages;
    private MultiChoiceModeListenerImpl choiceModeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationThemeSetter.themeSetter(this);
        setContentView(R.layout.activity_messages);
        this.initComponents();
    }

    private void initComponents() {
        this.initToolBar();
        this.initNavigationView();
        this.initFloatingActionButton();
        this.initListView();
    }

    private void initToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNavigationView() {
        this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, this.toolbar,
                R.string.view_navigation_open, R.string.view_navigation_close);
        this.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        this.navigationView = (NavigationView) this.findViewById(R.id.navigation_view);
        ApplicationThemeSetter.styleNavigationHeaderView(navigationView);
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                NavigationMenuReceiver.menuReceiver(MessagesActivity.this, item);
                return true;
            }
        });
        this.navigationView.setCheckedItem(R.id.menu_navigation_messages);

        NavigationMenuView menuView = (NavigationMenuView) this.navigationView.getChildAt(0);
        if(menuView != null)
            menuView.setVerticalScrollBarEnabled(false);
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initListView() {
        this.messages = new LinkedList<Message>(){
            {
                GregorianCalendar calendar = new GregorianCalendar();
                this.add(new Message(getColor(), "Привет, как дела?", calendar.getTime(), "Надежда"));
                this.add(new Message(getColor(), "Чем занимаетесь?", calendar.getTime(), "Аркадий"));
                this.add(new Message(getColor(), "Приходите на выставку IT", calendar.getTime(), "IT-Technology"));
                this.add(new Message(getColor(), "Купить автомобиль", calendar.getTime(), "Александр"));
                this.add(new Message(getColor(), "Вы в этом уверены?", calendar.getTime(), "Сергей"));
                this.add(new Message(getColor(), "Чем занимаетесь в свободное время?", calendar.getTime(), "Петр"));
                this.add(new Message(getColor(), "Я уверен, все получится", calendar.getTime(), "Георгий"));
                this.add(new Message(getColor(), "Вы сами написали приложение для VK?", calendar.getTime(), "Наталья"));
                this.add(new Message(getColor(), "Спасибо за работу", calendar.getTime(), "Павел"));
                this.add(new Message(getColor(), "Приглашаем Вас на выставку", calendar.getTime(), "Евгений"));
            }
        };
        this.listView = (ListView) this.findViewById(R.id.list_view_messages);
        this.listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        this.listView.setAdapter(new ListViewAdapter(this, R.layout.layout_list_view_message, this.messages));

        this.choiceModeListener = new MultiChoiceModeListenerImpl(this, this.listView);
        this.listView.setMultiChoiceModeListener(this.choiceModeListener);
    }

    private int getRandomNumber(){
        return (int) (Math.random()*256);
    }
    private int getColor(){
        return Color.argb(80, getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_messages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuReceiver.menuReceiver(this, item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.choiceModeListener.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.choiceModeListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
