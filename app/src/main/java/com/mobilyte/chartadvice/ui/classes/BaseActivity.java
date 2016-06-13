package com.mobilyte.chartadvice.ui.classes;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;

/**
 * Created by mohit on 7/6/16.
 */
public class BaseActivity extends AppCompatActivity {

    // dialog
    private ProgressDialog pd;

    protected void showDialog() {
        hideDialog();
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait....");
        pd.show();
    }

    protected void hideDialog() {
        if (pd != null && pd.isShowing())
            pd.dismiss();
    }

    protected void showToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private Snackbar snackbar;

    protected void snackBarMessage(View view, String message) {
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    protected void snackBarRetry(View view, final String tag, final RetryInterface callback) {
        snackbar = Snackbar.make(view, getString(R.string.internet_connection_error), Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                callback.onRetry(tag);
            }
        });

        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        if (snackbar != null && snackbar.isShownOrQueued())
            snackbar.dismiss();
        super.onDestroy();
    }

    protected void showFragment(Fragment fragment, String tag, int id) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        boolean popped = fragmentManager.popBackStackImmediate(tag, 0);
        if (popped) {
            // fragment popped from backStack
        } else {
            android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(id, fragment);
            ft.addToBackStack(tag);
            ft.commit();
        }
    }
}
