package com.guc.aidltest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by guc on 2018/8/17.
 * 描述：实体类
 */
public class Person implements Parcelable {
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    public String name;
    public int age;

    public Person() {
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                "age='" + age + '\'' +
                '}';
    }
}
