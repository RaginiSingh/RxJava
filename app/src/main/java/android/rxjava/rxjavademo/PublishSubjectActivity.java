package android.rxjava.rxjavademo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/*PublishSubject is much similar to BehaviourSubject except that it emits only those items which are emitted after the subscription. Also, It doesn’t give any default value.public*/
public class PublishSubjectActivity extends MainActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void goToOnClick(View v) {
        PublishSubject publishSubject = PublishSubject.create();
        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);

        publishSubject.subscribe(getSecondObserver());
        publishSubject.onNext(4);
        publishSubject.onNext(5);
        publishSubject.onComplete();
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
                textView.append("First Observer Completed");
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
                textView.append("Second Observer Completed");
            }
        };
    }
}
