package com.tuannv007.gplxb2.data.rxjava;

import com.tuannv007.gplxb2.data.model.Category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class GplxDatarepository {
    public static Observable<List<Category>> makeObservable =
            Observable.create(new ObservableOnSubscribe<List<Category>>() {
                @Override
                public void subscribe(ObservableEmitter<List<Category>> emitter) {
                    try {
                        emitter.onComplete();
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                }
            });
}
