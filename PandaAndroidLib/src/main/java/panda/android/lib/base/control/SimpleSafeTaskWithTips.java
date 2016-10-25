package panda.android.lib.base.control;

import android.app.Dialog;
import android.content.Context;

import panda.android.lib.R;
import panda.android.lib.base.model.NetResultInfo;
import panda.android.lib.base.model.net.BaseRepositoryCollection;
import panda.android.lib.base.util.DevUtil;


/**
 * 简单的安全异步任务，仅仅指定返回结果的类型，不可输入参数（方便和调度器调度使用）
 * 被废弃：以后的错误码的处理统一放在BaseApp里面（采用 EventBus 机制）
 */
@Deprecated
public abstract class SimpleSafeTaskWithTips<T extends NetResultInfo> extends SimpleSafeTask<T> {
    public SimpleSafeTaskWithTips(Context context) {
        super(context);
    }

    public SimpleSafeTaskWithTips(Context context, Dialog dialog) {
        super(context, dialog);
    }

    @Override
    protected void onPostExecuteSafely(T result, Exception e) {
        super.onPostExecuteSafely(result, e);
        if (result == null) {
            DevUtil.showInfo(getContext(), getContext().getResources().getString(R.string.net_cannot_access));
            return;
        }

        if (result.getRespCode() != NetResultInfo.RETURN_CODE_000000) {
            DevUtil.showInfo(getContext(), result.getRespDesc());
            return;
        }
    }
}
