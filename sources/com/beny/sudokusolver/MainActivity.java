package com.beny.sudokusolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    static int[][] puzzle = ((int[][]) Array.newInstance(int.class, new int[]{9, 9}));
    Button b;
    int c = 1;
    String id;
    int r = 1;
    Button reset;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        this.b = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int text;
                MainActivity.this.c = 1;
                MainActivity.this.r = 1;
                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) {
                        MainActivity.this.id = "t" + MainActivity.this.c + "r" + MainActivity.this.r;
                        EditText t = (EditText) MainActivity.this.findViewById(MainActivity.this.getResources().getIdentifier(MainActivity.this.id, "id", MainActivity.this.getPackageName()));
                        if (t.getText().toString().equals("")) {
                            text = 0;
                        } else {
                            text = Integer.parseInt(t.getText().toString());
                        }
                        MainActivity.puzzle[i][j] = text;
                        MainActivity.this.c++;
                    }
                    MainActivity.this.c = 1;
                    MainActivity.this.r++;
                }
                if (!MainActivity.this.isValid(MainActivity.puzzle)) {
                    Toast.makeText(MainActivity.this, "Error!\twrong input \n this sudoku can't be solved", 1).show();
                } else {
                    SodukuSolver.solve(MainActivity.puzzle);
                }
                MainActivity.this.c = 1;
                MainActivity.this.r = 1;
                for (int i2 = 0; i2 <= 8; i2++) {
                    for (int j2 = 0; j2 <= 8; j2++) {
                        MainActivity.this.id = "t" + MainActivity.this.c + "r" + MainActivity.this.r;
                        ((EditText) MainActivity.this.findViewById(MainActivity.this.getResources().getIdentifier(MainActivity.this.id, "id", MainActivity.this.getPackageName()))).setText(MainActivity.puzzle[i2][j2] + "");
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.c = mainActivity.c + 1;
                    }
                    MainActivity.this.c = 1;
                    MainActivity.this.r++;
                }
            }
        });
        Button button2 = (Button) findViewById(R.id.resetbtn);
        this.reset = button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.c = 1;
                MainActivity.this.r = 1;
                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) {
                        MainActivity.this.id = "t" + MainActivity.this.c + "r" + MainActivity.this.r;
                        MainActivity.puzzle[i][j] = 0;
                        ((EditText) MainActivity.this.findViewById(MainActivity.this.getResources().getIdentifier(MainActivity.this.id, "id", MainActivity.this.getPackageName()))).setText(MainActivity.puzzle[i][j] + "");
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.c = mainActivity.c + 1;
                    }
                    MainActivity.this.c = 1;
                    MainActivity.this.r++;
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.About /*2131230721*/:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.feedback /*2131230883*/:
                startActivity(new Intent(this, Feedback.class));
                return true;
            default:
                return true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean isValid(int[][] grid) {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                int temp = grid[i][j];
                grid[i][j] = 0;
                for (int x = 0; x <= 8; x++) {
                    if (grid[i][x] != 0 && grid[i][x] == temp) {
                        return false;
                    }
                }
                grid[i][j] = temp;
            }
        }
        for (int i2 = 0; i2 <= 8; i2++) {
            for (int j2 = 0; j2 <= 8; j2++) {
                int temp2 = grid[i2][j2];
                grid[i2][j2] = 0;
                for (int x2 = 0; x2 <= 8; x2++) {
                    if (grid[x2][j2] != 0 && grid[x2][j2] == temp2) {
                        return false;
                    }
                }
                grid[i2][j2] = temp2;
            }
        }
        for (int i3 = 0; i3 <= 8; i3++) {
            for (int j3 = 0; j3 <= 8; j3++) {
                int temp3 = grid[i3][j3];
                grid[i3][j3] = 0;
                int startRow = i3 - (i3 % 3);
                int startCol = j3 - (j3 % 3);
                for (int k = 0; k < 3; k++) {
                    for (int z = 0; z < 3; z++) {
                        if (grid[k + startRow][z + startCol] != 0 && grid[k + startRow][z + startCol] == temp3) {
                            return false;
                        }
                    }
                }
                grid[i3][j3] = temp3;
            }
        }
        return true;
    }
}
