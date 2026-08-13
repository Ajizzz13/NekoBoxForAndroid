package io.nekohasekai.sagernet.fmt.internal;

import androidx.annotation.NonNull;

import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.nekohasekai.sagernet.fmt.KryoConverters;
import moe.matsuri.nb4a.utils.JavaUtil;

public class ComboBean extends InternalBean {

    public List<Long> proxies;
    public int interval;

    @Override
    public String displayName() {
        if (JavaUtil.isNotBlank(name)) {
            return name;
        } else {
            return "Combo " + Math.abs(hashCode());
        }
    }

    @Override
    public void initializeDefaultValues() {
        super.initializeDefaultValues();
        if (name == null) name = "";
        if (interval <= 0) interval = 5;

        if (proxies == null) {
            proxies = new ArrayList<>();
        }
    }

    @Override
    public void serialize(ByteBufferOutput output) {
        output.writeInt(2); // version 2
        output.writeInt(proxies.size());
        for (Long proxy : proxies) {
            output.writeLong(proxy);
        }
        output.writeInt(interval);
    }

    @Override
    public void deserialize(ByteBufferInput input) {
        int version = input.readInt();
        if (version < 1) {
            input.readString();
            input.readInt();
        }
        int length = input.readInt();
        proxies = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            proxies.add(input.readLong());
        }
        if (version >= 2) {
            interval = input.readInt();
        } else {
            interval = 5;
        }
    }

    @NotNull
    @Override
    public ComboBean clone() {
        return KryoConverters.deserialize(new ComboBean(), KryoConverters.serialize(this));
    }

    public static final Creator<ComboBean> CREATOR = new CREATOR<ComboBean>() {
        @NonNull
        @Override
        public ComboBean newInstance() {
            return new ComboBean();
        }

        @Override
        public ComboBean[] newArray(int size) {
            return new ComboBean[size];
        }
    };
}
