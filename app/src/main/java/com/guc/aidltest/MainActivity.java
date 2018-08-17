package com.guc.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guc.aidltest.adapter.AdapterPerson;
import com.guc.aidltest.bean.Person;
import com.guc.aidltest.service.PersonService;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_age)
    EditText mEtAge;
    @BindView(R.id.btn_add_person)
    Button mBtnAddPerson;
    @BindView(R.id.btn_get_person)
    Button mBtnGetPerson;
    @BindView(R.id.rcv_person)
    RecyclerView mRcvPerson;
    private IPersonAidlInterface mPersonAidl;

    private AdapterPerson mAdapter;
    private Person mPerson;
    private Random random = new Random();
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPersonAidl = IPersonAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPersonAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        //绑定服务
        Intent intent = new Intent(this, PersonService.class);
        bindService(intent, mConn, BIND_AUTO_CREATE);
    }

    private void initView() {
        mAdapter = new AdapterPerson(this, null);
        mRcvPerson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRcvPerson.setAdapter(mAdapter);
    }

    @OnClick({R.id.btn_add_person, R.id.btn_get_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_person:
                if (mPersonAidl == null) return;
                mPerson = new Person();
                mPerson.name = "万剑归中" + random.nextInt();
                mPerson.age = random.nextInt();
                try {
                    mPersonAidl.addPerson(mPerson);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_get_person:
                try {
                    mAdapter.update(mPersonAidl.getPersonList());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }
}
