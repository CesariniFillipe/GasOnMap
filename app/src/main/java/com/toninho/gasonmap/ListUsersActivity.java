package com.toninho.gasonmap;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.toninho.gasonmap.database.DBController;
import com.toninho.gasonmap.database.DBCreator;

public class ListUsersActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        DBController dbController = new DBController(getBaseContext());
        String [] fields = {User.ID, User.EMAIL};
        Cursor cursor = dbController.loadData(User.TABLE, fields);

        fields = new String[] {User.ID, User.EMAIL};
        int[] idViews = new int[] {R.id._id, R.id.name};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_show_user, cursor, fields, idViews, 0);
        list = (ListView) findViewById(R.id.userList);
        list.setAdapter(adapter);
    }
}
