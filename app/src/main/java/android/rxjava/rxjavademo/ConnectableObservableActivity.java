package android.rxjava.rxjavademo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class ConnectableObservableActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        textView =(TextView)findViewById(R.id.textView);
    }

    public void goToOnClick(View v){
        createConnectableObservable();
    }

    private Observable getObservable() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                for (int i = 0; i < 3; i++) {
                    emitter.onNext(i);
                }

                emitter.onComplete();
            }
        });

    }

    private void createConnectableObservable() {
        io.reactivex.observables.ConnectableObservable<Integer> connectableObservable = getObservable().publish();
        connectableObservable.subscribe(getObserver("First Observer "));
        connectableObservable.subscribe(getObserver("Second Observer "));
        connectableObservable.connect();
    }

    private Observer<Integer> getObserver(final String tag) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                textView.append(tag + value);
                textView.append("\n");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
