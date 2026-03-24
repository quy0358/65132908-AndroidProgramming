package ntquy.ntu.appmonan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MonAnAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MonAn> danhSachMonAn;

    public MonAnAdapter(Context context, ArrayList<MonAn> danhSachMonAn) {
        this.context = context;
        this.danhSachMonAn = danhSachMonAn;
    }

    @Override
    public int getCount() {
        return danhSachMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_mon_an, parent, false);

            holder = new ViewHolder();
            holder.imgMonAn = convertView.findViewById(R.id.imgMonAn);
            holder.tvTenMon = convertView.findViewById(R.id.tvTenMon);
            holder.tvGia = convertView.findViewById(R.id.tvGia);
            holder.tvMoTa = convertView.findViewById(R.id.tvMoTa);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MonAn monAn = danhSachMonAn.get(position);
        holder.imgMonAn.setImageResource(monAn.getHinhAnh());
        holder.tvTenMon.setText(monAn.getTenMon());
        holder.tvGia.setText(monAn.getGia());
        holder.tvMoTa.setText(monAn.getMoTa());

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgMonAn;
        TextView tvTenMon;
        TextView tvGia;
        TextView tvMoTa;
    }
}
