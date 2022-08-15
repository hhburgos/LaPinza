package com.cninfotech.template.buyer.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberVH> {
    private List<String> items;
    private List<String> members;
    private Context context;
    public static boolean isExpandible = true;

    public MemberAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;

        this.members = new ArrayList<>();
    }

    @NonNull
    @Override
    public MemberVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acordion_member, parent, false);

        return new MemberVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberVH holder, int position) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, this.members);
        holder.listViewMembers.setAdapter(adapter);

        holder.accordion.setVisibility(isExpandible ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class MemberVH extends RecyclerView.ViewHolder {
        public EditText organizationMember;
        public Button btnAddMember;
        public ListView listViewMembers;
        public RelativeLayout accordion;
        public RelativeLayout relative_tile;
        private LinearLayout linearLayout;

        public MemberVH(@NonNull View itemView) {
            super(itemView);

            this.organizationMember = itemView.findViewById(R.id.input_organization_name);
            this.btnAddMember = itemView.findViewById(R.id.btn_addmember);
            this.listViewMembers = itemView.findViewById(R.id.lvMembers);
            this.accordion = itemView.findViewById(R.id.expandable_layout);
            this.linearLayout = itemView.findViewById(R.id.linear_layout);
            this.relative_tile = itemView.findViewById(R.id.relative_tile);

            this.relative_tile.setOnClickListener(v -> {
                isExpandible = !isExpandible;
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
