package android.rxjava.rxjavademo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/*BehaviourSubject emits the most recently item at the time of subscription or a default item if none has been emitted and then continues the sequence until complete.*/

public class BehaviorSubjectActivity extends MainActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void goToOnClick(View v) {
        BehaviorSubject<Integer> source = BehaviorSubject.create();

        source.subscribe(getFirstObserver());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();
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
                textView.append("First completed");
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
                textView.append("Second completed");
            }
        };
    }
}
