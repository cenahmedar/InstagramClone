package com.cenah.instagramclone.fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cenah.instagramclone.R;
import com.cenah.instagramclone.UI.PrograssBarDialog;
import com.cenah.instagramclone.adapters.ImageListAdapter;
import com.cenah.instagramclone.adapters.ProfileImageAdapter;
import com.cenah.instagramclone.helpers.ApplicationPreferenceManager;
import com.cenah.instagramclone.models.Picture;
import com.cenah.instagramclone.rest.RestFullHelper;
import com.cenah.instagramclone.rest.services.InstagramService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenah.instagramclone.helpers.Utils.SELECTED_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileImagesListFragment extends Fragment implements ImageListAdapter.OnProfileAdapterClick{

    private View rootView;
    private Toolbar toolbar;
    private PrograssBarDialog prograssBarDialog;
    private InstagramService instagramService;
    private Activity activity;
    private RecyclerView recyclerView;
    private ArrayList<Picture.PictureDetail> arrayList;
    private ImageListAdapter imageListAdapter;
    private Picture pictures;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile_images_list, container, false);
        prograssBarDialog = new PrograssBarDialog(getContext());
        activity = getActivity();
        instagramService = new RestFullHelper().getInstagramServiceClient();
        setToolBar();
        init();
        getUserPictures();
        return rootView;
    }

    private void init() {
        recyclerView = rootView.findViewById(R.id.recyclerView);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        imageListAdapter = new ImageListAdapter(activity, ProfileImagesListFragment.this, arrayList);
        recyclerView.setAdapter(imageListAdapter);
    }

    private void setToolBar() {
        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("Posts");
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
    }

    private void getUserPictures() {
        prograssBarDialog.show();
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
                recyclerView.setAdapter(new ImageListAdapter(activity, ProfileImagesListFragment.this, arrayList));
                recyclerView.scrollToPosition(SELECTED_IMAGE.getPosition());
            }

            @Override
            public void onFailure(@NotNull Call<Picture> call, @NotNull Throwable t) {
                prograssBarDialog.hide();
            }
        });
    }


    @Override
    public void onMoreClick(Picture.PictureDetail filesNameModel, int position, View view) {

    }

    @Override
    public void onLikeClick(Picture.PictureDetail filesNameModel, int position, View view) {

    }

    @Override
    public void onCommentClick(Picture.PictureDetail filesNameModel, int position, View view) {

    }

    @Override
    public void onSendClick(Picture.PictureDetail filesNameModel, int position, View view) {

    }

    @Override
    public void onSaveClick(Picture.PictureDetail filesNameModel, int position, View view) {

    }
}
