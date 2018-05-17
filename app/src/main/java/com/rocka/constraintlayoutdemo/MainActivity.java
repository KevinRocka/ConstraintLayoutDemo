package com.rocka.constraintlayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_normal)
    Button btnNormal;
    @BindView(R.id.btn_bias)
    Button btnBias;
    @BindView(R.id.btn_chain)
    Button btnChain;
    @BindView(R.id.btn_ratio)
    Button btnRatio;
    @BindView(R.id.btn_circle)
    Button btnCircle;
    @BindView(R.id.btn_guidline)
    Button btnGuidline;
    @BindView(R.id.btn_barrier)
    Button btnBarrier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_normal, R.id.btn_bias, R.id.btn_chain, R.id.btn_ratio, R.id.btn_circle, R.id.btn_guidline, R.id.btn_barrier})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                toNewActivtiy(NormalActivity.class);
                break;
            case R.id.btn_bias:
                toNewActivtiy(BiasActivity.class);
                break;
            case R.id.btn_chain:
                toNewActivtiy(ChainActivity.class);
                break;
            case R.id.btn_ratio:
                toNewActivtiy(RatioActivity.class);
                break;
            case R.id.btn_circle:
                toNewActivtiy(CircleActivity.class);
                break;
            case R.id.btn_guidline:
                toNewActivtiy(GuidelineActivity.class);
                break;
            case R.id.btn_barrier:
                toNewActivtiy(BarrierActivity.class);
                break;
            default:
                break;
        }
    }

    public void toNewActivtiy(Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
}
