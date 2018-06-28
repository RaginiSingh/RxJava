package android.rxjava.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class HotObservableSubjectActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_observale_types);
    }

    public void goToAsyncSubject(View v) {
        Observable<Integer> cold = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i <= 2; i++) {
                    System.out.println("AsyncSubject Observer Source Emits : " + i);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });

        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        cold.subscribe(asyncSubject);
        asyncSubject.subscribe(getObserver1("AsyncSubject Observer1 "));
        asyncSubject.subscribe(getObserver1("AsyncSubject Observer2 "));
    }

    private Observer<Integer> getObserver1(final String tag) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.d(tag, tag + value);
                Toast.makeText(HotObservableSubjectActivity.this, tag + " " + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void goToBehaviourSubject(View v) throws InterruptedException {
        Intent intent = new Intent(this, BehaviorSubjectActivity.class);
        startActivity(intent);
    }

    public void goToPublishSubject(View v) {
        Intent intent = new Intent(this, PublishSubjectActivity.class);
        startActivity(intent);
    }

    public void goToReplaySubject(View v) {
        Intent intent = new Intent(this, ReplaySubjectActivity.class);
        startActivity(intent);
    }
}
