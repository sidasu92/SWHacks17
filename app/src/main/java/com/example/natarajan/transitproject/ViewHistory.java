package com.example.natarajan.transitproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        Intent intent = getIntent();
        final String origin_lat = intent.getStringExtra("origin_lat");
        final String origin_long = intent.getStringExtra("origin_long");

        try {

            DatabaseHelper dbHelper = DatabaseHelper.getInstance(getBaseContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.natarajan.assaignment1/databases/patients_natarajan.db", null);
            Cursor cur = db.rawQuery("select * from search_history", null);
            TableLayout history_tbl = (TableLayout) findViewById(R.id.history_table);
            TableRow row= new TableRow(this);
            TextView t1 = new TextView(this);
            t1.setText("Time stamp");
            TextView t2 = new TextView(this);
            t2.setText("Destination");
            TextView t3 = new TextView(this);
            t3.setText("LatLong");
            row.addView(t2);
            history_tbl.addView(row,0);

            cur.moveToFirst();
            int i=1;
            while (cur.isAfterLast() != true) {
                String time = (String) cur.getString(0);
                final String desti = (String) cur.getString(1);
                final String lat_long = (String) cur.getString(2);


                // add new row
                TableRow r= new TableRow(this);
                TextView t4 = new TextView(this);
                t4.setText(time);
                TextView t5 = new TextView(this);
                t5.setText(desti);
                TextView t6 = new TextView(this);
                t6.setText(lat_long);
                Button bt = new Button(this);
                bt.setText("Get Directions");
                bt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent activityChangeIntent = new Intent(ViewHistory.this, MapsActivity.class);
                        // currentContext.startActivity(activityChangeIntent);
                        try {
                            activityChangeIntent.putExtra("destination", lat_long);
                            activityChangeIntent.putExtra("origin_lat", origin_lat);
                            activityChangeIntent.putExtra("origin_long", origin_long);
                        }catch(Exception ex) {
                            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        ViewHistory.this.startActivity(activityChangeIntent);
                    }
                });
                r.addView(t5); r.addView(bt);
                history_tbl.addView(r,i);
                i++;
                history_tbl.addView(new TableRow(this), i);

                cur.moveToNext();
                i++;
            }

            cur.close();
            db.close();
            dbHelper.close();
        }catch(Exception ex) {
            Toast.makeText(getBaseContext(), "No search history", Toast.LENGTH_LONG).show();
        }
    }
}
