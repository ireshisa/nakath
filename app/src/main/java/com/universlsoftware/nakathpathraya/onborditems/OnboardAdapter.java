package com.universlsoftware.nakathpathraya.onborditems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.universlsoftware.nakathpathraya.R;

import java.util.List;

public class OnboardAdapter extends RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder> {

    private List<OnboardItem> onboardItems;

    public OnboardAdapter(List<OnboardItem> onboardItems) {
        this.onboardItems = onboardItems;
    }

    @NonNull
    @Override
    public OnboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardViewHolder holder, int position) {
        holder.setOnbordingData(onboardItems.get(position));

    }

    @Override
    public int getItemCount() {
        return onboardItems.size();
    }

    class OnboardViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDescriotion;
        private ImageView imageOnboarding;

        public OnboardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textTitle = itemView.findViewById(R.id.textTitle);
            this.textDescriotion = itemView.findViewById(R.id.textDescription);
            this.imageOnboarding = itemView.findViewById(R.id.imagOnbarding);

        }

        void setOnbordingData(OnboardItem onboardItem) {
            textTitle.setText(onboardItem.getTitle());
            textDescriotion.setText(onboardItem.getDescription());
            imageOnboarding.setImageResource(onboardItem.getImage());

        }


    }


}
