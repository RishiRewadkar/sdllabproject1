package com.example.sdllabproject1;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class project_detailse extends RecyclerView.Adapter<project_detailse.ViewHolder> {

    private Context mContext;
    private ArrayList<projectTitles> pro_title;
    TextView taskname, projectname, taskdesc;
    PopupWindow popupWindow;
    String role,td,tn,pn;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public project_detailse(Context context, ArrayList<projectTitles> title){
        mContext = context;
        pro_title = title;
        for(int i = 0;i<title.size();i++){
            expandState.append(i,false);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
      final  LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View view =  layoutInflater.inflate(R.layout.info,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                LayoutInflater inflater = (LayoutInflater)
                        view1.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_task, null);

                // create the popup window
                int width = 700;
                int height = 550;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                projectname = popupView.findViewById(R.id.projectname);
                taskname = popupView.findViewById(R.id.taskname);
                taskdesc = popupView.findViewById(R.id.taskdesc);

               final TextView textView = view.findViewById(R.id.tv);
                FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("tasks").get().
                        addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                 @Override
                                                 public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                     for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                         task pd = documentSnapshot.toObject(task.class);
                                                         if(pd.getTaskname().equals(textView.getText().toString()))
                                                         taskdesc.setText(pd.getTaskdesc());
                                                         projectname.setText(pd.getProjectname());
                                                     }
                                                 }

            });
                taskname.setText(textView.getText().toString());


                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view1, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

       String tlead = "Team Lead : ";
        holder.setIsRecyclable(false);
        projectTitles item=pro_title.get(position);
        holder.title.setTag(item);
        TextView name = holder.title;
        name.setText(pro_title.get(position).getDname());
        holder.desc.setText(pro_title.get(position).getDesc());
        holder.lead.setText(tlead + pro_title.get(position).getLead());
        holder.stat.setText(pro_title.get(position).getDate());
        final boolean isExpanded = expandState.get(position);
        holder.expandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        holder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout, holder.buttonLayout,  position);
            }
        });

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menue);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                      //  projectTitles cards=(projectTitles) view.getTag();
                      //  final String value = cards.getDname();
                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                FirebaseFirestore.getInstance().collection("USERS").document(user).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        final ModelUsers md = task.getResult().toObject(ModelUsers.class);
                        role = md.getRole();
                    }
                });
                        switch (item.getItemId()) {
                            case R.id.action_eod:
                                Intent intent = new Intent(view.getContext(), newnoteactivity.class);
                                intent.putExtra("project",pro_title.get(position).getDname());
                                intent.putExtra("task",pro_title.get(position).getLead());
                                mContext.startActivity(intent);

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }



    @Override
    public int getItemCount() {
        return pro_title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc,lead,stat,buttonViewOption;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;

        Button but;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv);
            desc = itemView.findViewById(R.id.tv_desc);
            lead = itemView.findViewById(R.id.tv_lead);
            stat = itemView.findViewById(R.id.tv_status);
            buttonViewOption=itemView.findViewById(R.id.textViewOptions);
            but = itemView.findViewById(R.id.selectButton);
            expandableLayout = (LinearLayout) itemView.findViewById(R.id.expandableLayout);
            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.button);


        }
    }
    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final  int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        }else{
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}
