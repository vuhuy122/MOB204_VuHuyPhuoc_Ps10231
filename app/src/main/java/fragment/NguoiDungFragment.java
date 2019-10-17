package fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lab1_vuhuyphuoc_ps10231.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.NguoiDungAdapter;
import DAO.NguoiDungDAO;
import model.NguoiDung;


public class NguoiDungFragment extends Fragment {

    ListView lvNguoiDunng;
    ArrayList<NguoiDung> list;
    NguoiDungAdapter nguoiDungAdapter;
    NguoiDungDAO nguoiDungDAO;
    EditText edUserName_add, edPassword_add, edRePassword_add, edPhone, edFullName_add;
    Button btnAddUser, btnCancelUser, btnShowUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvNguoiDunng = view.findViewById(R.id.lv_NguoiDung);
        list = new ArrayList<>();
        nguoiDungDAO = new NguoiDungDAO(getContext());
        nguoiDungAdapter = new NguoiDungAdapter(getContext(), list);
        lvNguoiDunng.setAdapter(nguoiDungAdapter);


        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.FABThemNguoiDung);
        floatingActionButton.show();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                edUserName_add = v.findViewById(R.id.edUserName_add);
                edPassword_add = v.findViewById(R.id.edPassword_add);
                edRePassword_add = v.findViewById(R.id.edRePassword_add);
                edPhone = v.findViewById(R.id.edPhone);
                edFullName_add = v.findViewById(R.id.edFullName);

                btnAddUser = v.findViewById(R.id.btnAddUser);
                btnCancelUser = v.findViewById(R.id.btnCancelUser);
                btnShowUser = v.findViewById(R.id.btnShowUser);

                nguoiDungDAO = new NguoiDungDAO(getContext());
                List<NguoiDung> list = nguoiDungDAO.getAllNguoiDung();
                ArrayAdapter<NguoiDung> a = new ArrayAdapter<NguoiDung>(getContext(), android.R.layout.simple_list_item_1, list) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = view.findViewById(R.id.text);
                        if (position % 2 == 0) {
                            textView.setTextColor(Color.RED);
                        } else
                            textView.setTextColor(Color.CYAN);
                        return view;
                    }
                };
                alertDialog.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        nguoiDungDAO = new NguoiDungDAO(getActivity());
                        NguoiDung user = new NguoiDung(edUserName_add.getText().toString(),
                                edPassword_add.getText().toString(), edPhone.getText().toString(),
                                edFullName_add.getText().toString());
                        try {
                            if (validateForm() > 0) {
                                try {
                                    if (nguoiDungDAO.inserNguoiDung(user) > 0) {
                                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NullPointerException npe) {
                                    Toast.makeText(getContext(), "Lỗi NullPointerException", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception ex) {
                            Log.e("Error", ex.toString());
                        }
                    }


                    public int validateForm() {
                        int check = 1;
                        if (edUserName_add.getText().length() == 0 || edFullName_add.getText().length() == 0 || edPhone.getText().length() == 0
                                || edPassword_add.getText().length() == 0 ||
                                edRePassword_add.getText().length() == 0) {
                            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
                            check = -1;
                        } else {
                            String pass = edPassword_add.getText().toString();
                            String rePass = edRePassword_add.getText().toString();
                            if (!pass.equals(rePass)) {
                                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                                check = -1;
                            }
                        }
                        return check;
                    }
                });
                alertDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }


        });
    }


}
