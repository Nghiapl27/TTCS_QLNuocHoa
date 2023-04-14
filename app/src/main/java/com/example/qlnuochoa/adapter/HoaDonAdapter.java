package com.example.qlnuochoa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlnuochoa.R;
import com.example.qlnuochoa.dao.HoaDonDAO;
import com.example.qlnuochoa.dao.NhanVienDAO;
import com.example.qlnuochoa.dao.NuocHoaDAO;
import com.example.qlnuochoa.fragment.HoadonFragment;
import com.example.qlnuochoa.model.HoaDon;
import com.example.qlnuochoa.model.NhanVien;
import com.example.qlnuochoa.model.Nuochoa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    HoadonFragment fragment;
    private ArrayList<HoaDon> lists;
    TextView tvMaHD, tvHDTenGiay, tvHDGiaMua, tvHDNgayMua, tvHDTrangThai, tvTenNV;
    ImageView imgDeleteHD;
    NuocHoaDAO nuocHoaDAO;
    HoaDonDAO hoaDonDAO;
    NhanVienDAO nhanVienDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonAdapter(@NonNull Context context, HoadonFragment fragment, ArrayList<HoaDon> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.hoa_don_item,null);
        }
        final HoaDon item = lists.get(position);
        if(item != null){
            tvMaHD = v.findViewById(R.id.tvMaHD_item);
            tvMaHD.setText("ID: "+item.maHD);

            nuocHoaDAO = new NuocHoaDAO(context);
            Nuochoa nuochoa = nuocHoaDAO.getID(String.valueOf(item.maNuocHoa));
            tvHDTenGiay = v.findViewById(R.id.tvTenGiay_HD_item);
            tvHDTenGiay.setText("Nước hoa: "+nuochoa.tenNuocHoa);

            tvTenNV = v.findViewById(R.id.tvTenNV_item);
            nhanVienDAO = new NhanVienDAO(context);
            NhanVien nhanVien = nhanVienDAO.getTen(String.valueOf(item.maNV));
            tvTenNV.setText("Tên nhân viên bán: "+ nhanVien.hoTenNV);


            tvHDGiaMua = v.findViewById(R.id.tvGiaMua_HD_item);
            tvHDGiaMua.setText("Giá: "+item.giaHD);

            tvHDNgayMua = v.findViewById(R.id.tvNgayMua_HD_item);
            tvHDNgayMua.setText("Date: "+item.ngay);

            tvHDTrangThai = v.findViewById(R.id.tvTrangThai_HD_item);
            if(item.trangThai == 1){
                tvHDTrangThai.setTextColor(Color.GREEN);
                tvHDTrangThai.setText("Đã thanh toán");
            }else{
                tvHDTrangThai.setTextColor(Color.RED);
                tvHDTrangThai.setText("Chưa thanh toán");
            }
            imgDeleteHD = v.findViewById(R.id.img_delete_HD);
        }
        imgDeleteHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoaHoaDon(String.valueOf(item.maHD));
            }
        });
        return v;
    }
}
