package com.cos.insta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//3. 상속받기
//오류는 알트엔터
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private static final String TAG = "UserAdapter";

    //4. 컬렉션 만들기
    private final List<Data> datas;

    public RecyclerAdapter(List<Data> datas){
        this.datas=datas;
    }

    //5. addItem, removeItem
    public void addItem(Data data){
        datas.add(data);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        datas.remove(position);
    }

    //7. getView랑 똑같음
    //차이점: listView는 화면에 3개 필요하면 최초 로딩시 3개 그려야하니까 getView 3번 호출
    //그다음 스크롤해서 2개가 추가될 때 다시 getView 호출
    //but, recyclerView는 스크롤해서 2개 추가될 때 onBindViewHoler 호출
    //onCreateViewHolder: 해당 Activiy 실행시에에만 호출
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_view, parent,false);
        return new MyViewHolder(view);//view가 리스트뷰에 하나 그려짐
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.setItem(datas.get(position));

    }

    //6. 컬렉션 크기 알려주기(화면에 몇 개 그려야할지 알아야하기 때문)
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //순서 중요
    //1. ViewHolder 만들기
    //ViewHolder란? 하나의 View를 가지고 있는 Holder.
    //오류는 알트 엔터 constructer만들기
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //2. data_item이 가지고 있는 위젯들을 선언
        private CircleImageView civIdimg;
        private TextView tvUsername;
        private ImageView ivMainimg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            civIdimg = itemView.findViewById(R.id.img_pro1);
            tvUsername = itemView.findViewById(R.id.tv_username);
            ivMainimg = itemView.findViewById(R.id.main_img);

        }

        public void setItem(Data data){
            //civIdimg.setImage
            //civIdimg.setText(data.getId().toString());
            //tvUsername.setText(data.getUsername());
        }
    }

    //↑↑↑ 데이터 없이 View만 가지고 있다.
}
