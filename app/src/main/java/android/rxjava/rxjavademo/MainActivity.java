package android.rxjava.rxjavademo;

import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMap(View v) {
        Observable<Integer> observable = Observable.fromArray(1, 2, 3)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer * 5;
                    }
                });
        observable.subscribe(getObserver1("Map"));
    }

    public void goToClick(View view) {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emit) {
                try {
                    emit.onNext(1);
                    emit.onNext(2);
                    emit.onNext(3);
                } catch (Exception ex) {
                    emit.onError(ex);
                }

                emit.onComplete();
            }
        });

        observable.subscribe(getObserver1("CREATE"));
    }

    private Observer<Integer> getObserver1(final String tag) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.d(tag, "Observer " + value);
                Toast.makeText(MainActivity.this, "Observer " + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void goToFilter(View v) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                });

        observable.subscribe(getObserver1("Filter"));
    }

    private Observer<String> getObserver2(final String tag) {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(tag, "Observer " + value);
                Toast.makeText(MainActivity.this, "Observer " + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


    }

    private Observable getColdOservable() {
        Observable<String> coldObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i <= 1; i++) {
                    e.onNext(String.valueOf(i));
                }
            }
        });

        return coldObservable;
    }

    public void goToColdObservable(View v) {
        getColdOservable().subscribe(getObserver1("Cold Observable Subscriber1"));
        getColdOservable().subscribe(getObserver1("Cold Observable Subscriber2"));
    }


    public void goToHotObservable(View v) {

        ConnectableObservable hotObservable = getColdOservable().publish();

        hotObservable.subscribe(getObserver1("Hot Observable Subscriber1"));
        hotObservable.subscribe(getObserver1("Hot Observable Subscriber2"));
        hotObservable.connect();
    }

    public void mapDemo() {
        Observable.just(5, 6, 7).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "-)";
            }
        }).subscribe();
    }

    public void goToFrom(View view) {
        Observable<String> fromObservable = Observable.fromArray("1", "2", "3");
        fromObservable.subscribe(getObserver2("FROM"));
    }

    public void goToJust(View view) {
        Observable<String> justObservable = Observable.fromArray("1", "2", "3");
        justObservable.subscribe(getObserver2("JUST"));
    }

    public void goToHotObservableTypes(View v) {
        Intent intent = new Intent(this, HotObservableActivity.class);
        startActivity(intent);
    }
}
