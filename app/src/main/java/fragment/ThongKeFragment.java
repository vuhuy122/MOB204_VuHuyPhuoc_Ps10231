package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab1_vuhuyphuoc_ps10231.R;

public class ThongKeFragment extends Fragment {
   TextView tvThoongKeNgay, tvThongKeThang, tvThongKeNam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvThoongKeNgay = view.findViewById(R.id.tvThongKeNgay);
        tvThongKeThang = view.findViewById(R.id.tvThongKeThang);
        tvThongKeNam = view.findViewById(R.id.tvThongKeNam);
    }
}
