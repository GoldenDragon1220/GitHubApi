package com.exercise.githubuserviewer;

import com.exercise.githubuserviewer.api.BaseApiException;
import com.exercise.githubuserviewer.github.bean.GitHubUsersBean;
import com.exercise.githubuserviewer.github.bean.UserDetailInfo;
import com.exercise.githubuserviewer.github.repository.GitHubApiRepository;
import com.exercise.githubuserviewer.github.repository.IGitHubApiRepository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class ApiUnitTest {

    IGitHubApiRepository mRepository;

    @Before
    public void setUpRxSchedulers() {
        mRepository = new GitHubApiRepository();

        final Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(@android.support.annotation.NonNull Runnable runnable) {
                        runnable.run();
                    }
                });
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitComputationSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitSingleSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
    }

    @Test
    public void testGetGitHubUseCaseSuccess() {
        int page = 0;
        int perPage = 30;
        int since = 0;
        TestObserver<List<GitHubUsersBean>> observer = new TestObserver<>();
        mRepository.getUserList(page, perPage, since).subscribe(observer);
        observer.assertNoErrors();
    }

    @Test
    public void testGetUserDetailSuccess() {
        TestObserver<UserDetailInfo> observer = new TestObserver<>();
        mRepository.getUserDetailInfo("mojombo").subscribe(observer);
        observer.assertNoErrors();
    }

    @Test
    public void testGetUserDetailFailed() {
        TestObserver<UserDetailInfo> observer = new TestObserver<>();
        mRepository.getUserDetailInfo("adacdaeafda").subscribe(observer);
        observer.assertError(RuntimeException.class);
    }

}
