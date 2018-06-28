package android.rxjava.rxjavademo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectActivity extends MainActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void goToOnClick(View v) {
        ReplaySubject replaySubject = ReplaySubject.create();
        replaySubject.subscribe(getFirstObserver());

        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);

        replaySubject.subscribe(getSecondObserver());
        replaySubject.onNext(4);
        replaySubject.onNext(5);
        replaySubject.onComplete();
    }

    private Observer getFirstObserver() {
        return new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                textView.append("First Observer " + o);
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

    private Observer getSecondObserver() {
        return new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                textView.append("Second Observer " + o);
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
