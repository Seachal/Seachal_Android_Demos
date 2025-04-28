package com.seachal.seachaltest.popupwindow;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.seachal.seachaltest.R;

/**
 * 使用PopupMenu实现上下文菜单
 */
public class PopupMenuActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_popup_menu);

        Button btnShowMenu = findViewById(R.id.btn_show_menu);
        btnShowMenu.setOnClickListener(v -> showPopupMenu(v));
    }

    private void showPopupMenu(android.view.View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.o_menu_popup);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_add) {
            showToast("添加");
            return true;
        } else if (itemId == R.id.action_edit) {
            showToast("编辑");
            return true;
        } else if (itemId == R.id.action_delete) {
            showToast("删除");
            return true;
        }
        return false;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}