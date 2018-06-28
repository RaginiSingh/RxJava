package android.rxjava.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HotObservableActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_observable);
    }

    public void goToSubjectObservable(View v) {
        //Subject is used to create hot observable and to convert cold into hot observable

        Intent intent = new Intent(this, HotObservableSubjectActivity.class);
        startActivity(intent);
    }

    public void goToConnectableObservable(View v) {
        Intent intent = new Intent(this, ConnectableObservableActivity.class);
        startActivity(intent);
    }
}
