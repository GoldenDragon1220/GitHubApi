package com.exercise.githubuserviewer.github.interactors;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;

/**
 * Created by rexhuang on 2018/9/28.
 */

public abstract class BaseUseCase<T> {

    Disposable mDisposable;

    public abstract Single<T> buildObservable();

    public void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public void execute(BiConsumer<T, Throwable> consumer, Scheduler schedulers) {
        Single<T> o = buildObservable();
        if (schedulers != null) {
            o = o.observeOn(schedulers);
        }
        mDisposable = o.subscribe(consumer);
    }

    //By default, observe result on main thread
    public void execute(BiConsumer<T, Throwable> consumer) {
        execute(consumer, AndroidSchedulers.mainThread());
    }


}
