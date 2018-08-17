package com.guc.aidltest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.guc.aidltest.IPersonAidlInterface;
import com.guc.aidltest.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guc on 2018/8/17.
 * 描述：aidl服务端代码
 */
public class PersonService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<Person> mPersons;
    private IBinder mIBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
         */
        mIBinder = new IPersonAidlInterface.Stub() {
            @Override
            public void addPerson(Person person) throws RemoteException {
                if (mPersons == null) {
                    mPersons = new ArrayList<>();
                }
                mPersons.add(person);
            }

            @Override
            public List<Person> getPersonList() throws RemoteException {
                return mPersons;
            }
        };
    }

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        return mIBinder;
    }
}
