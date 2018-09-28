package com.mmy.maimaiyun.model.msg.ui.activity;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import java.util.Locale;

import butterknife.Bind;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

/**
 * @创建者 lucas
 * @创建时间 2017/11/23 0023 16:14
 * @描述
 */

public class ConversationActivity extends BaseActivity {
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    ConversationFragment mFragment;

    @Bind(R.id.title_center_text)
    TextView mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        String title = getIntent().getData().getQueryParameter("title");
        mTitle.setText(title);
    }

    @Override
    public void initData() {
        String targetId = getIntent().getData().getQueryParameter("targetId");
        String name = getIntent().getData().getLastPathSegment().toUpperCase(Locale.US);
        mConversationType = Conversation.ConversationType.valueOf(name);
        enterFragment(mConversationType,targetId);
    }

    /**
     * 加载会话页面 ConversationFragmentEx 继承自 ConversationFragment
     *
     * @param mConversationType 会话类型
     * @param mTargetId         会话 Id
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        mFragment = new ConversationFragment();

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        mFragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //xxx 为你要加载的 id
        transaction.add(R.id.rong_content, mFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_conversation1;
    }
}
