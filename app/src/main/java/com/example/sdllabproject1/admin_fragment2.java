package com.example.sdllabproject1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class admin_fragment2 extends Fragment {

    RecyclerView recyclerView;
    public ArrayList<projectTitles> projectList;
    private RecyclerView.Adapter Adapter;
    List<String> list;
    String b;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.admin_tab2,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        projectList = new ArrayList<>();

            FirebaseFirestore.getInstance().collection("Project").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        ProjectDetails pd = documentSnapshot.toObject(ProjectDetails.class);
                        if(pd.getStatus().equals("Upcoming"))
                        projectList.add(new projectTitles(pd.getTitle(),pd.getDescription(),pd.getTeamLead(),pd.getStatus()));

                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    final RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
                    recyclerView.setLayoutManager(rvLiLayoutManager);
                    project_details dom = new project_details(getActivity(), projectList);
                    recyclerView.setAdapter(dom);
                }
            });
        Log.d("lips4t", "onCreateView: "+projectList);




return view;
    }
}
