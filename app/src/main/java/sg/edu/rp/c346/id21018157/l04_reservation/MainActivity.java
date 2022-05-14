package sg.edu.rp.c346.id21018157.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvReservation;
    ImageView ivCafe;
    TextView tvDate;
    DatePicker dp;
    TextView tvTime;
    TimePicker tp;
    CheckBox cbSmoking;
    TextView tvDetails;
    EditText etName;
    EditText etNumber;
    EditText etPax;
    Button btnConfirm;
    Button btnReset;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvReservation = findViewById(R.id.textViewReservation);
        ivCafe = findViewById(R.id.imageViewCafe);
        tvDate = findViewById(R.id.textViewDate);
        dp = findViewById(R.id.datePicker);
        tvTime = findViewById(R.id.textViewTime);
        tp = findViewById(R.id.timePicker);
        cbSmoking = findViewById(R.id.checkBoxSmoking);
        tvDetails = findViewById(R.id.textViewDetails);
        etName = findViewById(R.id.editTextName);
        etNumber = findViewById(R.id.editTextNumber);
        etPax = findViewById(R.id.editTextPax);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        tvMessage = findViewById(R.id.textViewMessage);

        //default time and date once app is opened
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        dp.updateDate(2020, 06-1,01); //june 06 must -1 (months start from 0)

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = String.format("%02d", dp.getDayOfMonth());
                String month = String.format("%02d", dp.getMonth()+1);
                String year = String.valueOf(dp.getYear());

                String hour = String.valueOf(tp.getCurrentHour());
                String minute = String.format("%02d", tp.getCurrentMinute());

                //tvMessage.setText(hour + minute + day + month + year); //tweak

                String name = String.valueOf(etName.getText());
                String number = String.valueOf(etNumber.getText());
                String pax = String.valueOf(etPax.getText());

                //enhancement1: error message on toast
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etNumber.getText().toString()) ||
                        TextUtils.isEmpty(etPax.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Reservation form is incomplete.",Toast.LENGTH_SHORT).show();

                } else if (cbSmoking.isChecked()) {
                         Toast.makeText(MainActivity.this, "Dear Mr/Mrs " + name + "," + "\nYour table in the smoking area " +
                                "is booked for " + pax + " pax at " +  hour + minute + " hours " + " on "
                                + day + "/" + month + "/" + year + "\nPhone No.: " + number, Toast.LENGTH_SHORT).show();

                        tvMessage.setText("Dear Mr/Mrs " + name + "," + "\nYour table in the smoking area " +
                                "is booked for " + pax + " pax at " +  hour + minute + " hours " + " on "
                                 + day + "/" + month + "/" + year + "\nPhone No.: " + number);
                    } else {
                        Toast.makeText(MainActivity.this, "Dear Mr/Mrs " + name + "," + "\nYour table in the non-smoking area " +
                                "is booked for " + pax + " pax at " +  hour + minute + " hours " + " on "
                                + day + "/" + month + "/" + year + "\nPhone No.: " + number, Toast.LENGTH_SHORT).show();

                        tvMessage.setText("Dear Mr/Mrs " + name + "," + "\nYour table in the non-smoking area " +
                                "is booked for " + pax + " pax at " +  hour + minute + " hours " + " on "
                                + day + "/" + month + "/" + year + "\nPhone No.: " + number);
                    }

            }
        });

        //enhancement2: max and min time in timePicker
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                boolean flag = true;
                int maximumHour = 20;
                int minimumHour = 8;
                int maximumMin = 59;
                int minimumMin = 00;

                int currentHour = hourOfDay;
                int currentMin = minute;
            }
        });

         btnReset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 tp.setCurrentHour(19);
                 tp.setCurrentMinute(30);
                 dp.updateDate(2020, 06-1,01);

                 etName.getText().clear();
                 etNumber.getText().clear();
                 etPax.getText().clear();
                 tvMessage.setText("");
             }
         });

    }
}
