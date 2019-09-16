package com.cenah.instagramclone.fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cenah.instagramclone.R;
import com.cenah.instagramclone.UI.PrograssBarDialog;
import com.cenah.instagramclone.activites.HomeActivity;
import com.cenah.instagramclone.adapters.ProfileImageAdapter;
import com.cenah.instagramclone.helpers.ApplicationPreferenceManager;
import com.cenah.instagramclone.models.Picture;
import com.cenah.instagramclone.models.UserInfo;
import com.cenah.instagramclone.rest.RestFullHelper;
import com.cenah.instagramclone.rest.services.InstagramService;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenah.instagramclone.helpers.Utils.SELECTED_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileImageAdapter.OnProfileAdapterClick {

    private View rootView;
    private Toolbar toolbar;
    private PrograssBarDialog prograssBarDialog;
    private TextView tvPosts, tvFollowers, tvFollowing, display_name, description, website;
    private CardView btn_edit;
    private CircleImageView profile_image;
    private RecyclerView recyclerView;
    private ProfileImageAdapter profileImageAdapter;
    private ArrayList<Picture.PictureDetail> arrayList;
    private Activity activity;
    private InstagramService instagramService;
    private UserInfo userInfo;
    private Picture pictures;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_action_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_item:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        prograssBarDialog = new PrograssBarDialog(getContext());
        activity = getActivity();
        instagramService = new RestFullHelper().getInstagramServiceClient();
        setToolBar();
        init();
        getUserInfo();
        return rootView;
    }

    private void init() {
        tvPosts = rootView.findViewById(R.id.tvPosts);
        profile_image = rootView.findViewById(R.id.profile_image);
        tvFollowers = rootView.findViewById(R.id.tvFollowers);
        tvFollowing = rootView.findViewById(R.id.tvFollowing);
        display_name = rootView.findViewById(R.id.display_name);
        description = rootView.findViewById(R.id.description);
        website = rootView.findViewById(R.id.website);
        btn_edit = rootView.findViewById(R.id.btn_edit);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        profileImageAdapter = new ProfileImageAdapter(activity, ProfileFragment.this, arrayList);
        recyclerView.setAdapter(profileImageAdapter);
    }

    private void setToolBar() {
        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        // remove back button
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });*/

    }

    private void getUserInfo() {
        prograssBarDialog.show();
        Call<UserInfo> response = instagramService.getUserInfo(new ApplicationPreferenceManager(activity).getToken());
        response.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                prograssBarDialog.hide();
                if (response.body() == null || !response.isSuccessful()) {
                    Toast.makeText(getContext(), "user Info Error", Toast.LENGTH_SHORT).show();
                }
                userInfo = response.body();
                if (userInfo != null)
                    showUserInfo();

                getUserPictures();
            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                prograssBarDialog.hide();
            }
        });
    }

    private void showUserInfo() {
        tvPosts.setText(String.valueOf(userInfo.getData().getCounts().getMedia()));
        tvFollowers.setText(String.valueOf(userInfo.getData().getCounts().getFollowed_by()));
        tvFollowing.setText(String.valueOf(userInfo.getData().getCounts().getFollows()));
        display_name.setText(userInfo.getData().getFull_name());
        description.setText(userInfo.getData().getBio());
        website.setText(userInfo.getData().getWebsite());
        toolbar.setTitle(userInfo.getData().getUsername());
        Picasso.get().load(userInfo.getData().getProfile_picture()).error(R.drawable.avatar).resize(500, 500)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE).into(profile_image);
    }

    private void getUserPictures() {
        prograssBarDialog.show();
        // Call<UserInfo> response = instagramService.getUserInfo(new ApplicationPreferenceManager(activity).getToken());
        Call<Picture> call = instagramService.getUserPhotos(new ApplicationPreferenceManager(activity).getToken());
        call.enqueue(new Callback<Picture>() {
            @Override
            public void onResponse(@NotNull Call<Picture> call, @NotNull Response<Picture> response) {
                prograssBarDialog.hide();
                if (response.body() == null || !response.isSuccessful()) {
                    Toast.makeText(getContext(), "user Info Error", Toast.LENGTH_SHORT).show();
                }
                pictures = response.body();
                if (pictures != null) {
                    arrayList = pictures.getData();
                }
                //  profileImageAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(new ProfileImageAdapter(activity, ProfileFragment.this, arrayList));
            }

            @Override
            public void onFailure(@NotNull Call<Picture> call, @NotNull Throwable t) {
                prograssBarDialog.hide();
            }
        });
    }

    @Override
    public void onClick(Picture.PictureDetail filesNameModel, int position, View view) {
        SELECTED_IMAGE.setPicture(filesNameModel);
        SELECTED_IMAGE.setPosition(position);
        ((HomeActivity) Objects.requireNonNull(getActivity())).addFragment(new ProfileImagesListFragment(), false);
    }
}
