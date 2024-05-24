package sg.edu.np.mad.madpractical5;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> list_objects;
    private ListActivity activity;
    public UserAdapter(ArrayList<User> list_objects, ListActivity activity) {
        this.list_objects = list_objects;
        this.activity = activity;
    }
    // method to create a view holder for a username
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    // method to bind username to a viewholder
    public void onBindViewHolder(UserViewHolder holder, int position) {
        // get position of username
        User list_items = list_objects.get(position);

        if(String.valueOf(list_items.name).endsWith("7")) {
            holder.largeImage.setVisibility(View.VISIBLE);
        }

        holder.name.setText(list_items.name);
        holder.description.setText(list_items.description);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Profile")
                        .setMessage(list_items.name)
                        .setCancelable(true)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Random random = new Random();
                                int randomnum = random.nextInt(999999);
                                Intent mainactivity = new Intent(activity, MainActivity.class);
                                mainactivity .putExtra("name", list_items.name + randomnum);
                                mainactivity .putExtra("description", list_items.description);
                                mainactivity .putExtra("followed", list_items.followed);
                                mainactivity .putExtra("id", list_items.id);
                                v.getContext().startActivity(mainactivity);
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    public int getItemCount() {
        return list_objects.size();}

}