package com.guc.aidltest.adapter;

import android.content.Context;

import com.guc.aidltest.R;
import com.guc.aidltest.bean.Person;

import java.util.List;

/**
 * Created by guc on 2018/8/17.
 * 描述：
 */
public class AdapterPerson extends CommonRecycleAdapter<Person> {

    public AdapterPerson(Context context, List<Person> mDatas) {
        super(context, mDatas, R.layout.item_person);

    }

    @Override
    public void bindData(CommonViewHolder holder, Person data) {
        holder.setText(R.id.tv_name, "姓名：" + data.name)
                .setText(R.id.tv_age, "年龄：" + data.age);
    }
}
