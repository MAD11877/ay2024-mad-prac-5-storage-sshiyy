package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean Following;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        int randomNumber = getIntent().getIntExtra("randomNumber", 0);
        String name = getIntent().getStringExtra("name");

        TextView textViewName = findViewById(R.id.textview2);
        TextView textViewRandom = findViewById(R.id.textview2);
        TextView textViewDesc = findViewById(R.id.textview3);
        textViewName.setText(name);
        textViewRandom.setText("MAD " + randomNumber);
        textViewDesc.setText("MAD Developer");

        User user = dbHandler.getUser(name);


        Button btnFollow = findViewById(R.id.button1);
        Button btnmsg = findViewById(R.id.button2);
        btnFollow.setText("Follow");

        btnFollow.setOnClickListener(view -> {
            String toastMessage;
            if(user.followed){
                btnFollow.setText("Unfollow");
                toastMessage = "Followed";
                user.setFollowed(Following);
                dbHandler.updateUser(user);
            } else {
                btnFollow.setText("Follow");
                toastMessage = "Unfollowed";
            }
            user.followed = !user.followed;
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
        });

    }
}