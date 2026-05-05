package thi.quy65132908.gk1.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskActionListener {

    private EditText etTaskTitle;
    private ImageButton btnAdd;
    private RecyclerView rvTasks;
    private LinearLayout layoutEmpty;
    private TextView tvTaskCount;

    private List<Task> taskList;
    private TaskAdapter taskAdapter;
    private DatabaseReference tasksRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo Firebase
        tasksRef = FirebaseDatabase.getInstance().getReference("tasks");

        // Ánh xạ view
        etTaskTitle = findViewById(R.id.etTaskTitle);
        btnAdd = findViewById(R.id.btnAdd);
        rvTasks = findViewById(R.id.rvTasks);
        layoutEmpty = findViewById(R.id.layoutEmpty);
        tvTaskCount = findViewById(R.id.tvTaskCount);

        // Khởi tạo RecyclerView
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList, this);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskAdapter);

        // Sự kiện thêm task
        btnAdd.setOnClickListener(v -> addTask());

        // Xử lý nhấn Done trên bàn phím
        etTaskTitle.setOnEditorActionListener((v, actionId, event) -> {
            addTask();
            return true;
        });

        // Lắng nghe dữ liệu từ Firebase (realtime)
        loadTasks();
    }

    private void addTask() {
        String title = etTaskTitle.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo key mới trên Firebase
        String id = tasksRef.push().getKey();
        if (id == null) return;

        Task task = new Task(id, title, false);
        tasksRef.child(id).setValue(task).addOnCompleteListener(t -> {
            if (t.isSuccessful()) {
                etTaskTitle.setText("");
                Toast.makeText(this, R.string.toast_added, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTasks() {
        tasksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Task task = dataSnapshot.getValue(Task.class);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
                taskAdapter.notifyDataSetChanged();
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,
                        "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        if (taskList.isEmpty()) {
            rvTasks.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
            tvTaskCount.setText(R.string.no_tasks);
        } else {
            rvTasks.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
            tvTaskCount.setText(String.format(getString(R.string.task_count), taskList.size()));
        }
    }

    // === Callback từ Adapter ===

    @Override
    public void onToggleCompleted(Task task, boolean isCompleted) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("completed", isCompleted);
        tasksRef.child(task.getId()).updateChildren(updates);
    }

    @Override
    public void onEditTask(Task task) {
        // Hiển thị dialog chỉnh sửa
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_task, null);
        EditText etEditTitle = dialogView.findViewById(R.id.etEditTitle);
        etEditTitle.setText(task.getTitle());
        etEditTitle.setSelection(task.getTitle().length());

        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_edit_title)
                .setView(dialogView)
                .setPositiveButton(R.string.btn_save, (dialog, which) -> {
                    String newTitle = etEditTitle.getText().toString().trim();
                    if (!newTitle.isEmpty()) {
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("title", newTitle);
                        tasksRef.child(task.getId()).updateChildren(updates)
                                .addOnCompleteListener(t -> {
                                    if (t.isSuccessful()) {
                                        Toast.makeText(this, R.string.toast_updated,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }

    @Override
    public void onDeleteTask(Task task) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_delete_title)
                .setMessage(R.string.dialog_delete_message)
                .setPositiveButton(R.string.btn_delete, (dialog, which) -> {
                    tasksRef.child(task.getId()).removeValue()
                            .addOnCompleteListener(t -> {
                                if (t.isSuccessful()) {
                                    Toast.makeText(this, R.string.toast_deleted,
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }
}